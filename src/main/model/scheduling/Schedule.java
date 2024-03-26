//package model.scheduling;
//
//import model.*;
//import ui.*;
//import java.util.*;
//import java.time.*;
//
//import com.google.ortools.Loader;
//import com.google.ortools.linearsolver.*;
//
//public class Schedule {
//
//    private StoreApp store;
//    private double[] shiftHours;
//    private int[] shiftDay;
//    private int[] numShiftsPerDay;
//
//    private static final Map<String, Integer> DAY_TO_INT = Map.of(
//            "Sunday", 0,
//            "Monday", 1,
//            "Tuesday", 2,
//            "Wednesday", 3,
//            "Thursday", 4,
//            "Friday", 5,
//            "Saturday", 6
//    );
//
//    public Schedule(StoreApp store) {
//        this.store = store;
//        this.shiftHours = getShiftHours();
//        this.shiftDay = getShiftDay();
//        this.numShiftsPerDay = calculateNumShiftsPerDay();
//    }
//
//    static {
//        Loader.loadNativeLibraries();
//    }
//
//    public void maximizeEmployeeHours() {
//        MPSolver solver = new MPSolver(
//                "ScheduleOptimization",
//                MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);
//
//        int numEmployees = getNumberOfEmployees();
//        int numShifts = getNumberOfShifts();
//        boolean[][] available = getEmployeeAvailability();
//
//        MPVariable[][] x = new MPVariable[numEmployees][numShifts];
//        for (int i = 0; i < numEmployees; i++) {
//            for (int j = 0; j < numShifts; j++) {
//                x[i][j] = solver.makeIntVar(0, 1, "x[" + i + "," + j + "]");
//            }
//        }
//
//        for (int i = 0; i < numEmployees; i++) {
//            MPConstraint constraint = solver.makeConstraint(0, 40, "max_40_hours_week_" + i);
//            for (int j = 0; j < numShifts; j++) {
//                constraint.setCoefficient(x[i][j], shiftHours[j]);
//            }
//        }
//
//        for (int i = 0; i < numEmployees; i++) {
//            for (int j = 0; j < numShifts; j++) {
//                if (!available[i][j]) { // Directly check availability
//                    MPConstraint constraint = solver.makeConstraint(0, 0, "avail_" + i + "_" + j);
//                    constraint.setCoefficient(x[i][j], 1);
//                }
//            }
//        }
//
//        for (int j = 0; j < numShifts; j++) {
//            List<EmployeeNeeds> shifts = store.getThisStore().getAllEmployeeNeeds();
//            EmployeeNeeds shiftNeeds = shifts.get(j);
//            int employeesNeeded = shiftNeeds.getNumberOfEmployees();
//            MPConstraint constraint = solver.makeConstraint(1, employeesNeeded, "shift_filled_once_" + j);
//            for (int i = 0; i < numEmployees; i++) {
//                constraint.setCoefficient(x[i][j], 1);
//            }
//        }
//
//        for (int i = 0; i < numEmployees; i++) {
//            for (int day = 0; day < 7; day++) {
//                MPConstraint constraint = solver.makeConstraint(0, 8, "max_8_hours_day_" + day + "_emp_" + i);
//                for (int j = 0; j < numShifts; j++) {
//                    if (shiftDay[j] == day) { // If the shift is on this day
//                        constraint.setCoefficient(x[i][j], shiftHours[j]); // Add the shift's hours to the day's tota
//                    }
//                }
//            }
//        }
//
//        MPObjective objective = solver.objective();
//        for (int i = 0; i < numEmployees; i++) {
//            for (int j = 0; j < numShifts; j++) {
//                objective.setCoefficient(x[i][j], 1);
//            }
//        }
//        objective.setMaximization();
//
//        MPSolver.ResultStatus resultStatus = solver.solve();
//        if (resultStatus == MPSolver.ResultStatus.OPTIMAL || resultStatus == MPSolver.ResultStatus.FEASIBLE) {
//            System.out.println("Solution found!");
//            List<Employee> employees = store.getThisStore().getEmployeeList().getEmployeeList(); // ees
//            List<EmployeeNeeds> shifts = store.getThisStore().getAllEmployeeNeeds(); //
//
//            for (int i = 0; i < numEmployees; i++) {
//                Employee employee = employees.get(i); // Assuming you have a way to get the specific employee object
//                String employeeName = employee.getName(); // Assuming Employee class has a getName() method
//                System.out.println(employeeName + " is assigned to shifts:");
//
//                // Iterate through each shift
//                for (int j = 0; j < numShifts; j++) {
//                    // Directly print out the solution value for this employee-shift combination
//
//                    // Check if employee i is assigned to shift j
//                    if (x[i][j].solutionValue() == 1) {
//                        // Assuming EmployeeNeeds (shifts) has a method to describe the shift
//                        String shiftDescription = "Shift " + shifts.get(j).toString();
//                        System.out.println(shiftDescription);
//                    }
//                }
//            }
//        } else {
//            System.out.println("No solution found.");
//        }
//    }
//
//    // Placeholder methods for example purposes
//    private  int getNumberOfEmployees() {
//
//        List<Employee> employeeList = this.store.getThisStore().getEmployeeList().getEmployeeList();
//        return employeeList.size();
//    }
//
//    private  int getNumberOfShifts() {
//        List<EmployeeNeeds> employeeNeeds = this.store.getThisStore().getAllEmployeeNeeds();
//        return employeeNeeds.size();
//
//    }
//
//
//    private int[] getShiftDay() {
//        List<EmployeeNeeds> needs = this.store.getThisStore().getAllEmployeeNeeds();
//        int[] shiftDay = new int[needs.size()];
//
//        for (int i = 0; i < needs.size(); i++) {
//            // Map the day string to a DayOfWeek and then to the int value
//            shiftDay[i] = DayOfWeek.valueOf(needs.get(i).getDay().toUpperCase()).getValue();
//        }
//
//        return shiftDay;
//    }
//
//
//    private double[] getShiftHours() {
//        List<EmployeeNeeds> needs = this.store.getThisStore().getAllEmployeeNeeds();
//        double[] shiftHours = new double[needs.size()];
//
//        for (int i = 0; i < needs.size(); i++) {
//            // Calculate shift length in hours
//            EmployeeNeeds need = needs.get(i);
//            long minutes = java.time.Duration.between(need.getStartTime(), need.getEndTime()).toMinutes();
//            shiftHours[i] = minutes / 60.0;
//        }
//
//        return shiftHours;
//    }
//
//
//    private int[] getNumShiftsPerDay() {
//        List<EmployeeNeeds> needs = this.store.getThisStore().getAllEmployeeNeeds();
//        int[] numShiftsPerDay = new int[7]; // 7 days in a week
//
//        for (EmployeeNeeds need : needs) {
//            int dayIndex = DayOfWeek.valueOf(need.getDay().toUpperCase()).getValue() - 1; // Convert day t
//            numShiftsPerDay[dayIndex]++;
//        }
//
//        return numShiftsPerDay;
//    }
//
//    private boolean[][] getEmployeeAvailability() {
//        List<Employee> employees = this.store.getThisStore().getEmployeeList().getEmployeeList();
//        List<EmployeeNeeds> needs = this.store.getThisStore().getAllEmployeeNeeds();
//        boolean[][] availability = new boolean[employees.size()][needs.size()];
//
//        for (int i = 0; i < employees.size(); i++) {
//            List<DailyAvailability> availabilities = employees.get(i).getWeeklyAvailability();
//            for (int j = 0; j < needs.size(); j++) {
//                EmployeeNeeds need = needs.get(j);
//                DayOfWeek needDay = DayOfWeek.valueOf(need.getDay().toUpperCase());
//
//                // Check if the employee is available for this day and time
//                boolean isAvailable = availabilities.stream()
//                        .anyMatch(avail -> DayOfWeek.valueOf(avail.getDay().toUpperCase()).equals(needDay)
//                                           && !need.getStartTime().isBefore(avail.getStartTime())
//                                           && !need.getEndTime().isAfter(avail.getEndTime()));
//                availability[i][j] = isAvailable;
//            }
//        }
//
//        return availability;
//    }
//
//    private boolean isEmployeeAvailableForShift(Employee employee, EmployeeNeeds shift) {
//        for (DailyAvailability avail : employee.getWeeklyAvailability()) {
//            // Check if the day of the week matches
//            if (avail.getDay().equalsIgnoreCase(shift.getDay())) {
//                // Check if the employee's available start time is before or equal to the shift start time
//                // and the employee's available end time is after or equal to the shift end time
//        if (!avail.getStartTime().isAfter(shift.getStartTime()) && !avail.getEndTime().isBefore(shift.getEndTime())) {
//                    return true; // Employee is available for this shift
//                }
//            }
//        }
//        return false; // Employee is not available for this shift
//    }
//
//    private int[] calculateNumShiftsPerDay() {
//        List<EmployeeNeeds> needs = this.store.getThisStore().getAllEmployeeNeeds();
//        int[] numShiftsPerDay = new int[7]; // Initialize with 0s for Sunday through Saturday
//
//        for (EmployeeNeeds need : needs) {
//            // Assuming EmployeeNeeds has a method getDayOfWeek() that returns an int 0-6 for Sunday-Saturday
//            int dayIndex = DAY_TO_INT.get(need.getDay());
//            numShiftsPerDay[dayIndex]++;
//        }
//
//        return numShiftsPerDay;
//    }
//
//    private boolean areAllTrue(boolean[][] array) {
//        for (boolean[] row : array) {
//            for (boolean value : row) {
//                if (!value) {
//                    return false; // Found a false value, return false
//                }
//            }
//        }
//        return true; // All values were true
//    }
//
//
//
//
//}
