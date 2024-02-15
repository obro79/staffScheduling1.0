package model;

import model.DailyAvailability;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class OperationalHours {

    protected List<DailyAvailability> storeHours;

    public OperationalHours() {
        this.storeHours = new ArrayList<>();
    }

    public void updateStoreHours() {
        Scanner scanner = new Scanner(System.in); // Assuming you have a scanner instance

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : daysOfWeek) {
            System.out.println("Enter store hours for " + day + ": ");
            System.out.print("Opening Time (HH:MM): ");
            LocalTime openingTime = LocalTime.parse(scanner.nextLine());
            System.out.print("Closing Time (HH:MM): ");
            LocalTime closingTime = LocalTime.parse(scanner.nextLine());
            storeHours.add(new DailyAvailability(day, openingTime, closingTime));
        }

    }

    public void printStoreHours() {
        System.out.println("Store Hours:");
        for (DailyAvailability da : storeHours) {
            System.out.println(da);
        }
    }
}
