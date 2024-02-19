package model;

import java.time.LocalTime;


public class EmployeeNeeds {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfEmployees;

    public EmployeeNeeds(String day, LocalTime startTime, LocalTime endTime, int numberOfEmployees) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public String toString() {
        return day + ": " + startTime + " to " + endTime + ", " + numberOfEmployees + " employees needed";
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

