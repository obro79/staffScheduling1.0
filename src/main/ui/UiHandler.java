package ui;

import model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//Handles all the user interactions; prompts the user to choose from one of the options.

public class UiHandler {
    public static final Scanner scanner = new Scanner(System.in);

    public UiHandler() {

    }

    //EFFECTS: will execute one of the cases
    public static void runEmployeeManagementSystem() {

        int option;
        do {
            printOptions();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: addEmployee();
                break;
                case 2: updateEmployeeAvailability();
                    break;
                case 3: printAllEmployeeNames();
                    break;
                case 4: getEmployeeAvailability();
                    break;
                case 5: printOperationalHours();
                    break;
                case 6: updateStoreHours();
                    break;
                case 7: updateEmployeeNeeds();
                    break;
                case 8: printEmployeeNeeds();
                    break;
                case 9: System.out.println("Exiting...");
                    break;
                default: System.out.println("Invalid option. Please select a valid option.");
            }
        } while (option != 9); // Update condition to reflect new exit option
        scanner.close();
    }

    //EFFECTS: allows user to update employee's Availability for existing Employee
    public static void updateEmployeeAvailability() {
        System.out.println("Which Employees Availability would you like to update? (Enter their Name): ");
        String employeeName = scanner.nextLine();
        for (Employee e : model.EmployeeList.getInstance().getEmployeeList()) {
            if (e.getName().equalsIgnoreCase(employeeName)) {
                System.out.println("Ok Let's update their availability.");
                updateAvailability(e);
            }
        }
        System.out.println("It looks like there is no employee with that name.");
    } // would you like to add one?

    //MODIFIES: Employee if they are in employeeList
    //EFFECTS: updates the employees availability or lets user know that the employee doesnt exist
    static void getEmployeeAvailability() {
        boolean retry = false; // Flag to control the retry loop
        do {
            System.out.println("Which Employee's Availability would you like to get? (Enter their Name): ");
            String employeeName = scanner.nextLine();
            boolean found = false; // Flag to check if an employee is found

            for (Employee e : model.EmployeeList.getInstance().getEmployeeList()) {
                if (e.getName().equalsIgnoreCase(employeeName)) {
                    System.out.println("Ok, here's their availability: ");
                    printEmployeeAvailability(e);
                    found = true; // Mark as found
                    break; // Exit the loop as we've found the employee
                }
            }

            if (!found) {
                System.out.println("It looks like there is no employee with that name.");
            }

            System.out.println("Would you like to try a different employee? (Yes/No): ");
            String response = scanner.nextLine();
            retry = response.equalsIgnoreCase("Yes"); // Update the retry flag based on user input
        } while (retry); // Continue looping if the user wants to retry
    }



    public static void printEmployeeAvailability(Employee e) {
        for (DailyAvailability d: e.getWeeklyAvailability()) {
            System.out.println(d.toString());
        }
    }

    //EFFECTS: Prints all the options that the user can do to the console
    public static void printOptions() {
        System.out.println("What would you like to do?");
        System.out.println("(1) Add Employee");
        System.out.println("(2) Update Existing Employee Availability");
        System.out.println("(3) Get List of Employees");
        System.out.println("(4) Get Employees Availability");
        System.out.println("(5) Get Operational Hours"); // will swap option 5 and 6 later. that will make more sense
        System.out.println("(6) Update Operational Hours");
        System.out.println("(7) Update Scheduling Needs");
        System.out.println("(8) Get Scheduling Needs");
        System.out.println("(9) Exit");
        System.out.print("Select an option (1-9): ");
    }

    //EFFECTS: Creates a new employee with the job and name specified by the user
    public static void addEmployee() {

        String name;
        String job;
        boolean confirmed;

        Employee newEmployee;

        do {
            System.out.print("Enter employee's name: ");
            name = scanner.nextLine();
            System.out.print("Enter employee's job: ");
            job = scanner.nextLine();

            newEmployee = new Employee();
            newEmployee.setName(name);
            newEmployee.setJob(job);

            String prompt = "Would you also like to update their availability right now? (Yes/No): ";
            System.out.println(prompt);
            if (scanner.nextLine().equalsIgnoreCase("Yes")) {
               // updateAvailability();
            } else {
                System.out.println("Ok, you can add it later");
            }

            confirmed = getConfirmation(newEmployee);
        } while (!confirmed);
        newEmployee.addSelfToList();
    }


    //EFFECTS: returns if the info inputed by the user is correct
    private static boolean getConfirmation(Employee e) {
        System.out.println("Employees name: " + e.getName());
        System.out.println("Employees job: " + e.getJob());
        System.out.print("Please Confirm (Yes/No): ");
        String confirmation = scanner.nextLine();
        return confirmation.equalsIgnoreCase("Yes");
    }

    //REQUIRES: it's called on an employee
    //EFFECTS: Fils out the weeklyAvailability according to the user
    public static void updateAvailability(Employee e) {

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : daysOfWeek) {
            System.out.println("Enter availability for " + day + ": ");
            System.out.print("Start Time (HH:MM): ");
            LocalTime startTime = LocalTime.parse(ui.UiHandler.scanner.nextLine());
            System.out.print("End Time (HH:MM): ");
            LocalTime endTime = LocalTime.parse(ui.UiHandler.scanner.nextLine());
            e.getWeeklyAvailability().add(new DailyAvailability(day, startTime, endTime));
        }

    }

    //REQUIRES: EmployeeList is not empty
    //EFFECTS: Prints all the employee names in employeeList to the console
    public static void printAllEmployeeNames() {
        System.out.println("List of all employee names:");
        for (Employee e : EmployeeList.getInstance().getEmployeeList()) {
            System.out.println(e.getName());
        }
    }


    //MODIFIES: storeHours
    //EFFECTS: Adds store hours for each day of the week to the list
    public static void updateStoreHours() {

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : daysOfWeek) {
            System.out.println("Enter store hours for " + day + ": ");
            System.out.print("Opening Time (HH:MM): ");
            LocalTime openingTime = LocalTime.parse(scanner.nextLine());
            System.out.print("Closing Time (HH:MM): ");
            LocalTime closingTime = LocalTime.parse(scanner.nextLine());
            List<DailyAvailability> storeHours = Store.getInstance().getStoreHours();
            storeHours.add(new DailyAvailability(day, openingTime, closingTime));
        }

    }

    //REQUIRES: that storeHours is not empty
    //EFFECTS: prints the list of Operational hours to the console
    public static void printOperationalHours() {
        for (DailyAvailability d: Store.getInstance().getStoreHours()) {
            System.out.println(d);
        }
    }

    //REQUIRES: StoreHours is not empty
    //EFFECTS: Adds an arbitrary number of EmployeeNeeds to employeeNeeds
    public static void updateEmployeeNeeds() {
        System.out.println("Updating scheduling needs...");

        for (DailyAvailability day : Store.getInstance().getStoreHours()) {
            System.out.println("Enter employee needs for " + day.getDay() + ": ");
            boolean moreNeeds = true;

            while (moreNeeds) {
                System.out.print("Start Time (HH:MM): ");
                LocalTime startTime = LocalTime.parse(scanner.nextLine().trim());
                System.out.print("End Time (HH:MM): ");
                LocalTime endTime = LocalTime.parse(scanner.nextLine().trim());
                System.out.print("Number of employees needed: ");
                int numberOfEmployees = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                ArrayList<EmployeeNeeds> employeeNeeds = Store.getInstance().getAllEmployeeNeeds();

                employeeNeeds.add(new EmployeeNeeds(day.getDay(), startTime, endTime, numberOfEmployees));

                System.out.print("Are there more time slots for this day? (yes/no): ");
                moreNeeds = scanner.nextLine().trim().equalsIgnoreCase("yes");
            }
        }
    }

    //REQUIRES: EmployeeNeeds is not Empty
    //EFFECTS: prints all elements of EmployeeNeeds to the console
    public static void printEmployeeNeeds() {
        System.out.println("Getting scheduling needs...");
        System.out.println("Employee Needs:");
        for (EmployeeNeeds need : Store.getInstance().getAllEmployeeNeeds()) {
            System.out.println(need);
        }
    }

    public void printAllStoreAttributes(Store s) {
        this.printOperationalHours();
        this.printEmployeeNeeds();
        List<Employee> employeeList = s.getEmployeeList().getEmployeeList();
        for (Employee e : employeeList) {
            System.out.println(e.getName());
            printEmployeeAvailability(e);
        }
    }

}

