package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private final JButton b1 = new JButton("Load Previously Saved State");
    private final JButton b2 = new JButton("Create New Tracker");
    private final JLabel labelHome = new JLabel("The Calorie Tracker");

    // For Goal Menu
    private final JTextField calGoal = new JTextField();
    private final JPanel goalPanel = new JPanel();
    private final JLabel labelGoal = new JLabel("Enter your calorie goal: ");

    // For Main Screen

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
        cl.show(mainPanel, "1");

        setHomePanel();
        goalMenu();

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
        b2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        labelHome.setFont(new Font("Times New Roman", Font.PLAIN, 48));

        labelHome.setBounds(300, 0, 500, 200);
        b1.setBounds(300, 150, 400, 100);
        b2.setBounds(300, 300, 400, 100);

        homePanel.add(labelHome);
        homePanel.add(b1);
        homePanel.add(b2);
    }

    // EFFECTS: creates menu allowing user to make calorie goal
    private void goalMenu() {
        goalPanel.setLayout(null);

        labelGoal.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        labelHome.setBounds(300, 0, 500, 200);

        calGoal.setBounds(300, 150, 400, 100);
        calGoal.setFont(new Font("Times New Roman", Font.BOLD, 24));

        goalPanel.add(labelGoal);
        goalPanel.add(calGoal);
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

    // MODIFIES: calories
    // EFFECTS: sets new calorie goal
    private void setCals() {
        // TODO
    }
}
