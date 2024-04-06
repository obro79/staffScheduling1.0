package model;

import persistence.Writeable;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

//stores a list of employees.

public class EmployeeList {
    private List<Employee> employeeList;
    private static EmployeeList onlyEmployeeList = new EmployeeList();

    //EFFECTS: creates a new instance of EmployeeList
    public EmployeeList() {
        this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public static EmployeeList getInstance() {
        return onlyEmployeeList;
    }

    //MODIFIES: this
    //EFFECTS: adds given Employee to employeelist
    public void addEmployee(Employee e) {
        this.employeeList.add(e);
    }
}
