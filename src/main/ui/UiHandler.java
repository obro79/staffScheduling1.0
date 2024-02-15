package ui;

import model.Employee;
import model.EmployeeList;
import model.OperationalNeeds;

import java.util.Scanner;

public class UiHandler {
    public static final Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("methodlength") // i tried for like 2 hours to reduce this method to <25 best I got was 26
    // used enums and helpers and everything felix said it's ok for switch else

    public static void runEmployeeManagementSystem() {
        Scanner scanner = new Scanner(System.in); // Make sure scanner is defined outside this snippet
        int option;

        do {
            System.out.println("What would you like to do?");
            System.out.println("(1) Add Employee");
            System.out.println("(2) Update Existing Employee Availability");
            System.out.println("(3) Get List of Employees");
            System.out.println("(4) Get Employee’s Availability");
            System.out.println("(5) Get Operational Hours");
            System.out.println("(6) Update Operational Hours");
            System.out.println("(7) Update Scheduling Needs");
            System.out.println("(8) Get Scheduling Needs"); // New option added here
            System.out.println("(9) Exit"); // Updated to be option 9
            System.out.print("Select an option (1-9): "); // Update prompt to reflect new option count

            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (option) {
                case 1:
                    Employee newEmployee = new Employee(); // Assuming constructor handles everything
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
                    getOperationalHours();
                    break;
                case 6:
                    updateOperationalHours();
                    break;
                case 7:
                    updateSchedulingNeeds();
                    break;
                case 8:
                    getSchedulingNeeds();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        } while (option != 9); // Update condition to reflect new exit option
        scanner.close(); // Note: Only close the scanner if you're done with System.in for the rest of the program
    }

    // Assume this method exists, adjust as needed for your application
    private static void getSchedulingNeeds() {
        System.out.println("Displaying Scheduling Needs...");
        // Logic to display scheduling needs
    }


    private static void addEmployee() {
        // Implementation
        System.out.println("Adding an employee...");
    }

    private static void updateEmployeeAvailability() {
        scanner.nextLine();
        System.out.println("Which Employee's Availability would you like to update? (Enter their Name): ");
        String employeeName = String.valueOf(scanner);
        for (Employee e : model.EmployeeList.getInstance().getEmployeeList()) {
            if (e.getName() == employeeName) {
                System.out.println("Ok Let's update their availability.");
                e.updateAvailability();
            }
        }
        System.out.println("It looks like there is no employee with that name.");
    } // would you like to add one?

    private static void getListOfEmployees() {

        System.out.println("Getting list of employees...");
    }

    private static void getEmployeeAvailability() {

        System.out.println("Which Employee's Availability would you like to get? (Enter their Name): ");
        scanner.nextLine();
        String employeeName = String.valueOf(scanner);
        for (Employee e : model.EmployeeList.getInstance().getEmployeeList()) {
            if (e.getName() == employeeName) {
                System.out.println("Ok here's their availability: ");
                e.updateAvailability();
            }
        }
        System.out.println("It looks like there is no employee with that name.");
        System.out.println("Would you like to try a different employee?: ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("Yes")) {
            getEmployeeAvailability();
        }
    }

    private static void getOperationalHours() {

        System.out.println("Getting store hours...");
        OperationalNeeds operationalNeeds = OperationalNeeds.getInstance();
        operationalNeeds.printStoreHours();
    }

    private static void updateOperationalHours() {

        System.out.println("Updating store hours...");
        OperationalNeeds operationalNeeds = OperationalNeeds.getInstance();
        operationalNeeds.updateStoreHours();

    }

    private static void updateSchedulingNeeds() {
        System.out.println("Updating scheduling needs...");
        OperationalNeeds operationalNeeds = OperationalNeeds.getInstance();
        operationalNeeds.updateEmployeeNeeds();
    }

    private static void getSchedulingNeed() {
        System.out.println("Getting scheduling needs...");
        OperationalNeeds operationalNeeds = OperationalNeeds.getInstance();
        operationalNeeds.updateEmployeeNeeds();
        operationalNeeds.printEmployeeNeeds();
    }
}

