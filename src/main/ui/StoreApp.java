package ui;

import model.EmployeeList;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Store;
import model.Employee;
import model.EmployeeList;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static ui.UiHandler.printAllEmployeeNames;
import static ui.UiHandler.printEmployeeAvailability;

public class StoreApp {

    private static final String JSON_STORE = "./data/Store.json";
    private Store thisStore;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private UiHandler uiHandler;

    public static final Scanner scanner = new Scanner(System.in);


    public StoreApp() throws FileNotFoundException {
        this.uiHandler = new UiHandler();
        this.thisStore = new Store();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        runEmployeeManagementSystem();
    }


    @SuppressWarnings("methodlength")

    //EFFECTS: will execute one of the cases
    public void runEmployeeManagementSystem() {

        int option;
        do {
            printOptions();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: this.uiHandler.printAllStoreAttributes(this.thisStore);
                    break;
                case 2: saveAllStoreAttributes();
                    break;
                case 3: loadAllStoreAttributes();
                    break;
                case 4: this.uiHandler.addEmployee();
                    break;
                case 5: this.uiHandler.updateEmployeeAvailability();
                    break;
                case 6: printAllEmployeeNames();
                    break;
                case 7: this.uiHandler.getEmployeeAvailability();
                    break;
                case 8: this.uiHandler.printOperationalHours();
                    break;
                case 9: this.uiHandler.updateStoreHours();
                    break;
                case 10: this.uiHandler.updateEmployeeNeeds();
                    break;
                case 11: this.uiHandler.printEmployeeNeeds();
                    break;
                case 12: System.out.println("Exiting...");
                    break;
                default: System.out.println("Invalid option. Please select a valid option.");
            }
        } while (option != 12); // Update condition to reflect new exit option
        scanner.close();
    }

    public void printOptions() {
        System.out.println("Select from: ");
        System.out.println("(1) Print All Store Attributes");
        System.out.println("(2) Save All Store Attributes ");
        System.out.println("(3) Load All Store Attributes ");
        System.out.println("(4) Add an Employee ");
        System.out.println("(5) Update Employee Availability ");
        System.out.println("(6) Get All Employee Names");
        System.out.println("(7) Get an Employee's Availability");
        System.out.println("(8) Get Store Hours");
        System.out.println("(9) Update Store Hours");
        System.out.println("(10) Update Employee Needs");
        System.out.println("(11) Print Employee Needs");
        System.out.println("(12) Quit");

    }



    public void saveAllStoreAttributes() {
        this.thisStore.getStoreHours();
    }

    public void loadAllStoreAttributes() {
        //stub
    }

    public void quit() {
        System.out.println("Would you like to save your current work? (Yes/No)");
        String answer = scanner.nextLine();
        if (answer.trim().equalsIgnoreCase("Yes")) {
            saveAllStoreAttributes();
        }
        System.out.println("Ok your current work won't be saved.");
    }

    public Store getThisStore() {
        return this.thisStore;
    }

    public JsonWriter getJsonWriter() {
        return this.jsonWriter;
    }

    public JsonReader getJsonReader() {
        return this.jsonReader;
    }

}
