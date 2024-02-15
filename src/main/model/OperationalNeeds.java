
package model;

import model.EmployeeNeeds;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OperationalNeeds {
    private static OperationalNeeds instance;
    protected List<DailyAvailability> storeHours;
    private ArrayList<EmployeeNeeds> allEmployeeNeeds;


    private OperationalNeeds() {
        this.storeHours = new ArrayList<>();
        this.allEmployeeNeeds = new ArrayList<>();
    }

    public static synchronized OperationalNeeds getInstance() {
        if (instance == null) {
            instance = new OperationalNeeds();
        }
        return instance;
    }

    public void updateStoreHours() {
        Scanner scanner = new Scanner(System.in); // Consider managing scanner externally for better resource management

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : daysOfWeek) {
            System.out.println("Enter store hours for " + day + ": ");
            System.out.print("Opening Time (HH:MM): ");
            LocalTime openingTime = LocalTime.parse(scanner.nextLine());
            System.out.print("Closing Time (HH:MM): ");
            LocalTime closingTime = LocalTime.parse(scanner.nextLine());
            storeHours.add(new DailyAvailability(day, openingTime, closingTime));
        }

        // No need to close the scanner here if it's used elsewhere in your application
    }

    public void printStoreHours() {
        System.out.println("Store Hours:");
        for (DailyAvailability da : storeHours) {
            System.out.println(da);
        }
    }

    public List<DailyAvailability> getStoreHours() {
        return storeHours;
    }

    public void updateEmployeeNeeds() {
        Scanner scanner = new Scanner(System.in); // Assume scanner is passed or globally accessible

        for (DailyAvailability day : this.storeHours) {
            System.out.println("Enter employee needs for " + day.getDay() + ": ");
            boolean moreNeeds = true;

            while (moreNeeds) {
                System.out.print("Start Time (HH:MM): ");
                LocalTime startTime = LocalTime.parse(scanner.nextLine());
                System.out.print("End Time (HH:MM): ");
                LocalTime endTime = LocalTime.parse(scanner.nextLine());
                System.out.print("Number of employees needed: ");
                int numberOfEmployees = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                this.allEmployeeNeeds.add(new EmployeeNeeds(day.getDay(), startTime, endTime, numberOfEmployees));

                System.out.print("Are there more time slots for this day? (yes/no): ");
                moreNeeds = scanner.nextLine().trim().equalsIgnoreCase("yes");
            }
        }
    }

    public void printEmployeeNeeds() {
        System.out.println("Employee Needs:");
        for (EmployeeNeeds need : this.allEmployeeNeeds) {
            System.out.println(need);
        }
    }

}

