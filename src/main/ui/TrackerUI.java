package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// GUI for program
public class TrackerUI extends JFrame {

    private final JPanel mainPanel = new JPanel();
    private final CardLayout cl = new CardLayout();

    // For Starting Menu
    private final JFrame frame = new JFrame();;
    private final JPanel homePanel = new JPanel();
    private final JButton b1 = new JButton("Click Here To Get Started!");
    private final JLabel labelHome = new JLabel("The Calorie Tracker");

    // For Goal Menu
    private final JTextField calGoal = new JTextField();
    private final JPanel goalPanel = new JPanel();
    private final JLabel labelGoal = new JLabel("Enter your calorie goal: ");

    // For Main Screen
    private final JPanel mainScreenPanel = new JPanel();
    private final JButton addFoodButton = new JButton("Add Food (+)");
    private final JButton removeFoodButton = new JButton("Remove Food (-)");
    private final JButton addExerciseButton = new JButton("Add Exercise (+)");
    private final JButton removeExerciseButton = new JButton("Remove Exercise (-)");
    private final JButton homeButton = new JButton("Home");
    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");
    private final JButton setGoalButtone = new JButton("Set Calorie Goal");
    private final LocalDate today = LocalDate.now();
    private final JLabel title = new JLabel("Please select an option");
    private final JLabel todayDate = new JLabel("Today's Date: " + today);

    // For Adding FoodItems Screen

    // For Removing FoodItems Screen

    // For Adding Exercises Screen

    // For Removing Exercises Screen

    // For Changing Calorie Goal Screen

    // MODIFIES: this
    // EFFECTS: Sets Up GUI
    public TrackerUI() {
        mainPanel.setLayout(cl);

        mainPanel.add(homePanel, "1");
        mainPanel.add(goalPanel, "2");
        mainPanel.add(mainScreenPanel, "3");
        cl.show(mainPanel, "1");

        setHomePanel();
        setGoalPanel();
        setMainScreenPanel();

        frame.add(mainPanel);
        frame.setTitle("Calorie Tracker");
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // EFFECTS: displays starting menu with option to load previous mode
    public void setHomePanel() {
        homePanel.setLayout(null);
        homePanel.setBackground(new Color(0, 255, 51));

        b1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        labelHome.setFont(new Font("Times New Roman", Font.PLAIN, 48));

        labelHome.setBounds(300, 0, 500, 200);
        b1.setBounds(250, 150, 500, 100);

        homePanel.add(labelHome);
        homePanel.add(b1);
    }

    // EFFECTS: creates menu allowing user to make calorie goal
    private void setGoalPanel() {
        goalPanel.setLayout(null);

        labelGoal.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        labelHome.setBounds(300, 0, 500, 200);

        calGoal.setBounds(300, 150, 400, 100);
        calGoal.setFont(new Font("Times New Roman", Font.BOLD, 24));

        goalPanel.add(labelGoal);
        goalPanel.add(calGoal);
    }

    // EFFECTS: displays main menu 
    private void setMainScreenPanel() {
        mainScreenPanel.setLayout(null);
        homePanel.setBackground(new Color(0, 255, 51));

        title.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        todayDate.setFont(new Font("Times New Roman", Font.PLAIN, 48));

        title.setBounds(300, 150, 500, 200);
        todayDate.setBounds(300, 0, 500, 200);
        addFoodButton.setBounds(0, 300, 100, 75);
        removeFoodButton.setBounds(125, 300, 100, 75);
        addExerciseButton.setBounds(250, 300, 100, 75);
        removeExerciseButton.setBounds(375, 300, 100, 75);
        setGoalButtone.setBounds(125, 450, 100, 75);
        homeButton.setBounds(0, 450, 100, 75);
        saveButton.setBounds(250, 450, 100, 75);
        loadButton.setBounds(375, 450, 100, 75);

        addMainButtons();
    }

    // EFFECTS: adds buttons to mainScreenPanel
    private void addMainButtons() {
        mainScreenPanel.add(title);
        mainScreenPanel.add(todayDate);
        mainScreenPanel.add(addFoodButton);
        mainScreenPanel.add(removeFoodButton);
        mainScreenPanel.add(addExerciseButton);
        mainScreenPanel.add(removeExerciseButton);
        mainScreenPanel.add(setGoalButtone);
        mainScreenPanel.add(homeButton);
        mainScreenPanel.add(saveButton);
        mainScreenPanel.add(loadButton);
    }

    // EFFECTS: sets add food panel
    private void setAddFoodPanel() {
        // TODO
    }

    // EFFECTS: sets remove food panel
    private void setRemoveFoodPanel() {
        // TODO
    }

    // EFFECTS: sets add exercise panel
    private void setAddExPanel() {
        // TODO
    }
    
    // EFFECTS: sets remove exercise panel
    private void setRemoveExPanel() {
        // TODO
    }

    // EFFECTS: sets new calorie goal
    private void setCalsPanel() {
        // TODO
    }

    // EFFECTS: responsible for performing all the button actions
    private void actionPerformed() {
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
