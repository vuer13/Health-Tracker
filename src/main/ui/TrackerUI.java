package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Calories;
import model.Exercise;
import model.FoodGroup;
import model.FoodItems;
import model.ListExercise;
import model.ListOfFoodItems;
import persistance.JsonReader;
import persistance.JsonWriter;

// GUI for program
public class TrackerUI extends JFrame implements ListSelectionListener {

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
    private final JButton backButtonFood = new JButton("Back");
    private final JButton backButtonEx = new JButton("Back");

    // For Starting Menu
    private final JFrame frame = new JFrame();
    private final JPanel homePanel = new JPanel();
    private final JLabel labelHome = new JLabel("Enter your calorie goal: ");
    private final JTextField calGoalStart = new JTextField();

    // For Main Screen
    private final JPanel mainScreenPanel = new JPanel();
    private final JButton addFoodButton = new JButton("Add Food (+)");
    private final JButton removeFoodButton = new JButton("Remove Food (-)");
    private final JButton addExerciseButton = new JButton("Add Exercise (+)");
    private final JButton removeExerciseButton = new JButton("Remove Exercise (-)");
    private final JButton setGoalButtone = new JButton("Set Calorie Goal");
    private final LocalDate today = LocalDate.now();
    private final JLabel title = new JLabel("Please select an option: ");
    private final JLabel todayDate = new JLabel(
            "Today's Date: " + today.getMonthValue() + "-" + today.getDayOfMonth() + "-"
                    + today.getYear());
    private JLabel goal = new JLabel("");
    private final JButton calStatsButton = new JButton("Calorie Statistics");
    private final JButton foodStatsButton = new JButton("Food Statistics");
    private final JButton exStatsButton = new JButton("Exercise Statistics");

    String[] foodGroups = { "All", "Fruit", "Vegetable", "Grain", "Protein", "Dairy" };
    private final JComboBox<?> foodGroupsBox = new JComboBox<>(foodGroups);

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

        viewAllFoodItems();
        viewAllEx();

        mainPanel.setLayout(cl);

        mainPanel.add(homePanel, "1");
        mainPanel.add(mainScreenPanel, "3");
        mainPanel.add(addFoodItemsPanel, "4");
        mainPanel.add(addExPanel, "5");

        setHomePanel();
        setMainScreenPanel();
        setAddFoodPanel();
        setAddExPanel();
        actionPerformed();
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
    // EFFECTS: displays starting menu allowing user to set calorie goal
    public void setHomePanel() {
        homePanel.setLayout(null);
        homePanel.setBackground(new Color(92, 214, 121));

        calGoalStart.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        labelHome.setFont(new Font("Palatino", Font.PLAIN, 48));

        labelHome.setBounds(475, 200, 1000, 200);
        calGoalStart.setBounds(475, 400, 500, 100);

        homePanel.add(labelHome);
        homePanel.add(calGoalStart);

        calGoalStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int goals = Integer.parseInt(calGoalStart.getText());
                cal = new Calories(goals);
                cl.show(mainPanel, "3");
                goal.setText("Your Calorie Goal: " + String.valueOf(cal.getCalorieGoal()));
                calGoalStart.setText("");
            }
        });

        addCalPhotos();
    }

    // EFFECTS: Adds photos to the calorie goal menu
    private void addCalPhotos() {
        // TODO
    }

    // EFFECTS: displays main menu
    private void setMainScreenPanel() {
        mainScreenPanel.setLayout(null);
        mainScreenPanel.setBackground(new Color(92, 214, 121));

        goal.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        title.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        todayDate.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        goal.setBounds(100, 40, 800, 100);
        todayDate.setBounds(700, 40, 600, 100);

        mainScreenPanel.add(listsPanel);
        listsPanel.setBounds(95, 175, 1250, 410);

        addMainButtons();

        addFoodButton.setBounds(125, 650, 150, 75);
        removeFoodButton.setBounds(275, 650, 150, 75);
        addExerciseButton.setBounds(425, 650, 150, 75);
        removeExerciseButton.setBounds(575, 650, 150, 75);
        setGoalButtone.setBounds(725, 650, 150, 75);
        calStatsButton.setBounds(875, 650, 150, 75);
        foodStatsButton.setBounds(1025, 650, 150, 75);
        exStatsButton.setBounds(1175, 650, 150, 75);
        foodGroupsBox.setBounds(125, 600, 150, 75);
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

    // EFFECTS: updates both ex and fooditems UI
    private void updateUI() {
        updateUIfoodItems();
        updateUIex();
    }

    // EFFECTS: Displays all food items that have been added
    private void viewAllFoodItems() {
        lofiModel = new DefaultListModel<>();
        lofiJlist = new JList<>(lofiModel);
        lofiJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lofiJlist.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lofiJlist.addListSelectionListener(this);
        lofiJlist.setCellRenderer(new FoodItemsRenderer());

        JScrollPane scroll = new JScrollPane(lofiJlist);
        scroll.setPreferredSize(new Dimension(620, 400));
        listsPanel.add(scroll, BorderLayout.CENTER);
    }

    // EFFECTS: Displays all exercises that have been added
    private void viewAllEx() {
        loeModel = new DefaultListModel<>();
        loeJlist = new JList<>(loeModel);
        loeJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loeJlist.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        loeJlist.addListSelectionListener(this);
        loeJlist.setCellRenderer(new ExerciseRenderer());

        JScrollPane scroll = new JScrollPane(loeJlist);
        scroll.setPreferredSize(new Dimension(620, 400));
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
        mainScreenPanel.add(goal);
        mainScreenPanel.add(calStatsButton);
        mainScreenPanel.add(foodStatsButton);
        mainScreenPanel.add(exStatsButton);
        mainScreenPanel.add(foodGroupsBox);
    }

    // EFFECTS: sets add food items panel
    private void setAddFoodPanel() {
        addFoodItemsPanel.setLayout(null);
        addFoodItemsPanel.setBackground(new Color(230, 133, 154));

        setAddFoodItemsFonts();

        addFoodName.setBounds(110, 150, 300, 80);
        addFoodNameField.setBounds(350, 150, 300, 80);
        addCals.setBounds(110, 300, 300, 80);
        addCalsField.setBounds(350, 300, 300, 80);
        addGroup.setBounds(110, 450, 300, 80);
        addGroupField.setBounds(350, 450, 300, 80);

        addFood.setBounds(180, 600, 120, 80);
        clearFood.setBounds(310, 600, 120, 80);
        backButtonFood.setBounds(440, 600, 120, 80);

        addFoodItemsPanel.add(addFoodName);
        addFoodItemsPanel.add(addFoodNameField);
        addFoodItemsPanel.add(addCals);
        addFoodItemsPanel.add(addCalsField);
        addFoodItemsPanel.add(addGroup);
        addFoodItemsPanel.add(addGroupField);
        addFoodItemsPanel.add(addFood);
        addFoodItemsPanel.add(clearFood);
        addFoodItemsPanel.add(backButtonFood);

        addImagesToFoodPanel();
    }

    // MODIFIES: this
    // EFFECTS: changes fonts of panels and labels
    private void setAddFoodItemsFonts() {
        addFoodName.setFont(new Font("Times New Roman", Font.PLAIN, 38));
        addFoodNameField.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        addCals.setFont(new Font("Times New Roman", Font.PLAIN, 38));
        addCalsField.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        addGroup.setFont(new Font("Times New Roman", Font.PLAIN, 38));
        addGroupField.setFont(new Font("Times New Roman", Font.PLAIN, 36));
    }

    // adds images to the add food panel
    private void addImagesToFoodPanel() {
        try {
            BufferedImage newPicture = ImageIO.read(new File("data/food1.png"));
            Image newImg = newPicture.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel picture = new JLabel(new ImageIcon(newImg));
            picture.setBounds(1000, 50, 300, 300);
            addFoodItemsPanel.add(picture);
        } catch (IOException e) {
            System.out.println("IOException caught, file not found");
        }
        try {
            BufferedImage newPicture = ImageIO.read(new File("data/food2.png"));
            Image newImg = newPicture.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel picture = new JLabel(new ImageIcon(newImg));
            picture.setBounds(800, 400, 300, 300);
            addFoodItemsPanel.add(picture);
        } catch (IOException e) {
            System.out.println("IOException caught, file not found");
        }
    }

    // EFFECTS: removes food item from the list
    private void removeFoodItem() {
        int selected = lofiJlist.getSelectedIndex();
        if (selected != -1) {
            lofiModel.remove(selected);
            lofi.removeFood(lofi.getListOfFoodItems().get(selected));
        } else {
            JOptionPane.showMessageDialog(null, "Please select a food item to remove.",
                    "No Food Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: removes exercise from the list
    private void removeExercise() {
        int selected = loeJlist.getSelectedIndex();
        if (selected != -1) {
            loeModel.remove(selected);
            loe.removeExercise(loe.getListExercise().get(selected));
        } else {
            JOptionPane.showMessageDialog(null, "Please select a exercise to remove.",
                    "No Exercise Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: sets add exercise panel
    private void setAddExPanel() {
        addExPanel.setLayout(null);
        addExPanel.setBackground(new Color(79, 158, 219));

        addExName.setBounds(110, 200, 300, 80);
        exName.setBounds(350, 200, 300, 80);
        addExName.setFont(new Font("Times New Roman", Font.PLAIN, 38));
        exName.setFont(new Font("Times New Roman", Font.PLAIN, 36));

        calExName.setBounds(100, 400, 300, 80);
        calName.setBounds(350, 400, 300, 80);
        calExName.setFont(new Font("Times New Roman", Font.PLAIN, 38));
        calName.setFont(new Font("Times New Roman", Font.PLAIN, 36));

        addExButton.setBounds(180, 600, 120, 80);
        clearEx.setBounds(310, 600, 120, 80);
        backButtonEx.setBounds(440, 600, 120, 80);

        addExPanel.add(addExName);
        addExPanel.add(exName);
        addExPanel.add(calExName);
        addExPanel.add(calName);
        addExPanel.add(addExButton);
        addExPanel.add(clearEx);
        addExPanel.add(backButtonEx);

        addImagesToExPanel();
    }

    // EFFECTS: adds images to add ex panel
    private void addImagesToExPanel() {
        try {
            BufferedImage newPicture = ImageIO.read(new File("data/ex1.png"));
            Image newImg = newPicture.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel picture = new JLabel(new ImageIcon(newImg));
            picture.setBounds(1000, 50, 300, 300);
            addExPanel.add(picture);
        } catch (IOException e) {
            System.out.println("IOException caught, file not found");
        }
        try {
            BufferedImage newPicture = ImageIO.read(new File("data/ex2.png"));
            Image newImg = newPicture.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel picture = new JLabel(new ImageIcon(newImg));
            picture.setBounds(800, 400, 300, 300);
            addExPanel.add(picture);
        } catch (IOException e) {
            System.out.println("IOException caught, file not found");
        }
    }

    // EFFECTS: shows statistics of data inputted as a pop up window
    private void setStatistics() {
        int difference = cal.difference(lofi, loe);
        int goal = cal.getCalorieGoal();
        int needed = cal.neededAmount(lofi, loe);
        int over = cal.overAmount(lofi, loe);
        int totalLofi = lofi.totalNumOfCalories();
        int totalLoe = loe.getTotalCaloriesBurnt();

        String message = String.format(
                "Calorie Goal: %d\nCalories Required: %d\nCalories Over Goal: %d\nDifference in Calories Burned "
                        + "and Gained: %d\nTotal Food Calories: %d\nTotal Calories Burnt: %d",
                goal, needed, over, difference, totalLofi, totalLoe);
        JOptionPane.showMessageDialog(null, message, "Calories Statistics",
                JOptionPane.INFORMATION_MESSAGE,
                addImage("./data/cals.png", 150, 150));
    }

    // EFFECTS: shows food statistics
    private void setFoodStatistics() {
        int lofiSize = lofi.getListOfFoodItems().size();
        int lofiFruitCals = lofi.totalFruitCalories();
        int lofiVegetableCals = lofi.totalVegetableCalories();
        int lofiGrainCals = lofi.totalGrainCalories();
        int lofiDiaryCals = lofi.totalDairyCalories();
        int lofiProteinCals = lofi.totalProteinCalories();
        int lofiFruitSize = lofi.getFruitList().size();
        int lofiVegetableSize = lofi.getVegetableList().size();
        int lofiProteinSize = lofi.getProteinList().size();
        int lofiDiarySize = lofi.getDairyList().size();
        int lofiFGrainSize = lofi.getGrainList().size();
        int totalLofi = lofi.totalNumOfCalories();

        String message = String.format(
                "Number of foods eaten: %d\nCalories consumed: %d\nNumber of Fruits eaten: "
                        + "%d\nCalories of Fruit consumed: %d\nNumber of Vegetables eaten: "
                        + "%d\nCalories of Vegetables consumed: %d\nNumber of Proteins eaten: "
                        + "%d\nCalories of Proteins consumed: %d\nNumber of Grains eaten: %d\nCalories of Grains"
                        + " consumed: %d\nNumber of Dairy eaten: %d\nCalories of Dairy consumed: %d",
                lofiSize, totalLofi, lofiFruitSize, lofiFruitCals, lofiVegetableSize, lofiVegetableCals,
                lofiProteinSize, lofiProteinCals, lofiFGrainSize, lofiGrainCals, lofiDiarySize, lofiDiaryCals);
        JOptionPane.showMessageDialog(null, message, "Food Statistics",
                JOptionPane.INFORMATION_MESSAGE,
                addImage("./data/food3.png", 150, 150));
    }

    // EFFECTS: shows exercises statistics
    private void setExStatistics() {
        int loeSize = loe.getListExercise().size();
        int totalLoe = loe.getTotalCaloriesBurnt();

        String message = String.format(
                "Number of Exercises performed: %d\nTotal Calories Burnt: %d", loeSize, totalLoe);

        JOptionPane.showMessageDialog(null, message, "Exercise Statistics",
                JOptionPane.INFORMATION_MESSAGE,
                addImage("./data/weights.png", 150, 150));
    }

    // EFFECTS: responsible for performing all the button actions
    @SuppressWarnings("methodlength")
    private void actionPerformed() {
        addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, "4");
            }
        });

        addFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FoodItems fi;
                try {
                    fi = makeFoodItem();
                    lofi.addFood(fi);
                    lofiModel.addElement(fi);
                    clearFoodPanel();
                } catch (IllegalArgumentException i) {
                    JOptionPane.showMessageDialog(null, "Error: Invalid Inputs, Please Try Again", "Invalid",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        calStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStatistics();
            }
        });

        foodStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFoodStatistics();
            }
        });

        exStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setExStatistics();
            }
        });

        setGoalButtone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, "1");
            }
        });

        backButtonFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, "3");
            }
        });

        backButtonEx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, "3");
            }
        });

        clearFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFoodPanel();
            }
        });

        addExButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exercise ex;
                try {
                    ex = makeExercise();
                    loe.addExercise(ex);
                    loeModel.addElement(ex);
                    clearExPanel();
                } catch (IllegalArgumentException i) {
                    JOptionPane.showMessageDialog(null, "Error: Invalid Inputs, Please Try Again", "Invalid",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, "5");
            }
        });

        removeFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFoodItem();
                updateUIfoodItems();
            }
        });

        removeExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeExercise();
                updateUIex();
            }
        });

        foodGroupsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = foodGroupsBox.getSelectedIndex();
                if (selected == 0) {
                    updateUIfoodItems();
                } else if (selected == 1) {
                    updateUIfoodItemsFruit();
                } else if (selected == 2) {
                    updateUIfoodItemsVeg();
                } else if (selected == 3) {
                    updateUIfoodItemsGrain();
                } else if (selected == 4) {
                    updateUIfoodItemsProtein();
                } else if (selected == 5) {
                    updateUIfoodItemsDairy();
                }
            }
        });
    }

    // EFFECTS: shows only fruits
    private void updateUIfoodItemsFruit() {
        lofiModel.clear();
        List<FoodItems> fis = lofi.getFruitList();
        for (FoodItems food : fis) {
            lofiModel.addElement(food);
        }
    }

    // EFFECTS: shows only vegetables
    private void updateUIfoodItemsVeg() {
        lofiModel.clear();
        List<FoodItems> fis = lofi.getVegetableList();
        for (FoodItems food : fis) {
            lofiModel.addElement(food);
        }
    }

    // EFFECTS: shows only grains
    private void updateUIfoodItemsGrain() {
        lofiModel.clear();
        List<FoodItems> fis = lofi.getGrainList();
        for (FoodItems food : fis) {
            lofiModel.addElement(food);
        }
    }

    // EFFECTS: shows only proteins
    private void updateUIfoodItemsProtein() {
        lofiModel.clear();
        List<FoodItems> fis = lofi.getProteinList();
        for (FoodItems food : fis) {
            lofiModel.addElement(food);
        }
    }

    // EFFECTS: shows only dairy
    private void updateUIfoodItemsDairy() {
        lofiModel.clear();
        List<FoodItems> fis = lofi.getDairyList();
        for (FoodItems food : fis) {
            lofiModel.addElement(food);
        }
    }

    // EFFECTS: pops window open, giving user option to load previously saved data
    private void loadTracker() {
        int reponse = JOptionPane.showConfirmDialog(null,
                "Would you like to load your previously saved list of food items and exercises?",
                "Load Calorie Tracker", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                addImage("./data/loading.png", 150, 150));
        if (reponse == JOptionPane.YES_OPTION) {
            try {
                lofi = readerFood.readFootItems();
                loe = readerEx.readExercise();
                cal = readerCal.readCalories();
                updateUI();
                goal.setText("Your Calorie Goal: " + String.valueOf(cal.getCalorieGoal())); // put apart of update
                cl.show(mainPanel, "3");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error: Unable to load tracker", "Load Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            createNewInstances();
            updateUI();
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
                "Save Calorie Tracker", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                addImage("./data/save.png", 150, 150));
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

    // EFFECTS: constructs new food item with info from text fields
    private FoodItems makeFoodItem() {
        int cals;
        FoodGroup group;
        String name = addFoodNameField.getText();
        cals = Integer.parseInt(addCalsField.getText());
        group = FoodGroup.valueOf(addGroupField.getText().toUpperCase());

        FoodItems f = new FoodItems(name, cals, group);
        return f;
    }

    // MODIFIES: this
    // EFFECTS: sets all text fields to blank after adding an item
    private void clearFoodPanel() {
        addFoodNameField.setText("");
        addCalsField.setText("");
        addGroupField.setText("");
    }

    // EFFECTS: constructs new exercise with info from text fields
    private Exercise makeExercise() {
        int cals;
        String name = exName.getText();
        cals = Integer.parseInt(calName.getText());

        Exercise e = new Exercise(name, cals);
        return e;
    }

    // MODIFIES: this
    // EFFECTS: sets all text fields to blank after adding an exercise
    private void clearExPanel() {
        exName.setText("");
        calName.setText("");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // not required
    }

    // EFFECTS: creates new image instance
    private ImageIcon addImage(String file, int height, int width) {
        ImageIcon imageIcon = new ImageIcon(file);
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}