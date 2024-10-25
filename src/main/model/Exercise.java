// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import org.json.JSONObject;

import persistance.Writeable;

// Represents a Exercise with the amount of calories burnt
public class Exercise implements Writeable {

    private String exercise;
    private int caloriesburnt;

    // REQUIRES: calories > 0
    // EFFECTS: constucts exercise item with name of exercise and number of calories
    // it burns
    public Exercise(String exercise, int calories) {
        this.exercise = exercise;
        caloriesburnt = calories;
    }

    // EFFECTS: returns name of exercise
    public String getExercise() {
        return exercise;
    }

    // EFFECTS: returns number of calories burned
    public int getCaloriesBurned() {
        return caloriesburnt;
    }

    // REQUIRES: calories > 0
    // MODIFIES: this
    // EFFECTS: edits number of calories burned
    public void editCalories(int calories) {
        caloriesburnt = calories;
    }

    // MODIFIES: this
    // EFFECTS: edits exercise name
    public void editExercise(String exercise) {
        this.exercise = exercise;
    }

    // EFFECTS: returns json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", exercise);
        json.put("Calories", caloriesburnt);
        return json;
    }
}
