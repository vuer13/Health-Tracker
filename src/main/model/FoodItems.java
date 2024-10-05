package model;

public class FoodItems {

    private String name;
    private int calories;
    private FoodGroup foodGroups;

    // REQUIRES: foodGroups must be fruit, vegatable, diary, grains and protein
    // EFFECTS: Constructs Food Items with name, amount of calories and food groups
    // involved
    public FoodItems(String name, int calories, FoodGroup foodGroups) {
        this.name = name;
        this.calories = calories;
        this.foodGroups = foodGroups;
    }

    // EFFECTS: Returns name of item
    public String getName() {
        return this.name;
    }

    // EFFECTS: Returns number of calories item has
    public int getCalories() {
        return calories;
    }

    // EFFECTS: Returns food group the item is in
    public FoodGroup getFoodGroup() {
        return foodGroups;
    }

    // MODIFIES: this
    // EFFECTS: Edits name of food item
    public void editName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: Edits calories of food item
    public void editCalories(int calories) {
        this.calories = calories;
    }

    // REQUIRES: foodGroups must be fruit, vegatable, diary, grains and protein
    // MODIFIES: this
    // EFFECTS: Edits food group of food item
    public void editFoodGroup(FoodGroup foodGroups) {
        this.foodGroups = foodGroups;
    }
}
