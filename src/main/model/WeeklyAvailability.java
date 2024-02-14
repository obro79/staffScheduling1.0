package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeeklyAvailability extends Availability {

    private List<DailyAvailability> weeklyDailyAvailability;

    public WeeklyAvailability() {
        this.weeklyDailyAvailability = new ArrayList<>();
    }

    public void updateAvailability() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to update this employee's Availability? (Yes/No): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Yes")) {
            String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            for (String day : daysOfWeek) {
                System.out.println("Enter availability for " + day + ": ");
                System.out.print("Start Time (HH:MM): ");
                LocalTime startTime = LocalTime.parse(scanner.nextLine());

                System.out.print("End Time (HH:MM): ");
                LocalTime endTime = LocalTime.parse(scanner.nextLine());

                addAvailability(new DailyAvailability(day, startTime, endTime));
            }
            confirmWeeklyAvailability(scanner);
        }
    }

    private void addAvailability(DailyAvailability dailyAvailability) {
        weeklyDailyAvailability.add(dailyAvailability);
    }

    private void confirmWeeklyAvailability(Scanner scanner) {
        printWeeklyAvailability();
        System.out.println("Is it all correct? (Yes/No): ");

        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("No")) {
            System.out.println("Please update the availability as needed.");
        } else {
            System.out.println("Availability confirmed.");
        }
    }

    public void printWeeklyAvailability() {
        for (DailyAvailability a : weeklyDailyAvailability) {
            System.out.println(a);
        }
    }

    // Remaining methods...
}
