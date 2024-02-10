package model;

import java.util.*;

public class Employee {

    private String name;
    private String job;

    public Employee() {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Would you like to enter a new Employee? Yes/No:  ");
        String response = scanner.nextLine(); // Read the user input into a String

        if (response.equalsIgnoreCase("Yes")) {
            System.out.print("Enter employee's name: ");
            this.name = scanner.nextLine(); // Reads the next line for the name

            System.out.print("Enter employee's job: ");
            this.job = scanner.nextLine(); // Reads the next line for the job
        } else {
            System.out.println("Ok get back to work!");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

}
