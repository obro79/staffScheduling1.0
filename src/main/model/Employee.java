package model;


import java.util.ArrayList;


public class Employee {

    private String name;
    private String job;
    private ArrayList<DailyAvailability> weeklyAvailability;

    public Employee() {
        this.weeklyAvailability = new ArrayList<DailyAvailability>();
    }


    public void addSelfToList() {
        EmployeeList.getInstance().addEmployee(this);
    }

    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

    public ArrayList<DailyAvailability> getWeeklyAvailability() {
        return weeklyAvailability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
