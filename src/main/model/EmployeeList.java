package model;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class EmployeeList {
    private List<Employee> employeeList;
    private static final EmployeeList onlyEmployeeList = new EmployeeList();

    private EmployeeList() {
        this.employeeList = new ArrayList<>();
    }

    public static EmployeeList getInstance() {
        return onlyEmployeeList;
    }

    public List<Employee> getEmployeeList() {

        return Collections.unmodifiableList(employeeList);
    }

    public void addEmployee(Employee e) {
        this.employeeList.add(e);
    }
}
