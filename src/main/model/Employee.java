package model;


import java.util.ArrayList;
import org.json.JSONObject;

import model.DailyAvailability;



//Represents an employee at the resturant with a name, job and an availability

public class Employee {

    private String name;
    private String job;
    private ArrayList<DailyAvailability> weeklyAvailability;

    //EFFECTS: creates a new instance of Employee
    public Employee() {
        this.weeklyAvailability = new ArrayList<DailyAvailability>();
    }

    //MODIFIES: EmployeeList
    //EFFECTS: Adds Employee to EmployeeList
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
        return this.weeklyAvailability;
    }

    //MODIFIES: this
    //EFFECTS: sets employees name to given name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: sets employees job to given job
    public void setJob(String job) {
        this.job = job;
    }


}
