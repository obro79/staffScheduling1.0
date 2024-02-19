package model;

import java.util.ArrayList;

public class EmployeeList {

    private ArrayList<Employee> employeeList;
    private static EmployeeList onlyEmployeeList = new EmployeeList();

    private EmployeeList() {
        this.employeeList = new ArrayList<Employee>();
    }

    public static EmployeeList getInstance() {
        return onlyEmployeeList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return this.employeeList;
    }

}