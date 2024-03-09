package model;

import java.time.LocalTime;
import org.json.JSONObject;
import org.json.*;
import persistence.Writeable;


//EmployeeNeeds allows user to specify which day, and from a time to another time and the number of employees
// needed at a time.
public class EmployeeNeeds implements Writeable {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfEmployees;

    //Effects creates a new Employee Needs with given day, startTime, endTime, numberOfEmployees
    public EmployeeNeeds(String day, LocalTime startTime, LocalTime endTime, int numberOfEmployees) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfEmployees = numberOfEmployees;
    }


    //EFFECTS: converts EmployeeNeeds to a string
    public String toString() {
        return day + ": " + startTime + " to " + endTime + ", " + numberOfEmployees + " employees needed";
    }

    //EFFECTS: turns this into a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", this.day);
        json.put("startTime", this.startTime.toString());
        json.put("endTime", this.endTime.toString());
        json.put("numberOfEmployees", this.numberOfEmployees);
        return json;
    }


    public String getDay() {
        return this.day;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public int getNumberOfEmployees() {
        return this.numberOfEmployees;
    }

}

