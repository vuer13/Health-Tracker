package ui;

import model.Calories;
import model.Exercise;
import model.FoodGroup;
import model.FoodItems;
import model.ListExercise;
import model.ListOfFoodItems;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Tracker {

    private Scanner scanner;
    private boolean isProgramRunning;
    private ListOfFoodItems lofi;
    private ListExercise ex;
    private Calories cal;

    // EFFECTS: Initiates UI console base
    public Tracker() {
        isProgramRunning = true;
        lofi = new ListOfFoodItems();
        ex = new ListExercise();
        scanner = new Scanner(System.in);

        System.out.println("Welcome to the Calorie Tracker");
        System.out.println("Please enter your calorie goal for the day");
        int input = Integer.parseInt(scanner.nextLine());
        cal = new Calories(input);

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
        System.out.println("Your calorie goal is :" + cal.getCalorieGoal());
        System.out.println("Today's date is: " + cal.getDate());
        System.out.println("Please select an option");
        System.out.println("Reset calorie goal: c");
        System.out.println("Add/remove a item: a");
        System.out.println("View list of foods consumed: f");
        System.out.println("View list of exercises consumed: e");
        System.out.println("View your statistics for the day: s");
        System.out.println("Quit Application: q");
    }

    // EFFECTS: processes menu inputs
    public void processMenu(String input) {
        switch (input) {
            case "c":
                helpSetNewGoal();
                break;
            case "f":
                handlesFoodMenu();
                break;
            case "e":
                System.out.println(createListExercise(ex.getListExercise()));
                break;
            case "s":
                viewStatisitics();
                break;
            case "q":
                quitApp();
                break;
            case "a":
                handlesAddRemoveItem();
                break;
            default:
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
        System.out.println("Please select an option");
        System.out.println("Add food item: f");
        System.out.println("Remove food item: r");
        System.out.println("Add exercise item: e");
        // TODO: add remove exercise
        // TODO: add clear list
        System.out.println("Return to menu: m");
    }

    // EFFECTS: process addition of item
    public void processAddRemoveItem(String input) {
        switch (input) {
            case "f":
                createFood();
                break;
            case "r":
                removeFood();
                break;
            case "e":
                createExercise();
                break;
            // TODO: new case for remove exercise
            case "m":
                System.out.println("Returning to the main menu");
                break;
            default:
                System.out.println("That is not a valid option, please reselect.");
        }
    }

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
        System.out.println("Please select an option:");
        System.out.println("View all foods consumed: a");
        System.out.println("View different food groups consumed: g");
        System.out.println("Return to menu: m");
    }

    // EFFECTS: shows statistics of food items class
    public void viewStatisitics() {
        System.out.println("Your calorie goal is :" + cal.getCalorieGoal());
        System.out.println("You have netted: " + cal.difference(lofi, ex) + " in calories today.");
        System.out.println("You need " + cal.neededAmount(lofi, ex) + " to achieve your goal today.");
        System.out.println("You have gone over your goal by: " + cal.overAmount(lofi, ex));
        System.out.println("The total amount of food calories eaten today: " + lofi.totalNumOfCalories());
        System.out.println("The total amount of calories burned today: " + ex.getTotalCaloriesBurnt());
        System.out.println("The total amount of food items eaten today: " + lofi.sizeOfList());
        System.out.println("The total amount of fruit items eaten today: " + lofi.getFruitList().size());
        System.out.println("The total amount of vegetable items eaten today: " + lofi.getVegetableList().size());
        System.out.println("The total amount of grain items eaten today: " + lofi.getGrainList().size());
        System.out.println("The total amount of dairy items eaten today: " + lofi.getDairyList().size());
        System.out.println("The total amount of protein items eaten today: " + lofi.getProteinList().size());
    }

    // EFFECTS: process input menu for food items
    public void processMenuFood(String input) {
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
            String s = fi.getName() + " : " + fi.getCalories();
            ls.add(s);
        }
        return ls;
    }

    // EFFECTS: creates list of exercises in format exercises : calories
    public List<String> createListExercise(List<Exercise> listEx) {
        List<String> ls = new ArrayList<String>();
        for (Exercise e : listEx) {
            String s = e.getExercise() + " : " + e.getCaloriesBurned();
            ls.add(s);
        }
        return ls;
    }

    // EFFECTS: creates new food item and adds it to the list
    public void createFood() {
        System.out.println("Input Food Name");
        String foodName = this.scanner.nextLine();
        System.out.println("Input Amount of Calories");
        int calories = Integer.parseInt(scanner.nextLine());
        System.out.println("Input the food group:");
        FoodGroup foodGroup = FoodGroup.valueOf(scanner.next().toUpperCase());
        this.scanner.nextLine(); // Will need to find better solution later

        FoodItems food = new FoodItems(foodName, calories, foodGroup);
        lofi.addFood(food);
    }

    // EFFECTS: creates new exercise
    public void createExercise() {
        System.out.println("Input Exercise Name");
        String exName = this.scanner.nextLine();
        System.out.println("Input Amount of Calories");
        int calsBurned = Integer.parseInt(scanner.nextLine());

        Exercise exs = new Exercise(exName, calsBurned);
        ex.addExercise(exs);
    }

    // MODIFIES: this
    // EFFECTS: quits application
    public void quitApp() {
        System.out.println("Thanks for using the Calorie Tracker");
        this.isProgramRunning = false;
    }

    // MODIFIES: this
    // EFFECTS: removes food item from list
    public void removeFood() {
        System.out.println(createListFoodCals(lofi.getListOfFoodItems()));
        System.out.println("Which item would you like to remove? Please provide position in the list.");
        int input = Integer.parseInt(scanner.nextLine());
        input -= 1;
        lofi.removeFood(lofi.getListOfFoodItems().get(input));
        System.out.println("The item has been removed!");
    }

    // MODIFIES: this
    // EFFECTS: removes exercise item from list
    public void removeExercise() {
        // TODO: same as above
    }

    // MODIFIES: this
    // EFFECTS: clears food items list
    public void clearListFI() {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: clears exercise list
    public void clearListEx() {
        // TODO
    }
}
