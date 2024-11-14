package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

// GUI for program
public class TrackerUI extends JFrame {

    private JFrame startWindow;
    private JPanel panel1;
    private JPanel panel2;

    // MODIFIES: this
    // EFFECTS: Sets Up GUI
    public TrackerUI() {
        startWindow = new JFrame();
        startWindow.setTitle("Calorie Tracker");
        startWindow.setSize(1000, 600);
        startWindow.setLocationRelativeTo(null);

        startMenu();

        startWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showWindow();
    }

    // EFFECTS: shows window of GUI
    public void showWindow() {
        startWindow.setVisible(true);
    }

    // EFFECTS: displays starting menu with option to load previous mode
    public void startMenu() {
        panel1 = new JPanel();
        panel2 = new JPanel();
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

        startWindow.add(panel1, BorderLayout.NORTH);
        startWindow.add(panel2, BorderLayout.CENTER);
    }

    // EFFECTS: adds functionality for b1 when button is clicked
    private ActionListener b1Listener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadExercises();
                loadFoods();
            }

        };
        return al;
    }

    // EFFECTS: adds functionality for b2 when button is clicked
    private ActionListener b2Listener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goalMenu();
                loadNewExercises();
                loadNewFoods();
            }

        };
        return al;
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
