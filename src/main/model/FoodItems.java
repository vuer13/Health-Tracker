// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import org.json.JSONObject;

import persistance.Writeable;

// Represents a food item with the number of calories and its food group
public class FoodItems implements Writeable {

    private String name;
    private int calories;
    private FoodGroup foodGroups;

    // REQUIRES: calories > 0
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

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: Edits calories of food item
    public void editCalories(int calories) {
        this.calories = calories;
    }

    // MODIFIES: this
    // EFFECTS: Edits food group of food item
    public void editFoodGroup(FoodGroup foodGroups) {
        this.foodGroups = foodGroups;
    }

    // EFFECTS: returns json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Food", name);
        json.put("Calories", calories);
        json.put("Food Group", foodGroups);
        return json;
    }

    // EFFECTS: returns a string that states
    public String formatFoodString() {
        return this.name + ", Calories: " + this.calories + ", Food Group: " + this.foodGroups;
    }
}
