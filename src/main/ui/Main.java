package ui;

import javax.swing.SwingUtilities;

// Opens panel up
public class Main {
    
    // EFFECTS: creates new TrackerUI instance, using Java Swing library
    public static void main(String[] args) throws Exception {
        // No Longer Required:
        // new Tracker(); 

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TrackerUI main = new TrackerUI();
                main.startMenu();
            }
        });
    }
}
