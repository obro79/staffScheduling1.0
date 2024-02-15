package model;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeeklyAvailability {


    private List<DailyAvailability> weeklyDailyAvailability;

    public WeeklyAvailability() {
        this.weeklyDailyAvailability = new ArrayList<>();
    }

    public void updateAvailability() {
        // Assuming `scanner` is passed or available globally
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : daysOfWeek) {
            System.out.println("Enter availability for " + day + ": ");
            System.out.print("Start Time (HH:MM): ");
            LocalTime startTime = LocalTime.parse(ui.UiHandler.scanner.nextLine());
            System.out.print("End Time (HH:MM): ");
            LocalTime endTime = LocalTime.parse(ui.UiHandler.scanner.nextLine());
            weeklyDailyAvailability.add(new DailyAvailability(day, startTime, endTime));
        }

    }

}
