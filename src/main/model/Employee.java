package model;

import java.util.Scanner;

public class Employee extends WeeklyAvailability {

    private String name;
    private String job;

    public Employee() {
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

    private void addSelfToList() {
        EmployeeList.getInstance().addEmployee(this);
    }


    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

}
