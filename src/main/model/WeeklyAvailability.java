package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WeeklyAvailability {

    private List<Availability> weeklyAvailability;
    protected String day;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public WeeklyAvailability() {
        this.weeklyAvailability = new ArrayList<>();
    }

    public void addAvailability(Availability availability) {
        weeklyAvailability.add(availability);
    }

    public void printWeeklyAvailability() {
        for (Availability availability : weeklyAvailability) {
            System.out.println(availability);
        }
    }

    // Method to get the availability for all days
    public List<Availability> getWeeklyAvailability() {
        return weeklyAvailability;
    }


    public List<Availability> getAvailabilityByDay(String day) {
        List<Availability> dayAvailability = new ArrayList<>();
        for (Availability availability : weeklyAvailability) {
            if (availability.getDay().equalsIgnoreCase(day)) {
                dayAvailability.add(availability);
            }
        }
        return dayAvailability;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Availability availability : weeklyAvailability) {
            sb.append(availability.toString()).append("\n");
        }
        return sb.toString();
    }
}

