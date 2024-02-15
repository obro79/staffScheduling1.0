package model;

import java.util.Scanner;

public class Employee extends WeeklyAvailability {

    private String name;
    private String job;

    public Employee() {
        Scanner scanner = new Scanner(System.in);
        boolean infoCorrect;

        do {
            System.out.print("Enter employee's name: ");
            this.name = scanner.nextLine(); // Reads the next line for the name

            System.out.print("Enter employee's job: ");
            this.job = scanner.nextLine(); // Reads the next line for the job

            System.out.println("Would you also like to update their availability right now? (Yes/No)");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("Yes")) {
                this.updateAvailability();
            } else {
                System.out.println("Ok, you can add it later");
            }

            System.out.println("Employee's name: " + this.name);
            System.out.println("Employee's job: " + this.job);
            System.out.print("Please Confirm (Yes/No): ");
            String answer2 = scanner.nextLine();

            if (answer2.equalsIgnoreCase("Yes")) {
                infoCorrect = true;
                System.out.println("Great! The Employee has been added.");
            } else {
                System.out.println("Information is incorrect. Please enter again.");
                infoCorrect = false;
            }
        } while (!infoCorrect);
    }



    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

}
