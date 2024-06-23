package model;


import model.enums.Day;
import model.enums.Job;
import model.eventlog.Event;
import model.eventlog.EventLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Represents an employee at the store with a name, job and availability.

public class Employee  {

    private String name;
    private Job job;
    private Map<Day, List<DailyAvailability>> weeklyAvailability;
    private List<Shift> shifts;

    //EFFECTS: creates a new instance of Employee with name and job and empty availability
    public Employee(String name, Job job) {
        this.weeklyAvailability = new HashMap<>();
        shifts = new ArrayList<>();
        this.job = job;
        this.name = name;
        String eventString = "New Employee added with name " + name + " and job " + job;
        EventLog.getInstance().logEvent(new Event(eventString));
    }

    public void addDailyAvailability(DailyAvailability availability) {
        weeklyAvailability.computeIfAbsent(availability.getDay(), k -> new ArrayList<>()).add(availability);
    }

    public void addWeeklyAvailability(Day day, TimeRange... timeRanges) {
        for (TimeRange timeRange : timeRanges) {
            addDailyAvailability(new DailyAvailability(day, timeRange));
        }
    }

    //MODIFIES: this
    //EFFECTS: sets employees name to given name
    public void setName(String name) {
        this.name = name;
    }

    public List<Shift> getShifts() {
        return shifts;
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

