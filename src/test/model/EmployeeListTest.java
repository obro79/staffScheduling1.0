package model;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeListTest {

    private ArrayList<Employee> employeeList;
    private EmployeeList onlyEmployeeList;


    @BeforeEach

    public void runBefore() {

    }


    private EmployeeList() {
        this.employeeList = new ArrayList<Employee>();
    }

    public static EmployeeList getInstance() {
        return onlyEmployeeList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public void addEmployee(Employee e) {  //Look at this later
        if (!employeeList.contains(e)) {
            employeeList.add(e);
            System.out.println("Employee added: " + e.getName());
        } else {
            System.out.println("This employee is already in the list!");
        }
    }
}