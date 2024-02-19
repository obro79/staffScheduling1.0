package ui;

import model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UiHandler {
    public static final Scanner scanner = new Scanner(System.in);

    // i tried for like 2 hours to reduce this method to <25 best I got was 26
    // used enums and helpers and everything felix said it's ok for switch else

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
                case 5: getOperationalHours();
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


    private static void updateEmployeeAvailability() {
        System.out.println("Which Employee's Availability would you like to update? (Enter their Name): ");
        String employeeName = scanner.nextLine();
        for (Employee e : model.EmployeeList.getInstance().getEmployeeList()) {
            if (e.getName().equalsIgnoreCase(employeeName)) {
                System.out.println("Ok Let's update their availability.");
                updateAvailability();
            }
        }
        System.out.println("It looks like there is no employee with that name.");
    } // would you like to add one?


    private static void getEmployeeAvailability() {

        System.out.println("Which Employee's Availability would you like to get? (Enter their Name): ");
        scanner.nextLine();
        String employeeName = String.valueOf(scanner);
        for (Employee e : model.EmployeeList.getInstance().getEmployeeList()) {
            if (e.getName() == employeeName) {
                System.out.println("Ok here's their availability: ");
                updateAvailability();
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

    public static void printOptions() {
        System.out.println("What would you like to do?");
        System.out.println("(1) Add Employee");
        System.out.println("(2) Update Existing Employee Availability");
        System.out.println("(3) Get List of Employees");
        System.out.println("(4) Get Employee’s Availability");
        System.out.println("(5) Get Operational Hours"); // will swap option 5 and 6 later. that will make more sense
        System.out.println("(6) Update Operational Hours");
        System.out.println("(7) Update Scheduling Needs");
        System.out.println("(8) Get Scheduling Needs");
        System.out.println("(9) Exit");
        System.out.print("Select an option (1-9): ");
    }


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
                updateAvailability();
            } else {
                System.out.println("Ok, you can add it later");
            }

            confirmed = getConfirmation(newEmployee);
        } while (!confirmed);
        newEmployee.addSelfToList();
    }

    private static boolean getConfirmation(Employee e) {
        System.out.println("Employee's name: " + e.getName());
        System.out.println("Employee's job: " + e.getJob());
        System.out.print("Please Confirm (Yes/No): ");
        String confirmation = scanner.nextLine();
        return confirmation.equalsIgnoreCase("Yes");
    }


    public static void updateAvailability() {
        ArrayList<DailyAvailability> weeklyAvailability = new ArrayList<>();

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

    public static void printAllEmployeeNames() {
        System.out.println("List of all employee names:");
        for (Employee e : EmployeeList.getInstance().getEmployeeList()) {
            System.out.println(e.getName());
        }
    }

    // Operational Needs

    public static void updateStoreHours() {

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : daysOfWeek) {
            System.out.println("Enter store hours for " + day + ": ");
            System.out.print("Opening Time (HH:MM): ");
            LocalTime openingTime = LocalTime.parse(scanner.nextLine());
            System.out.print("Closing Time (HH:MM): ");
            LocalTime closingTime = LocalTime.parse(scanner.nextLine());
            List<DailyAvailability> storeHours = model.OperationalNeeds.getInstance().getStoreHours();
            storeHours.add(new DailyAvailability(day, openingTime, closingTime));
        }

    }

    public static void updateEmployeeNeeds() {
        System.out.println("Updating scheduling needs...");

        for (DailyAvailability day : model.OperationalNeeds.getInstance().getStoreHours()) {
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

                ArrayList<EmployeeNeeds> employeeNeeds = model.OperationalNeeds.getInstance().getAllEmployeeNeeds();

                employeeNeeds.add(new EmployeeNeeds(day.getDay(), startTime, endTime, numberOfEmployees));

                System.out.print("Are there more time slots for this day? (yes/no): ");
                moreNeeds = scanner.nextLine().trim().equalsIgnoreCase("yes");
            }
        }
    }


    private static void printEmployeeNeeds() {
        System.out.println("Getting scheduling needs...");
        System.out.println("Employee Needs:");
        for (EmployeeNeeds need : model.OperationalNeeds.getInstance().getAllEmployeeNeeds()) {
            System.out.println(need);
        }
    }

}

