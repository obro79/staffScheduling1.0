package model;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    private String name;
    private String job;
    private ArrayList<DailyAvailability> weeklyAvailability;
    private Employee newEmployee1;


    @BeforeEach

    public void runBefore() {
        newEmployee1 = new Employee("Owen", "Cook");
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
    void toJsontest() {
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("name", "Owen");
        expectedJson.put("job", "Cook");
        expectedJson.put("weeklyAvailability", new JSONArray());

        JSONObject actualJson = newEmployee1.toJson();


        assertEquals(expectedJson.toString(), actualJson.toString());
    }




    @Test

    public void getWeeklyAvailabilityTest() {

        assertEquals(newEmployee1.getWeeklyAvailability(), new ArrayList<DailyAvailability>());
    }


}

