// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalArgumentException;

import model.Calories;
import model.Exercise;
import model.FoodGroup;
import model.FoodItems;
import model.ListExercise;
import model.ListOfFoodItems;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import org.json.JSONException;

// UI of the program
public class Tracker {

    private Scanner scanner;
    private boolean isProgramRunning;
    private ListOfFoodItems lofi;
    private ListExercise ex;
    private Calories cal;
    private JsonWriter writerex;
    private JsonWriter writerf;
    private JsonWriter writerc;
    private JsonReader readerex;
    private JsonReader readerf;
    private JsonReader readerc;
    private static final String JSON_FOOD = "./data/foodItems.json";
    private static final String JSON_EX = "./data/exercises.json";
    private static final String JSON_CAL = "./data/cal.json";

    // REQUIRES: input must be a integer > 0
    // EFFECTS: Initiates UI console base
    public Tracker() {
        isProgramRunning = true;
        lofi = new ListOfFoodItems();
        ex = new ListExercise();
        scanner = new Scanner(System.in);
        writerex = new JsonWriter(JSON_EX);
        writerf = new JsonWriter(JSON_FOOD);
        writerc = new JsonWriter(JSON_CAL);
        readerex = new JsonReader(JSON_EX);
        readerf = new JsonReader(JSON_FOOD);
        readerc = new JsonReader(JSON_CAL);

        createDivider();
        System.out.println("Welcome to the Calorie Tracker");
        handlesStartMenu();
    }

    // EFFECTS: displays and processes inputs for start of menu
    public void handlesStartMenu() {
        displayStartMenu();
        String input = this.scanner.nextLine();
        processStartMenu(input);
    }

    // EFFECTS: displays start menu
    public void displayStartMenu() {
        System.out.println("Please select an option");
        System.out.println("Start new program: c");
        System.out.println("Load previously saved program: l");
    }

    // EFFECTS: processes start menu's input
    public void processStartMenu(String input) {
        if (input.equals("l")) {
            try {
                loadLists();
                while (this.isProgramRunning) {
                    handleMenu();
                }
            } catch (JSONException e) {
                System.out.println("Unable to load previous lists, restarting program");
                startProgram();
            }
        } else if (input.equals("c")) {
            startProgram();
        } else {
            System.out.println("That is not a valid option, please reselect.");
            handlesStartMenu();
        }
    }

    // EFFECTS: deals with start of program
    public void startProgram() {
        createDivider();
        System.out.println("Please enter your calorie goal for the day");
        int num = Integer.parseInt(scanner.nextLine());
        cal = new Calories(num);
        while (this.isProgramRunning) {
            handleMenu();
        }
    }

    // EFFECTS: displays and processes inputs for main menu
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processMenu(input);
    }

    // EFFECTS: displays main menu
    public void displayMenu() {
        createDivider();
        System.out.println("Your calorie goal is: " + cal.getCalorieGoal());
        System.out.println("Today's date is: " + cal.getDate());
        createDivider();
        System.out.println("Please select an option");
        System.out.println("Reset calorie goal: c");
        System.out.println("Add/remove a item: a");
        System.out.println("View list of foods consumed: f");
        System.out.println("View list of exercises consumed: e");
        System.out.println("View your statistics for the day: s");
        System.out.println("Save Current Lists & Goal: m");
        System.out.println("Quit Application: q");
    }

    // EFFECTS: processes menu inputs
    public void processMenu(String input) {
        createDivider();
        if (input.equals("c")) {
            helpSetNewGoal();
        } else if (input.equals("f")) {
            handlesFoodMenu();
        } else if (input.equals("e")) {
            System.out.println(createListExercise(ex.getListExercise()));
        } else if (input.equals("s")) {
            viewStatisitics();
        } else if (input.equals("q")) {
            quitApp();
        } else if (input.equals("a")) {
            handlesAddRemoveItem();
        } else if (input.equals("m")) {
            saveLists();
        } else {
            System.out.println("That is not a valid option, please reselect.");
        }
    }

    // EFFECTS: handles addition of item
    public void handlesAddRemoveItem() {
        displayAddRemoveItem();
        String input = this.scanner.nextLine();
        processAddRemoveItem(input);
    }

    // EFFECTS: displays which item to press
    public void displayAddRemoveItem() {
        createDivider();
        System.out.println("Please select an option");
        System.out.println("Add food item: f");
        System.out.println("Remove food item: r");
        System.out.println("Add exercise item: e");
        System.out.println("Remove exercise: x");
        System.out.println("Clear a list: c");
        System.out.println("Return to menu: m");
    }

    // EFFECTS: process addition of item
    public void processAddRemoveItem(String input) {
        if (input.equals("f")) {
            createFood();
        } else if (input.equals("r")) {
            removeFood();
        } else if (input.equals("e")) {
            createExercise();
        } else if (input.equals("x")) {
            removeExercise();
        } else if (input.equals("c")) {
            handlesClearList();
        } else if (input.equals("m")) {
            System.out.println("Returning to the main menu");
        } else {
            System.out.println("That is not a valid option, please reselect.");
            handlesAddRemoveItem();
        }
    }

    // REQUIRES: newCal must be a integer > 0
    // MODIFIES: this
    // EFFECTS: sets new calorie goal
    public void helpSetNewGoal() {
        System.out.println("Enter new goal: ");
        int newCal = Integer.parseInt(scanner.nextLine());
        cal.setNewGoal(newCal);
    }

    // EFFECTS: handles food menu
    public void handlesFoodMenu() {
        displayFoodMenu();
        String inputFood = this.scanner.nextLine();
        processMenuFood(inputFood);
    }

    // EFFECTS: displays food menu
    public void displayFoodMenu() {
        createDivider();
        System.out.println("Please select an option:");
        System.out.println("View all foods consumed: a");
        System.out.println("View different food groups consumed: g");
        System.out.println("Return to menu: m");
    }

    // EFFECTS: shows statistics of food items class
    public void viewStatisitics() {
        createDivider();
        System.out.println("Your calorie goal is: " + cal.getCalorieGoal());
        System.out.println("You have netted: " + cal.difference(lofi, ex) + " in calories today.");
        System.out.println("You need " + cal.neededAmount(lofi, ex) + " calories to achieve your goal today.");
        System.out.println("You have gone over your goal by: " + cal.overAmount(lofi, ex));
        createDivider();
        System.out.println("The total amount of food calories eaten today: " + lofi.totalNumOfCalories());
        System.out.println("The total amount of calories burned today: " + ex.getTotalCaloriesBurnt());
        createDivider();
        System.out.println("The total amount of food items eaten today: " + lofi.sizeOfList());
        System.out.println("The total amount of fruit items eaten today: " + lofi.getFruitList().size());
        System.out.println("The total amount of vegetable items eaten today: " + lofi.getVegetableList().size());
        System.out.println("The total amount of grain items eaten today: " + lofi.getGrainList().size());
        System.out.println("The total amount of dairy items eaten today: " + lofi.getDairyList().size());
        System.out.println("The total amount of protein items eaten today: " + lofi.getProteinList().size());
        System.out.println("The number of exercises you have down today is: " + ex.sizeList());
    }

    // EFFECTS: process input menu for food items
    public void processMenuFood(String input) {
        createDivider();
        switch (input) {
            case "a":
                System.out.println(createListFoodCals(lofi.getListOfFoodItems()));
                break;
            case "g":
                handlesFoodGroup();
                break;
            case "m":
                System.out.println("Returning to the main menu");
                break;
            default:
                System.out.println("That is not a valid option, please reselect.");
        }
    }

    // EFFECTS: handles addition of item
    public void handlesFoodGroup() {
        displayFoodGroup();
        String input = this.scanner.nextLine();
        processFoodGroup(input);
    }

    // EFFECTS: displays which item to press
    public void displayFoodGroup() {
        createDivider();
        System.out.println("Please select an option");
        System.out.println("View all fruits consumed: f");
        System.out.println("View all vegetables consumed: v");
        System.out.println("View all grains consumed: g");
        System.out.println("View all proteins consumed: p");
        System.out.println("View all dairy consumed: d");
        System.out.println("Return to menu: m");
    }

    // EFFECTS: process addition of item
    public void processFoodGroup(String input) {
        createDivider();
        switch (input) {
            case "f":
                System.out.println(createListFoodCals(lofi.getFruitList()));
                break;
            case "v":
                System.out.println(createListFoodCals(lofi.getVegetableList()));
                break;
            case "g":
                System.out.println(createListFoodCals(lofi.getGrainList()));
                break;
            case "p":
                System.out.println(createListFoodCals(lofi.getProteinList()));
                break;
            case "d":
                System.out.println(createListFoodCals(lofi.getDairyList()));
                break;
            case "m":
                System.out.println("Returning to the main menu");
                break;
            default:
                System.out.println("That is not a valid option, please reselect.");
        }
    }

    // EFFECTS: creates list of food items in format name : calories
    public List<String> createListFoodCals(List<FoodItems> listFood) {
        List<String> ls = new ArrayList<String>();
        for (FoodItems fi : listFood) {
            String s = fi.getName() + " : " + fi.getCalories() + " calories";
            ls.add(s);
        }
        return ls;
    }

    // EFFECTS: creates list of exercises in format exercises : calories
    public List<String> createListExercise(List<Exercise> listEx) {
        List<String> ls = new ArrayList<String>();
        for (Exercise e : listEx) {
            String s = e.getExercise() + " : " + e.getCaloriesBurned() + " calories";
            ls.add(s);
        }
        return ls;
    }

    // REQUIRES: calories must be a integer > 0
    // MODIFIES: this
    // EFFECTS: creates new food item and adds it to the list
    public void createFood() throws IllegalArgumentException {
        createDivider();
        System.out.println("Input Food Name");
        String foodName = this.scanner.nextLine();
        System.out.println("Input Amount of Calories");
        int calories = Integer.parseInt(scanner.nextLine());
        System.out.println("Input the food group:");
        System.out.println("Note: must be fruit, vegetable, grain, protein or dairy only.");

        FoodGroup foodGroup = checkFoodGroup();
        this.scanner.nextLine(); // find solution for this

        FoodItems food = new FoodItems(foodName, calories, foodGroup);
        lofi.addFood(food);
        System.out.println("The food item has been added");
    }

    // EFFECTS: determines if it is a food group or not
    public FoodGroup checkFoodGroup() throws IllegalArgumentException {
        boolean validInput = false;
        FoodGroup foodGroup = null;
        while (!validInput) {
            System.out.println("Input the food group:");
            try {
                foodGroup = FoodGroup.valueOf(scanner.next().toUpperCase());
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid Food Group.");
            }
        }
        return foodGroup;
    }

    // REQUIRES: calsBurned must be a integer > 0
    // MODIFIES: this
    // EFFECTS: creates new exercise
    public void createExercise() {
        createDivider();
        System.out.println("Input Exercise Name");
        String exName = this.scanner.nextLine();
        System.out.println("Input Amount of Calories");
        int calsBurned = Integer.parseInt(scanner.nextLine());

        Exercise exs = new Exercise(exName, calsBurned);
        ex.addExercise(exs);
        System.out.println("The exercise has been added");
    }

    // MODIFIES: this
    // EFFECTS: quits application
    public void quitApp() {
        createDivider();
        System.out.println("Thanks for using the Calorie Tracker");
        this.isProgramRunning = false;
    }

    // MODIFIES: this
    // EFFECTS: removes food item from list
    public void removeFood() {
        createDivider();
        if (lofi.sizeOfList() == 0) {
            System.out.println("There is nothing to remove");
        } else {
            System.out.println(createListFoodCals(lofi.getListOfFoodItems()));
            System.out.println("Which item would you like to remove? Please provide position in the list.");
            int input = Integer.parseInt(scanner.nextLine());
            input -= 1;
            lofi.removeFood(lofi.getListOfFoodItems().get(input));
            System.out.println("The item has been removed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes exercise item from list
    public void removeExercise() {
        createDivider();
        if (ex.sizeList() == 0) {
            System.out.println("There is nothing to clear");
        } else {
            System.out.println(createListExercise(ex.getListExercise()));
            System.out.println("Which item would you like to remove? Please provide position in the list.");
            int input = Integer.parseInt(scanner.nextLine());
            input -= 1;
            ex.removeExercise(ex.getListExercise().get(input));
            System.out.println("The item has been removed!");
        }
    }

    // EFFECTS: handles clearing of list
    public void handlesClearList() {
        displayClearList();
        String input = this.scanner.nextLine();
        processClearList(input);
    }

    // EFFECTS: displays which item to press
    public void displayClearList() {
        createDivider();
        System.out.println("Please select an option");
        System.out.println("Clear list of food items: f");
        System.out.println("Clear list of exercises: e");
        System.out.println("Return to the main menu: m");
    }

    // EFFECTS: process addition of item
    public void processClearList(String input) {
        createDivider();
        switch (input) {
            case "f":
                clearListFi();
                break;
            case "e":
                clearListEx();
                break;
            case "m":
                System.out.println("Returning to the main menu");
                break;
            default:
                System.out.println("That is not a valid option, please reselect.");
        }
    }

    // MODIFIES: this
    // EFFECTS: clears food items list
    public void clearListFi() {
        createDivider();
        if (lofi.sizeOfList() == 0) {
            System.out.println("There is nothing to clear");
        } else {
            lofi.clearList();
            System.out.println("List has been cleared");
        }
    }

    // MODIFIES: this
    // EFFECTS: clears exercise list
    public void clearListEx() {
        createDivider();
        if (ex.sizeList() == 0) {
            System.out.println("There is nothing to clear");
        } else {
            ex.clearList();
            System.out.println("List has been cleared");
        }
    }

    // EFFECTS: creates a divider for aesthetics purposes
    public void createDivider() {
        System.out.println("--------------------------------------------------");
    }

    // EFFECTS: saves both lists to file
    private void saveLists() {
        try {
            writerex.open();
            writerex.writeExercise(ex);
            writerex.close();
            writerf.open();
            writerf.writeFood(lofi);
            writerf.close();
            writerc.open();
            writerc.writeCalorie(cal);
            writerc.close();
            System.out.println("All lists & goals have been saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the file");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads previous lists from file
    private void loadLists() {
        try {
            lofi = readerf.readFootItems();
            ex = readerex.readExercise();
            cal = readerc.readCalories();
            System.out.println("Loaded all lists & previous goal");
        } catch (IOException e) {
            System.out.println("Unable to find file");
        }
    }
}
