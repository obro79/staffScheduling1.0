package model;


import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private String name;
    private String job;
    private ArrayList<DailyAvailability> weeklyAvailability;
    private Employee newEmployee1;


    @BeforeEach

    public void runBefore() {
        newEmployee1 = new Employee();
    }

    @Test

    public void setJobTest() {
        newEmployee1.setJob("Cook");
        assertEquals("Cook", newEmployee1.getJob());

    }

    @Test

    public void setNameTest() {
        newEmployee1.setName("Owen");
        assertEquals("Owen", newEmployee1.getName());

    }

    @Test

    public void getNameTest() {
        newEmployee1.setName("Owen");
        assertEquals("Owen", newEmployee1.getName());

    }

    @Test

    public void getJobTest() {
        newEmployee1.setJob("Cook");
        assertEquals("Cook", newEmployee1.getJob());

    }


    @Test

    public void addSelfToListTest() {
        newEmployee1.setJob("Cook");
        newEmployee1.setName("Owen");
        newEmployee1.addSelfToList();

        assertTrue(EmployeeList.getInstance().getEmployeeList().contains(newEmployee1));

    }

    @Test

    public void getWeeklyAvailabilityTest() {

        assertEquals(newEmployee1.getWeeklyAvailability(), new ArrayList<DailyAvailability>());
    }





}

