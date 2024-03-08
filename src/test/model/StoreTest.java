package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    private static Store instance;
    protected List<DailyAvailability> storeHours;
    private ArrayList<EmployeeNeeds> allEmployeeNeeds;

    @BeforeEach

    public void runBefore() {


    }

    @Test
    void testSingletonInstance() {

        Store firstInstance = Store.getInstance();
        Store secondInstance = Store.getInstance();
        assertSame(firstInstance, secondInstance, "getInstance should return the same instance of OperationalNeeds");
    }

    @Test
    void testStoreHoursList() {

        Store store = Store.getInstance();
        assertNotNull(store.getStoreHours(), "getStoreHours should return a non-null List");
        assertTrue(store.getStoreHours().isEmpty(), "getStoreHours should return an empty List by default");
    }

    @Test
    void testAllEmployeeNeedsList() {

        Store store = Store.getInstance();
        assertNotNull(store.getAllEmployeeNeeds(), "getAllEmployeeNeeds should return a non-null List");
        assertTrue(store.getAllEmployeeNeeds().isEmpty(), "getAllEmployeeNeeds should return an empty List by default");
    }

    @Test
    void getEmployeeListTest() {
        Store store = Store.getInstance();
        EmployeeList employeeList = store.getEmployeeList();

        assertNotNull(employeeList, "getEmployeeList should return a non-null EmployeeList");
        assertTrue(employeeList.getEmployeeList().isEmpty(), "getEmployeeList should initially return an empty list");

        // Add mock employees to the list for testing
        Employee emp1 = new Employee("Alice", "Manager");
        Employee emp2 = new Employee("Bob", "Cook");
        employeeList.addEmployee(emp1);
        employeeList.addEmployee(emp2);

        // Check if employees are correctly added
        List<Employee> retrievedList = employeeList.getEmployeeList();
        assertEquals(2, retrievedList.size(), "getEmployeeList should return a list with two employees");
        assertSame(emp1, retrievedList.get(0), "First employee should be Alice");
        assertSame(emp2, retrievedList.get(1), "Second employee should be Bob");
    }



}

