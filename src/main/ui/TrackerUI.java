package ui;

import javax.swing.JFrame;

// GUI for program
public class TrackerUI extends JFrame {

    private JFrame window;

    // MODIFIES: this
    // EFFECTS: Sets Up GUI
    public TrackerUI() {
        window = new JFrame();
        window.setTitle("Calorie Tracker");
        window.setSize(1000, 600);
        window.setLocationRelativeTo(null);

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showWindow();
    }

    // EFFECTS: shows window of GUI
    public void showWindow() {
        window.setVisible(true);
    }

    // EFFECTS: displays starting menu with option to load previous mode
    public void startMenu() {
        // TODO
    }

    // MODIFIES: calories
    // EFFECTS: creates menu allowing user to make calorie goal
    private void goalMenu() {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: creates new list of exercises
    private void loadNewExercises() {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: loads list of exercises
    private void loadExercises() {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: creates new list of food items from previously saved items
    private void loadNewFoods() {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: loads list of food items from previously saved items
    private void loadFoods() {
        // TODO
    }
}
