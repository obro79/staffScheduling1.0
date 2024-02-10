package ui;

import model.Employee;

import model.TimePeriod;

import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello Manager! Would you like to add another Employee? Enter Yes or No: ");
        String answer = scanner.nextLine();

        if (answer.equals("Yes")) {
            System.out.println("Ok let's start with their name. Enter their name: ");
            String name = scanner.nextLine();
            System.out.println("Thanks, now enter their job.");
            String job = scanner.nextLine();
            System.out.println("Thanks, now enter their availability: ");
            new Employee(name, job);

            Map<String, TimePeriod> availability = new HashMap<>(); // Initialize once, before the loop
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

            for (String day : days) {
                System.out.println(day + " Start: ");
                String start = scanner.nextLine(); // Get start time
                System.out.println(day + " End: ");
                String end = scanner.nextLine(); // Get end time

                availability.put(day, new TimePeriod(start, end));
            }
        } else {
            System.out.println("Ok");


        }

    }
}