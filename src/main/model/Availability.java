package model;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Availability {
    protected int idealHoursPerWeek;
    protected WeeklyAvailability weeklyAvailability;
    protected Scanner scanner;

    public Availability() {
        this.weeklyAvailability = new WeeklyAvailability();
        this.scanner = new Scanner(System.in);
        this.idealHoursPerWeek = getIdealHoursPerWeekFromUser();
        this.weeklyAvailability.updateAvailability();
    }

    private int getIdealHoursPerWeekFromUser() {
        System.out.print("Enter ideal hours per week: ");
        return scanner.nextInt(); // Read the user input for ideal hours
    }

    public abstract void updateAvailability();
}



