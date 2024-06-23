package model.scheduling;

import model.DailyAvailability;
import model.Employee;
import model.Shift;
import model.enums.Day;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    private final Map<Day, List<Shift>> shifts;
    private final Map<Shift, List<Employee>> shiftAssignments;

    public Schedule() {
        this.shifts = new HashMap<>();
        this.shiftAssignments = new HashMap<>();
    }

    // Add a shift to the schedule
    public void addShift(Shift shift) {
        shifts.computeIfAbsent(shift.getDay(), k -> new ArrayList<>()).add(shift);
    }

    // Assign an employee to a shift
    public void assignEmployeeToShift(Employee employee, Shift shift) {
        // Validate the assignment
        if (shifts.get(shift.getDay()) == null || !shifts.get(shift.getDay()).contains(shift)) {
            throw new IllegalArgumentException("Shift does not exist in the schedule");
        }

        boolean isAvailable = false;
        for (DailyAvailability availability : employee.getWeeklyAvailability().get(shift.getDay())) {
            if (availability.isAvailable() && shift.getTimeRange().isWithin(availability.getTimeRange())) {
                isAvailable = true;
                break;
            }
        }

        if (!isAvailable) {
            throw new IllegalArgumentException("Employee is not available for this shift");
        }

        // Assign the employee to the shift
        shiftAssignments.computeIfAbsent(shift, k -> new ArrayList<>()).add(employee);
    }

    // Get the list of shifts for a given day
    public List<Shift> getShifts(Day day) {
        return new ArrayList<>(shifts.getOrDefault(day, new ArrayList<>()));
    }

    // Get the list of employees assigned to a given shift
    public List<Employee> getEmployeesForShift(Shift shift) {
        return new ArrayList<>(shiftAssignments.getOrDefault(shift, new ArrayList<>()));
    }
}

