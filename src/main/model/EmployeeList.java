package model;

import java.util.ArrayList;

public class EmployeeList {

    private ArrayList<Employee> employeeList;
    private static EmployeeList onlyEmployeeList = new EmployeeList();

    public EmployeeList() {
        this.employeeList = new ArrayList<Employee>();
    }

    public static EmployeeList getInstance() {
        return onlyEmployeeList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public void addEmployee(Employee e) {
        getInstance().addEmployee(e);
    }

}