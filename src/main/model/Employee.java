package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    private String name;
    private String job;
    private ArrayList<DailyAvailability> weeklyAvailability;

    public Employee() {
        this.weeklyAvailability = new ArrayList<>();
        do {
            getInput("Enter employee's name: ", true);
            getInput("Enter employee's job: ", true);
            if (getInput("Would you also like to update their availability right now? (Yes/No): ",false).equalsIgnoreCase("Yes")) {
                updateAvailability();
            } else {
                System.out.println("Ok, you can add it later");
            }
        } while (!getConfirmation());
        addSelfToList();
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

    private boolean getConfirmation() {
        System.out.println("Employee's name: " + this.name);
        System.out.println("Employee's job: " + this.job);
        return getInput("Please Confirm (Yes/No): ", false).equalsIgnoreCase("Yes");
    }

    public void updateAvailability() {

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : daysOfWeek) {
            System.out.println("Enter availability for " + day + ": ");
            System.out.print("Start Time (HH:MM): ");
            LocalTime startTime = LocalTime.parse(ui.UiHandler.scanner.nextLine());
            System.out.print("End Time (HH:MM): ");
            LocalTime endTime = LocalTime.parse(ui.UiHandler.scanner.nextLine());
            weeklyAvailability.add(new DailyAvailability(day, startTime, endTime));
        }
    }

    private void addSelfToList() {
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
}
