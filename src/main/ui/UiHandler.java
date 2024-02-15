package ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.Employee;
import model.EmployeeList;

public class UiHandler {
    public static final Scanner scanner = new Scanner(System.in);

    public static void runEmployeeManagementSystem() {

        int option;

        do {
            System.out.println("What would you like to do?");
            System.out.println("(1) Add Employee");
            System.out.println("(2) Update Existing Employee Availability");
            System.out.println("(3) Get List of Employees");
            System.out.println("(4) Get Employee’s Availability");
            System.out.println("(5) Get Store Hours");
            System.out.println("(6) Update Store Hours");
            System.out.println("(7) Update Scheduling Needs");
            System.out.println("(8) Exit");
            System.out.print("Select an option (1-8): ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (option) {
                case 1:
                    Employee newEmployee = new Employee(); //good now
                    break;
                case 2:
                    updateEmployeeAvailability();
                    break;
                case 3:
                    EmployeeList.getInstance().printAllEmployeeNames();
                    break;
                case 4:
                    getEmployeeAvailability();
                    break;
                case 5:
                    getStoreHours();
                    break;
                case 6:
                    updateStoreHours();
                    break;
                case 7:
                    updateSchedulingNeeds();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        } while (option != 8);

        scanner.close();
    }

    private static void addEmployee() {
        // Implementation
        System.out.println("Adding an employee...");
    }

    private static void updateEmployeeAvailability() {
        // Implementation
        System.out.println("Updating employee availability...");
    }

    private static void getListOfEmployees() {
        // Implementation
        System.out.println("Getting list of employees...");
    }

    private static void getEmployeeAvailability() {
        // Implementation
        System.out.println("Getting employee's availability...");
    }

    private static void getStoreHours() {
        // Implementation
        System.out.println("Getting store hours...");
    }

    private static void updateStoreHours() {
        // Implementation
        System.out.println("Updating store hours...");
    }

    private static void updateSchedulingNeeds() {
        // Implementation
        System.out.println("Updating scheduling needs...");
    }
}

