package ui;

//import model.scheduling.Schedule;
import ui.gui.GUI;

import javax.swing.*;
import java.io.FileNotFoundException;


// this class runs StoreApp.

// 1 needs to be done before 2
// 1 needs to be done before 3,
// 6 needs to be done before 5
// 6 needs to be done before 8
// all inputs must be in the form that is specified.


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    StoreApp storeApp = new StoreApp();

//                    storeApp.loadAllStoreAttributes(); //delete later
//                    Schedule schedule = new Schedule(storeApp); //
//                    schedule.maximizeEmployeeHours(); //

                    GUI gui =  new GUI(storeApp);
                    gui.initializeUI();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}




