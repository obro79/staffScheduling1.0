package model;

import java.time.LocalTime;
import java.util.Scanner;

public class Availability extends WeeklyAvailability {


    public Availability() {

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to update this employee's information? (Yes/No): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Yes")) {
            for (String day : daysOfWeek) {
                System.out.println("Enter availability for " + day + ": ");
                System.out.print("Start Time (HH:MM): ");
                String startTimeStr = scanner.nextLine();
                LocalTime startTime = LocalTime.parse(startTimeStr);

                System.out.print("End Time (HH:MM): ");
                String endTimeStr = scanner.nextLine();
                LocalTime endTime = LocalTime.parse(endTimeStr);

                this.day = day;
                this.startTime = startTime;
                this.endTime = endTime;
                this.addToWeeklyAvailability();

            }
            this.confirmWeeklyAvailability();
        }
    }

    public void addToWeeklyAvailability() {
        this.getWeeklyAvailability().add(this);

    }

    public void confirmWeeklyAvailability() {
        Scanner scanner = new Scanner(System.in);

        this.printWeeklyAvailability();
        System.out.println("Is it all correct? (Yes/No): ");

        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("No")) {
            System.out.println("You should have done a better job.");
        }
        System.out.println("Ok. You must be so sure of your work.");
    }


    // Getters and Setters
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return day + ": " + startTime + " to " + endTime;
    }

}


