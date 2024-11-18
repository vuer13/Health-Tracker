package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Calories;
import model.Exercise;
import model.FoodItems;
import model.ListExercise;
import model.ListOfFoodItems;
import persistance.JsonReader;
import persistance.JsonWriter;

// GUI for program
public class TrackerUI extends JFrame {

    private final JPanel mainPanel = new JPanel();
    private final CardLayout cl = new CardLayout();

    private ListOfFoodItems lofi;
    private ListExercise loe;
    private Calories cal;

    private static final String JSON_STORE_FOOD = "./data/foodItems.json";
    private static final String JSON_STORE_EX = "./data/exercises.json";
    private static final String JSON_STORE_CAL = "./data/cal.json";

    // For Loading Menu
    private JsonReader readerFood;
    private JsonReader readerEx;
    private JsonReader readerCal;

    // For Saving 
    private JsonWriter writerFood;
    private JsonWriter writerEx;
    private JsonWriter writerCal;

    // GENERAL:
    private final JButton backButton = new JButton("Back"); // Use for both adds and both removes

    // For Starting Menu
    private final JFrame frame = new JFrame();
    private final JPanel homePanel = new JPanel();
    private final JLabel labelHome = new JLabel("Enter your calorie goal: ");
    private final JTextField calGoalStart = new JTextField();

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
    private final JButton setGoalButtone = new JButton("Set Calorie Goal");
    private final LocalDate today = LocalDate.now();
    private final JLabel title = new JLabel("Please select an option: ");
    private final JLabel todayDate = new JLabel(
            "Today's Date: " + today.getMonthValue() + "-" + today.getDayOfMonth() + "-" +
                    today.getYear());
    private JLabel goal = new JLabel("");

    private DefaultListModel<FoodItems> lofiModel;
    private DefaultListModel<Exercise> loeModel;
    private JList<FoodItems> lofiJlist;
    private JList<Exercise> loeJlist;

    private JPanel listsPanel = new JPanel();

    // For Adding FoodItems Screen
    private final JPanel addFoodItemsPanel = new JPanel();
    private final JLabel addFoodName = new JLabel("Food Name: ");
    private final JLabel addCals = new JLabel("Calories: ");
    private final JLabel addGroup = new JLabel("Food Group: ");
    private final JButton addFood = new JButton("Add Food (+)");
    private final JButton clearFood = new JButton("Clear");
    private final JTextField addFoodNameField = new JTextField();
    private final JTextField addCalsField = new JTextField();
    private final JTextField addGroupField = new JTextField();

    // For Removing FoodItems Screen

    // For Adding Exercises Screen
    private final JPanel addExPanel = new JPanel();
    private final JLabel addExName = new JLabel("Exercise: ");
    private final JLabel calExName = new JLabel("Calories Burnt: ");
    private final JTextField exName = new JTextField();
    private final JTextField calName = new JTextField();
    private final JButton addExButton = new JButton("Add Exercise (+)");
    private final JButton clearEx = new JButton("Clear");

    // For Removing Exercises Screen

    // For Changing Calorie Goal Screen

    // MODIFIES: this
    // EFFECTS: Sets Up GUI
    public TrackerUI() {
        initializeUI();
        initializeData();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                saveTracker();
            }
        });

        loadTracker();
    }

    // MODIFIES: this
    // EFFECTS: initializes UI components
    private void initializeUI() {
        frame.add(mainPanel);
        frame.setTitle("Calorie Tracker");
        frame.setSize(2000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewAllEx();
        viewAllFoodItems();

        mainPanel.setLayout(cl);

        mainPanel.add(homePanel, "1");
        mainPanel.add(goalPanel, "2");
        mainPanel.add(mainScreenPanel, "3");
        mainPanel.add(addFoodItemsPanel, "4");
        mainPanel.add(addExPanel, "5");

        setHomePanel();
        setGoalPanel();
        setMainScreenPanel();
        setAddFoodPanel();
        setAddExPanel();
    }

    // MODIFIES: this
    // EFFECTS: Initizlizes calorie tracker data
    private void initializeData() {
        readerFood = new JsonReader(JSON_STORE_FOOD);
        readerEx = new JsonReader(JSON_STORE_EX);
        readerCal = new JsonReader(JSON_STORE_CAL);

        writerEx = new JsonWriter(JSON_STORE_EX);
        writerCal = new JsonWriter(JSON_STORE_CAL);
        writerFood = new JsonWriter(JSON_STORE_FOOD);
    }

    // MODIFIES: this
    // EFFECTS: displays starting menu with option to load previous mode
    public void setHomePanel() {
        homePanel.setLayout(null);
        homePanel.setBackground(new Color(0, 255, 51));

        calGoalStart.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        labelHome.setFont(new Font("Times New Roman", Font.PLAIN, 48));

        labelHome.setBounds(250, 0, 500, 200);
        calGoalStart.setBounds(250, 150, 500, 100);

        homePanel.add(labelHome);
        homePanel.add(calGoalStart);

        calGoalStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int goals = Integer.parseInt(calGoalStart.getText());
                cal = new Calories(goals);
                cl.show(mainPanel, "3");
                goal.setText("Your Calorie Goal: " + String.valueOf(cal.getCalorieGoal()));
            }
        });
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
        mainScreenPanel.setBackground(new Color(0, 255, 51));

        goal.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        title.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        todayDate.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        goal.setBounds(100, 40, 800, 100);
        todayDate.setBounds(700, 40, 600, 100);

        mainScreenPanel.add(listsPanel);
        listsPanel.setBounds(95, 175, 1250, 450);

        addMainButtons();

        addFoodButton.setBounds(100, 650, 150, 75);
        removeFoodButton.setBounds(300, 650, 150, 75);
        addExerciseButton.setBounds(500, 650, 150, 75);
        removeExerciseButton.setBounds(700, 650, 150, 75);
        setGoalButtone.setBounds(900, 650, 150, 75);
        homeButton.setBounds(1100, 650, 150, 75);
    }

    // MODIFIES: this
    // EFFECTS: Updates UI with most current food items 
    private void updateUIfoodItems() {
        lofiModel.clear();
        List<FoodItems> fis = lofi.getListOfFoodItems();
        for (FoodItems food : fis) {
            lofiModel.addElement(food);
        }
    }

    // MODIFIES: this
    // EFFECTS: Updates UI with most current exercises
    private void updateUIex() {
        loeModel.clear();
        List<Exercise> es = loe.getListExercise();
        for (Exercise e : es) {
            loeModel.addElement(e);
        }
    }

    // EFFECTS: Displays all food items that have been added
    private void viewAllFoodItems() {
        lofiModel = new DefaultListModel<>();
        lofiJlist = new JList<>(lofiModel);
        lofiJlist.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JScrollPane scroll = new JScrollPane(lofiJlist);
        scroll.setPreferredSize(new Dimension(620, 440));
        listsPanel.add(scroll, BorderLayout.CENTER);
    }

    // EFFECTS: Displays all exercises that have been added
    private void viewAllEx() {
        loeModel = new DefaultListModel<>();
        loeJlist = new JList<>(loeModel);
        listsPanel.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JScrollPane scroll = new JScrollPane(loeJlist);
        scroll.setPreferredSize(new Dimension(620, 440));
        listsPanel.add(scroll, BorderLayout.CENTER);
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
        mainScreenPanel.add(goal);
    }

    // EFFECTS: sets add food items panel
    private void setAddFoodPanel() {
        addFoodItemsPanel.setLayout(null);

        addFoodName.setBounds(30, 10, 100, 100);
        addFoodNameField.setBounds(180, 50, 100, 20);
        addCals.setBounds(30, 100, 100, 100);
        addCalsField.setBounds(180, 150, 100, 20);
        addGroup.setBounds(30, 200, 100, 100);
        addGroupField.setBounds(180, 250, 100, 20);

        addFood.setBounds(150, 450, 100, 75);
        clearFood.setBounds(250, 450, 100, 75);
        backButton.setBounds(350, 450, 100, 75);

        addFoodItemsPanel.add(addFoodName);
        addFoodItemsPanel.add(addFoodNameField);
        addFoodItemsPanel.add(addCals);
        addFoodItemsPanel.add(addCalsField);
        addFoodItemsPanel.add(addGroup);
        addFoodItemsPanel.add(addGroupField);
        addFoodItemsPanel.add(addFood);
        addFoodItemsPanel.add(clearFood);
        addFoodItemsPanel.add(backButton);
    }

    // EFFECTS: sets remove food items panel
    private void setRemoveFoodPanel() {
        // TODO
    }

    // EFFECTS: sets add exercise panel
    private void setAddExPanel() {
        addExPanel.setLayout(null);

        addExName.setBounds(30, 10, 100, 100);
        exName.setBounds(180, 50, 100, 20);
        calExName.setBounds(30, 100, 100, 100);
        calName.setBounds(180, 150, 100, 20);

        addExButton.setBounds(150, 450, 100, 75);
        clearEx.setBounds(250, 450, 100, 75);

        addExPanel.add(addExName);
        addExPanel.add(exName);
        addExPanel.add(calExName);
        addExPanel.add(calName);
        addExPanel.add(addExButton);
        addExPanel.add(clearEx);
    }

    // EFFECTS: sets remove exercise panel
    private void setRemoveExPanel() {
        // TODO
    }

    // EFFECTS: shows both exercises and food items lists and statistics
    private void setListsAndStatistics() {
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

    // EFFECTS: pops window open, giving user option to load previously saved data
    private void loadTracker() {
        int reponse = JOptionPane.showConfirmDialog(null,
                "Would you like to load your previously saved list of food items and exercises?",
                "Load Calorie Tracker", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (reponse == JOptionPane.YES_OPTION) {
            try {
                lofi = readerFood.readFootItems();
                loe = readerEx.readExercise();
                cal = readerCal.readCalories();
                updateUIfoodItems();
                updateUIex();
                goal.setText("Your Calorie Goal: " + String.valueOf(cal.getCalorieGoal())); // put apart of update
                cl.show(mainPanel, "3");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error: Unable to load tracker", "Load Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            createNewInstances();
            updateUIfoodItems();
            updateUIex();
            cl.show(mainPanel, "1");
        }
        frame.setVisible(true);
    }

    // EFFECTS: creates new instances of objected
    private void createNewInstances() {
        lofi = new ListOfFoodItems();
        loe = new ListExercise();
        cal = new Calories(0);
    }

    // EFFECTS: pops window open, giving user option to save current data
    private void saveTracker() {
        int reponse = JOptionPane.showConfirmDialog(null,
        "Would you like to save your tracker before closing?",
        "Save Calorie Tracker", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (reponse == JOptionPane.YES_OPTION) {
            try {
                open();
                write();
                close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error: Unable to save tracker", "Save Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // EFFECTS: Opens all data files
    private void open() throws FileNotFoundException {
        writerEx.open();
        writerCal.open();
        writerFood.open();
    }

    // EFFECTS: Writes data into date files
    private void write() {
        writerEx.writeExercise(loe);
        writerCal.writeCalorie(cal);
        writerFood.writeFood(lofi);
    }

    // EFFECTS: Closes data files
    private void close() {
        writerEx.close();
        writerCal.close();
        writerFood.close();
    }
}
