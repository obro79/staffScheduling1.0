package ui;


import model.*;
import model.enums.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private List<Employee> employees;
    private Map<Day, List<Shift>> shifts;
    private Map<Day, List<TimeRange>> storeHours;

    // Constructor
    public Store() {
        this.employees = new ArrayList<>();
        this.shifts = new HashMap<>();
        this.storeHours = new HashMap<>();
    }

    // Add an employee to the store
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Add a shift to the store
    public void addShift(Shift shift) {
        shifts.computeIfAbsent(shift.getDay(), k -> new ArrayList<>()).add(shift);
    }

    // Add store hours
    public void addStoreHours(Day day, TimeRange timeRange) {
        storeHours.computeIfAbsent(day, k -> new ArrayList<>()).add(timeRange);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public Map<Day, List<Shift>> getShifts() {
        return new HashMap<>(shifts);
    }

    public Map<Day, List<TimeRange>> getStoreHours() {
        return new HashMap<>(storeHours);
    }

}
