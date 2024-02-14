package model;

import java.util.LinkedList;

public class EmployeeList extends Employee {

    private LinkedList<Employee> employeeList;

    public EmployeeList() {
        employeeList = new LinkedList<Employee>();
    }

    public LinkedList<Employee> getEmployeeList() {
        return this.employeeList;
    }

}
