package ui;



import model.DailyAvailability;
import model.Employee;
import model.WeeklyAvailability;

public class Main {
    public static void main(String[] args) {

        Employee newEmployee = new Employee();
        WeeklyAvailability newWeeklyAvailability = new WeeklyAvailability();
        newWeeklyAvailability.updateAvailability();
    }

}

