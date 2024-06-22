package model;


import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import model.DailyAvailability;

//Represents an employee at the store with a name, job and availability.

public class Employee  {

    private String name;
    private Job job;
    private Map<Day, List<DailyAvailability>> weeklyAvailability;

    //EFFECTS: creates a new instance of Employee with name and job and empty availability
    public Employee(String name, Job job) {
        this.weeklyAvailability = new HashMap<>();
        this.job = job;
        this.name = name;
        String eventString = "New Employee added with name " + name + " and job " + job;
        EventLog.getInstance().logEvent(new Event(eventString));
    }

    //MODIFIES: this
    //EFFECTS: sets employees name to given name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: sets employees job to given job
    public void setJob(Job job) {
        this.job = job;
    }

    public String getName() {
        return this.name;
    }

    public Job getJob() {
        return this.job;
    }

    public Map<Day, List<DailyAvailability>> getWeeklyAvailability() {
        return this.weeklyAvailability;
    }
}

