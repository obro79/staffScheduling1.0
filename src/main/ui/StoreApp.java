package ui;

import model.EmployeeList;
import org.json.JSONObject;
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
    private JSONObject jsonObject;

    public static final Scanner scanner = new Scanner(System.in);


    public StoreApp() throws FileNotFoundException {
        this.uiHandler = new UiHandler();
        this.thisStore = new Store();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonObject = new JSONObject();
        runEmployeeManagementSystem();
    }


    @SuppressWarnings("methodlength")

    //EFFECTS: will execute one of the cases
    public void runEmployeeManagementSystem() throws FileNotFoundException {

        try {
            int option;
            do {
                printOptions();
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        this.uiHandler.printAllStoreAttributes(this.thisStore);
                        break;
                    case 2:
                        saveAllStoreAttributes();
                        break;
                    case 3:
                        loadAllStoreAttributes();
                        break;
                    case 4:
                        this.uiHandler.addEmployee();
                        break;
                    case 5:
                        this.uiHandler.updateEmployeeAvailability();
                        break;
                    case 6:
                        printAllEmployeeNames();
                        break;
                    case 7:
                        this.uiHandler.getEmployeeAvailability();
                        break;
                    case 8:
                        this.uiHandler.printOperationalHours();
                        break;
                    case 9:
                        this.uiHandler.updateStoreHours();
                        break;
                    case 10:
                        this.uiHandler.updateEmployeeNeeds();
                        break;
                    case 11:
                        this.uiHandler.printEmployeeNeeds();
                        break;
                    case 12:
                        quit();
                        break;
                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                }
            } while (option != 12); // Update condition to reflect new exit option
            scanner.close();
        } catch (FileNotFoundException e) {
            //
        }
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


    public void saveAllStoreAttributes() throws FileNotFoundException {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.thisStore);
            this.jsonWriter.close();
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("No file with that name was found");
            throw e; // Re-throwing the exception to signal that the save operation failed
        }
    }

    public void loadAllStoreAttributes() {
        this.jsonReader.parseStore(this.jsonObject); //stub //TODO
    }

    public void quit() throws FileNotFoundException {
        try {
            System.out.println("Would you like to save your current work? (Yes/No)");
            String answer = scanner.nextLine();
            if (answer.trim().equalsIgnoreCase("Yes")) {
                saveAllStoreAttributes();
            } else {
                System.out.println("Ok your current work won't be saved.");
            }
        } catch (FileNotFoundException e) {
            //dd
        }

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
