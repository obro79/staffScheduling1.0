package ui;

import model.EmployeeList;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Store;
import model.Employee;
import model.EmployeeList;
import ui.gui.GUI;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


// this class Handles all the user inputs.
public class StoreApp {

    private static final String JSON_STORE = "./data/Store.json";
    private Store thisStore;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private UiHandler uiHandler;
    private JSONObject jsonObject;


    public static final Scanner scanner = new Scanner(System.in);

    //EFFECTS: creates a new instance of a store with a UiHandler, thisStore, jsonWriter, jsonReader, jsonObject
    public StoreApp() throws FileNotFoundException {
        this.uiHandler = new UiHandler();
        this.thisStore = new Store();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonObject = new JSONObject();
    }


    @SuppressWarnings("methodlength")

//    //EFFECTS: will execute one of the cases inputted by the user
//    public void runEmployeeManagementSystem() throws FileNotFoundException {
//
//        try {
//            int option;
//            do {
//                printOptions();
//                option = scanner.nextInt();
//                scanner.nextLine();
//
//                switch (option) {
//                    case 1:
//                        this.uiHandler.printAllStoreAttributes(this.thisStore);
//                        break;
//                    case 2:
//                        saveAllStoreAttributes();
//                        break;
//                    case 3:
//                        loadAllStoreAttributes();
//                        break;
//                    case 4:
//                        this.uiHandler.addEmployee();
//                        break;
//                    case 5:
//                        this.uiHandler.updateEmployeeAvailability(this.thisStore);
//                        break;
//                    case 6:
//                        this.uiHandler.printAllEmployeeNames(this.thisStore);
//                        break;
//                    case 7:
//                        this.uiHandler.getEmployeeAvailability(this.thisStore);
//                        break;
//                    case 8:
//                        this.uiHandler.printOperationalHours(this.thisStore);
//                        break;
//                    case 9:
//                        this.uiHandler.updateStoreHours(this.thisStore);
//                        break;
//                    case 10: this.uiHandler.updateEmployeeNeeds(this.thisStore);
//                        break;
//                    case 11:
//                        this.uiHandler.printEmployeeNeeds(this.thisStore);
//                        break;
//                    case 12:
//                        quit();
//                        break;
//                    default:
//                        System.out.println("Invalid option. Please select a valid option.");
//                }
//            } while (option != 12); // Update condition to reflect new exit option
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            //
//        }
//    }

    //EFFECTS: prints all the options to the console
    public void printOptions() {
        System.out.println("Select from: ");
        System.out.println("(1) Get All Store Attributes");
        System.out.println("(2) Save All Store Attributes ");
        System.out.println("(3) Load All Store Attributes ");
        System.out.println("(4) Add an Employee ");
        System.out.println("(5) Update an Employee's Availability ");
        System.out.println("(6) Get All Employee Names");
        System.out.println("(7) Get an Employee's Availability");
        System.out.println("(8) Get Store Hours");
        System.out.println("(9) Update Store Hours");
        System.out.println("(10) Update Employee Needs");
        System.out.println("(11) Get Employee Needs");
        System.out.println("(12) Quit");

    }


    //EFFECTS: saves all the store attributes to a Json File
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

    //MODIFIES: store
    //EFFECTS: Makes a store with all the data that was previously saved to the Json file
    public void loadAllStoreAttributes() {
        try {
            // Use the read method of JsonReader to load the Store from the JSON file
            thisStore = jsonReader.read(); // Assuming read() returns a Store object
            System.out.println("Store loaded successfully from file.");
        } catch (IOException e) {
            System.out.println("Unable to load the store from file: " + e.getMessage());
            // Handle the exception, e.g., by logging it or informing the user
        }

    }

    //EFFECTS: Ends the execution of hte program and ensures the user has saved their work
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
