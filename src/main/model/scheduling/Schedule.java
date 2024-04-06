package model.scheduling;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.*;
import ui.*;
import model.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.IntStream;
import java.time.LocalTime;


public class Schedule {
    private MPSolver solver;
    private MPVariable[][][] xp; // Decision variables: employee i, shift j, segment k
    private int numEmployees;
    private int numShifts;
    private int numSegments;
    private StoreApp store;
    private List<EmployeeNeeds> shifts;


    public static final Map<String, Integer> DAY_TO_INT = Map.of(
            "Sunday", 0, "Monday", 1, "Tuesday", 2, "Wednesday", 3,
            "Thursday", 4, "Friday", 5, "Saturday", 6);

    private static final double HOURS_PER_SEGMENT = 0.5;
    private static final int SEGMENTS_PER_HOUR = 2;
    private static final int MAX_HOURS_WEEK = 40;
    private static final int MAX_HOURS_DAY = 8;
    private static final int MAX_DAYS_WEEK = 6;
    private static final double PENALTY_FOR_SHORT_SHIFT = 10;
    private static final double DESIRED_MIN_SHIFT_LENGTH = 2;

    public Schedule(StoreApp store) {
        this.store = store;
        numEmployees = store.getThisStore().getEmployeeList().getEmployeeList().size();
        numShifts = store.getThisStore().getAllEmployeeNeeds().size();
        numSegments = calculateAllSegmentsForShifts(store.getThisStore().getAllEmployeeNeeds());
        shifts = store.getThisStore().getAllEmployeeNeeds();
    }

    public void initializeModel() {
        Loader.loadNativeLibraries();
        solver = new MPSolver("ScheduleOptimization",
                MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

        // Initialize decision variables here based on the number of segments
        xp = new MPVariable[numEmployees][numShifts][48];

        for (int e = 0; e < numEmployees; e++) {
            for (int s = 0; s < numShifts; s++) {
                int numSegmentsForShift = calculateSegmentsForShift(shifts.get(s));
                for (int seg = 0; seg < xp[e][s].length; seg++) {
                    if (seg < numSegmentsForShift) {
                        // Initialize decision variable for actual segments
                        xp[e][s][seg] = solver.makeIntVar(0, 1, "worker_" + e + "_shift_" + s
                                                                + "_segment_" + seg);
                    } else {
                        // For segments beyond the actual number of segments in a shift, explicitly set to 0
                        xp[e][s][seg] = solver.makeIntVar(0, 0, "unused_worker_" + e + "_shift_" + s
                                                                + "_segment_" + seg);
                    }
                }
            }
        }
    }

    public void addConstraints() {
        addMaximumWorkingHoursConstraints();
        ensureShiftCoverage();
        addPenaltyForShortShifts();
    }

    private int calculateSegmentsForShift(EmployeeNeeds shift) {
        Duration shiftDuration = Duration.between(shift.getStartTime(), shift.getEndTime());
        return (int) (shiftDuration.toMinutes() / 30); // Assuming 30-minute segments
    }

    public int calculateAllSegmentsForShifts(List<EmployeeNeeds> en) {
        int result = 0;
        for (EmployeeNeeds ep : en) {
            result += calculateSegmentsForShift(ep);
        }
        return result;
    }

    private void addMaximumWorkingHoursConstraints() {
        // Constraint for maximum hours per week
        for (int e = 0; e < numEmployees; e++) {
            MPConstraint maxHoursPerWeek = solver.makeConstraint(0, MAX_HOURS_WEEK, "max_hours_week_" + e);
            for (int s = 0; s < numShifts; s++) {
                for (int seg = 0; seg < xp[e][s].length; seg++) {
                    maxHoursPerWeek.setCoefficient(xp[e][s][seg], HOURS_PER_SEGMENT);
                }
            }
        }

        // Constraint for maximum hours per day
        for (int e = 0; e < numEmployees; e++) {
            for (int day = 0; day < MAX_DAYS_WEEK; day++) {
                MPConstraint maxHoursPerDay = solver.makeConstraint(0, MAX_HOURS_DAY, "max_hours_day_" + e
                                                                                      + "_" + day);
                for (int s = 0; s < numShifts; s++) {
                    EmployeeNeeds currentShift = shifts.get(s);
                    String shiftDay = currentShift.getDay();
                    if (DAY_TO_INT.get(shiftDay) == day) {
                        for (int seg = 0; seg < xp[e][s].length; seg++) {
                            maxHoursPerDay.setCoefficient(xp[e][s][seg], HOURS_PER_SEGMENT);
                        }
                    }
                }
            }
        }
    }

    private void ensureShiftCoverage() {
        // Ensure each shift is covered the number of times specified by the shift's requirement
        for (int s = 0; s < numShifts; s++) {
            int requiredCoverage = shifts.get(s).getNumberOfEmployees();
            MPConstraint coverageConstraint = solver.makeConstraint(requiredCoverage, requiredCoverage,
                    "coverage_shift_" + s);
            for (int e = 0; e < numEmployees; e++) {
                for (int seg = 0; seg < xp[e][s].length; seg++) {
                    coverageConstraint.setCoefficient(xp[e][s][seg], 1);
                }
            }
        }
    }

    private void addPenaltyForShortShifts() { //honestly doing fine without this constraint
        // Add a soft constraint to minimize the number of short shifts (less than DESIRED_MIN_SHIFT_LENGTH hours)
        for (int e = 0; e < numEmployees; e++) {
            for (int s = 0; s < numShifts; s++) {
                int segmentsInShift = xp[e][s].length;
                double shiftLength = segmentsInShift * HOURS_PER_SEGMENT;
                if (shiftLength < DESIRED_MIN_SHIFT_LENGTH) {
                    //
                }
            }
        }
    }

    public String solve() {
        initializeModel();
        addConstraints();

        // Solve the model
        MPSolver.ResultStatus resultStatus = solver.solve();
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            return generateSolutionSummary();
        } else {
            return "No solution found.";
        }
    }

    public String generateSolutionSummary() {
        StringBuilder summaryBuilder = new StringBuilder();
        for (int e = 0; e < numEmployees; e++) {
            List<Employee> employeeList = store.getThisStore().getEmployeeList().getEmployeeList();
            String employeeName = employeeList.get(e).getName();
            Map<String, String> assignedShifts = new LinkedHashMap<>();

            for (int s = 0; s < numShifts; s++) {
                EmployeeNeeds shift = shifts.get(s);
                String shiftKey = shift.getDay() + " " + shift.getStartTime() + " to " + shift.getEndTime();

                for (int seg = 0; seg < xp[e][s].length; seg++) {
                    if (xp[e][s][seg].solutionValue() > 0.5) {
                        assignedShifts.putIfAbsent(shiftKey, String.format("%s %s to %s", shift.getDay(),
                                shift.getStartTime(), shift.getEndTime()));
                        break; // Prevent adding the same shift multiple times
                    }
                }
            }

            summaryBuilder.append(formatAssignments(employeeName, assignedShifts));
        }
        return summaryBuilder.toString();
    }

    private String formatAssignments(String employeeName, Map<String, String> assignedShifts) {
        StringBuilder assignmentsBuilder = new StringBuilder();
        if (!assignedShifts.isEmpty()) {
            assignmentsBuilder.append(String.format("%s was assigned:\n", employeeName));
            for (String shiftDetails : assignedShifts.values()) {
                assignmentsBuilder.append("  ").append(shiftDetails).append("\n");
            }
        } else {
            assignmentsBuilder.append(String.format("%s was not assigned any shifts.\n", employeeName));
        }
        System.out.println(assignmentsBuilder);
        return assignmentsBuilder.toString();
    }




}
