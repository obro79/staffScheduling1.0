package ui;

import ui.UiHandler;

import java.io.FileNotFoundException;



// this class runs StoreApp
public class Main {
    // 1 needs to be done before 2
    // 1 needs to be done before 3,
    // 6 needs to be done before 5
    // 6 needs to be done before 8
    // all inputs must be in the form that is specified.


    public static void main(String[] args) throws FileNotFoundException {
        try {
            new StoreApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}


