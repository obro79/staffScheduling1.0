package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    private String name;
    private String job;
    private ArrayList<DailyAvailability> weeklyAvailability;

    public Employee() {
    }

    private String getInput(String prompt, boolean saveInput) {

        System.out.print(prompt);
        String input = ui.UiHandler.scanner.nextLine();
        if (saveInput) {
            if (prompt.toLowerCase().contains("name")) {
                this.name = input;
            } else if (prompt.toLowerCase().contains("job")) {
                this.job = input;
            }
        }
        return input;
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
