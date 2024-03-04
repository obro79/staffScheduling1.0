package model;

import java.time.LocalTime;

//EmployeeNeeds allows user to specify which day, and from a time to another time and the number of employees
// needed at a time
public class EmployeeNeeds {
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

