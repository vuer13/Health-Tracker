package ui;

import javax.swing.SwingUtilities;

// Opens panel up, beginning of program
public class Main {
    
    // EFFECTS: creates new TrackerUI instance, using Java Swing library
    public static void main(String[] args) throws Exception {
        // No Longer Required:
        // new Tracker(); 

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TrackerUI();
            }
        });
    }
}
