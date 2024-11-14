package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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

        startMenu();

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showWindow();
    }

    // EFFECTS: shows window of GUI
    public void showWindow() {
        window.setVisible(true);
    }

    // EFFECTS: displays starting menu with option to load previous mode
    public void startMenu() {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel label = new JLabel("The Calorie Tracker");

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 2));

        JButton b1 = new JButton("Load Previously Saved State");
        JButton b2 = new JButton("Create New Tracker");
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        b2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        b1.setPreferredSize(new Dimension(400, 75));
        b2.setPreferredSize(new Dimension(400, 75));
        label.setFont(new Font("Times New Roman", Font.PLAIN, 48));

        b1.addActionListener(b1Listener());
        b2.addActionListener(b2Listener());

        panel1.add(label);
        panel2.add(b1);
        panel2.add(b2);

        window.add(panel1, BorderLayout.NORTH);
        window.add(panel2, BorderLayout.CENTER);
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
