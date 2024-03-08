package model;


import org.json.JSONArray;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    @Test
    public void testEmployeeToJsonIncludesWeeklyAvailability() {

        String name = "John Doe";
        String job = "Developer";
        Employee employee = new Employee(name, job);


        String day = "Monday";
        LocalTime startTime = LocalTime.of(9, 0); // 09:00
        LocalTime endTime = LocalTime.of(17, 0);  // 17:00
        DailyAvailability availability = new DailyAvailability(day, startTime, endTime);
        employee.getWeeklyAvailability().add(availability);


        JSONObject employeeJson = employee.toJson();


        JSONArray availabilityArray = employeeJson.getJSONArray("weeklyAvailability");
        assertEquals(1, availabilityArray.length());

        JSONObject availabilityJson = availabilityArray.getJSONObject(0);
        assertEquals(day, availabilityJson.getString("day"));
        assertEquals(startTime.format(DateTimeFormatter.ISO_LOCAL_TIME), availabilityJson.getString("startTime"));
        assertEquals(endTime.format(DateTimeFormatter.ISO_LOCAL_TIME), availabilityJson.getString("endTime"));
    }

    @Test
    public void test2EmployeeToJsonIncludesWeeklyAvailability() {
        // Set up
        Employee employee = new Employee("John Doe", "Developer");
        employee.getWeeklyAvailability().add(new DailyAvailability("Monday", LocalTime.of(9, 0)
                , LocalTime.of(17, 0)));
        employee.getWeeklyAvailability().add(new DailyAvailability("Tuesday", LocalTime.of(10, 0)
                , LocalTime.of(18, 0)));

        // Execution
        JSONObject employeeJson = employee.toJson();
        JSONArray availabilityArray = employeeJson.getJSONArray("weeklyAvailability");

        // Assertions
        assertEquals(2, availabilityArray.length());

        JSONObject firstAvailability = availabilityArray.getJSONObject(0);
        assertEquals("Monday", firstAvailability.getString("day"));
        assertEquals("09:00:00", firstAvailability.getString("startTime"));
        assertEquals("17:00:00", firstAvailability.getString("endTime"));

        JSONObject secondAvailability = availabilityArray.getJSONObject(1);
        assertEquals("Tuesday", secondAvailability.getString("day"));
        assertEquals("10:00:00", secondAvailability.getString("startTime"));
        assertEquals("18:00:00", secondAvailability.getString("endTime"));
    }
}




