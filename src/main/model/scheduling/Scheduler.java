package model.scheduling;

import com.google.ortools.Loader;
import com.google.ortools.sat.*;
import model.*;
import model.enums.Day;
import ui.Store;

import java.time.LocalTime;
import java.util.*;

public class Scheduler {
    static {
        Loader.loadNativeLibraries();
    }

    private final Store store;
    private final Schedule schedule;
    private final Map<String, IntVar> shiftAssignments;
    private List<Employee> employees;
    private Map<Day, List<Shift>> shifts;
    private CpModel model;

    public Scheduler(Store store) {
        this.store = store;
        this.schedule = new Schedule();
        this.shiftAssignments = new HashMap<>();
    }

    public Schedule createSchedule() {
        initializeModel();
        createShiftAssignments();
        addShiftCoverageConstraints();
        addEmployeeAvailabilityConstraints();
        addWorkingHoursConstraints();
        solveModel();
        return schedule;
    }

    private void initializeModel() {
        model = new CpModel();
        employees = store.getEmployees();
        shifts = store.getShifts();
    }

    private void createShiftAssignments() {
        for (Map.Entry<Day, List<Shift>> entry : shifts.entrySet()) {
            Day day = entry.getKey();
            List<Shift> dayShifts = entry.getValue();

            for (Shift shift : dayShifts) {
                for (Employee employee : employees) {
                    String varName = employee.getName() + "_" + day + "_" + shift.getTimeRange().getStartTime() + "_"
                                     + shift.getTimeRange().getEndTime();
                    IntVar assignment = model.newBoolVar(varName);
                    shiftAssignments.put(varName, assignment);
                }
            }
        }
    }

    private void addShiftCoverageConstraints() {
        for (Map.Entry<Day, List<Shift>> entry : shifts.entrySet()) {
            Day day = entry.getKey();
            List<Shift> dayShifts = entry.getValue();

            for (Shift shift : dayShifts) {
                List<IntVar> shiftVars = new ArrayList<>();
                for (Employee employee : employees) {
                    String varName = employee.getName() + "_" + day + "_" + shift.getTimeRange().getStartTime() + "_"
                                     + shift.getTimeRange().getEndTime();
                    shiftVars.add(shiftAssignments.get(varName));
                }
                model.addEquality(LinearExpr.sum(shiftVars.toArray(new IntVar[0])), shift.getNumberOfEmployees());
            }
        }
    }

    private void addEmployeeAvailabilityConstraints() {
        for (Employee employee : employees) {
            Map<Day, List<DailyAvailability>> availability = employee.getWeeklyAvailability();
            for (Map.Entry<Day, List<DailyAvailability>> availabilityEntry : availability.entrySet()) {
                Day day = availabilityEntry.getKey();
                List<DailyAvailability> dailyAvailabilities = availabilityEntry.getValue();

                List<Shift> dayShifts = shifts.getOrDefault(day, Collections.emptyList());
                for (Shift shift : dayShifts) {
                    String varName = employee.getName() + "_" + day + "_" + shift.getTimeRange().getStartTime() + "_"
                                     + shift.getTimeRange().getEndTime();
                    IntVar assignment = shiftAssignments.get(varName);

                    boolean isAvailable = dailyAvailabilities.stream()
                            .anyMatch(a -> a.isAvailable() && shift.getTimeRange().isWithin(a.getTimeRange()));
                    if (!isAvailable) {
                        model.addEquality(assignment, 0);
                    }
                }
            }
        }
    }


    private void addWorkingHoursConstraints() {
        int maxHoursPerDay = 8;
        int maxHoursPerWeek = 40;
        for (Employee employee : employees) {
            // Daily working hours constraint
            for (Day day : Day.values()) {
                List<Shift> dayShifts = shifts.getOrDefault(day, Collections.emptyList());
                List<IntVar> dailyAssignments = new ArrayList<>();
                for (Shift shift : dayShifts) {
                    String varName = employee.getName() + "_" + day + "_" + shift.getTimeRange().getStartTime() + "_"
                                     + shift.getTimeRange().getEndTime();
                    dailyAssignments.add(shiftAssignments.get(varName));
                }
                model.addLessOrEqual(LinearExpr.sum(dailyAssignments.toArray(new IntVar[0])), maxHoursPerDay);
            }

            // Weekly working hours constraint
            List<IntVar> weeklyAssignments = new ArrayList<>();
            for (Day day : Day.values()) {
                List<Shift> dayShifts = shifts.getOrDefault(day, Collections.emptyList());
                for (Shift shift : dayShifts) {
                    String varName = employee.getName() + "_" + day + "_" + shift.getTimeRange().getStartTime() + "_"
                                     + shift.getTimeRange().getEndTime();
                    weeklyAssignments.add(shiftAssignments.get(varName));
                }
            }
            model.addLessOrEqual(LinearExpr.sum(weeklyAssignments.toArray(new IntVar[0])), maxHoursPerWeek);
        }
    }

    private void solveModel() {
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        if (status == CpSolverStatus.FEASIBLE || status == CpSolverStatus.OPTIMAL) {
            for (Map.Entry<String, IntVar> entry : shiftAssignments.entrySet()) {
                if (solver.value(entry.getValue()) == 1) {
                    String[] parts = entry.getKey().split("_");
                    if (parts.length != 4) {
                        throw new IllegalArgumentException("Invalid assignment key: " + entry.getKey());
                    }

                    String employeeName = parts[0];
                    Day day = Day.valueOf(parts[1]);
                    String startTimeStr = parts[2];
                    String endTimeStr = parts[3];

                    LocalTime startTime = LocalTime.parse(startTimeStr);
                    LocalTime endTime = LocalTime.parse(endTimeStr);
                    TimeRange timeRange = new TimeRange(startTime, endTime);

                    Shift shift = shifts.get(day).stream()
                            .filter(s -> s.getTimeRange().equals(timeRange))
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Shift not found for time range: "
                                                                         + timeRange));

                    Employee employee = employees.stream()
                            .filter(e -> e.getName().equals(employeeName))
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Employee not found: " + employeeName));

                    schedule.assignEmployeeToShift(employee, shift);
                }
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
