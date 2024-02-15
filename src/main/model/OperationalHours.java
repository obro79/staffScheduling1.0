
package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



import model.DailyAvailability;

public class OperationalHours {
    private static OperationalHours instance;
    protected List<DailyAvailability> storeHours;

    private OperationalHours() {
        this.storeHours = new ArrayList<>();
    }

    public static synchronized OperationalHours getInstance() {
        if (instance == null) {
            instance = new OperationalHours();
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
}
