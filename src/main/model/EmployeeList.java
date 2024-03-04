package model;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

//stores a list of employees and there is only one of them.

public class EmployeeList {
    private List<Employee> employeeList;
    private static final EmployeeList onlyEmployeeList = new EmployeeList();

    //EFFECTS: creates a new instance of EmployeeList
    private EmployeeList() {
        this.employeeList = new ArrayList<>();
    }

    public static EmployeeList getInstance() {
        return onlyEmployeeList;
    }

    public List<Employee> getEmployeeList() {
        return Collections.unmodifiableList(employeeList);
    }

    //MODIFIES: this
    //EFFECTS: adds given Employee to employeelist
    public void addEmployee(Employee e) {
        this.employeeList.add(e);
    }
}
