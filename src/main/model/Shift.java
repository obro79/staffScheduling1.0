
package model;


import model.enums.Day;
import model.eventlog.Event;
import model.eventlog.EventLog;

public class Shift {
    private Day day;
    private TimeRange timeRange;
    private int numberOfEmployees;

    // Constructor with day, timeRange, and numberOfEmployees
    public Shift(Day day, TimeRange timeRange, int numberOfEmployees) {
        this.day = day;
        this.timeRange = timeRange;
        this.numberOfEmployees = numberOfEmployees;
        String eventString = "Shift was added for " + day + " " + timeRange + " with " + numberOfEmployees + " employees";
        EventLog.getInstance().logEvent(new Event(eventString));
    }

    // toString method for displaying shift details
    @Override
    public String toString() {
        return day + ": " + timeRange + ", " + numberOfEmployees + " employees needed";
    }

    // Getters for Shift fields
    public Day getDay() {
        return day;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }
}


