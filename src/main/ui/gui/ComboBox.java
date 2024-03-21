package ui.gui;
import javax.swing.JComboBox;


public class ComboBox {


    public static JComboBox<String> createDropdown(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        // Configure the combo box further if needed
        return comboBox;
    }

    public static String[] generateHalfHourTimes() {
        String[] times = new String[48]; // 24 hours * 2 slots per hour
        int hour;
        int minute;
        for (int i = 0; i < times.length; i++) {
            hour = i / 2; // There are 2 slots in each hour
            minute = (i % 2) * 30; // Alternates between 0 and 30 minutes
            times[i] = String.format("%02d:%02d", hour, minute);
        }
        return times;
    }

}
