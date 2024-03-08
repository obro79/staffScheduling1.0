package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeListTest {

    private ArrayList<Employee> employeeList;
    private static EmployeeList onlyEmployeeList;
    private EmployeeList emplist1;
    private Employee employee1;


    @BeforeEach
    void runBefore() {
        employee1 = new Employee("Owen","Cook");
        employee1.setJob("Cook");
        employee1.setName("Owen");
    }

    @Test
    void testSingletonInstance() {
        EmployeeList firstInstance = EmployeeList.getInstance();
        EmployeeList secondInstance = EmployeeList.getInstance();
        assertSame(firstInstance, secondInstance);
    }

    @Test
    void testGetEmployeeList() {

        EmployeeList employeeList = EmployeeList.getInstance();
        assertNotNull(employeeList.getEmployeeList());

        // Test the list is empty initially
        assertTrue(employeeList.getEmployeeList().isEmpty());

        // Test adding an employee to the list and retrieving it
        employeeList.addEmployee(employee1);
        assertFalse(employeeList.getEmployeeList().isEmpty());
        assertEquals(employee1, employeeList.getEmployeeList().get(0));
    }

}
