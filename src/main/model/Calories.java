// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import java.time.LocalDate;

import org.json.JSONObject;

import persistance.Writeable;

import java.lang.Math;

// Represents calories of a food item or exercise
public class Calories implements Writeable {

    private int calorieGoal;
    private LocalDate date;

    // REQUIRES: cal > 0
    // EFFECTS: Constructs object with calorie goal and today's date
    public Calories(int cal) {
        calorieGoal = cal;
        date = LocalDate.now();
    }

    // EFFECTS: returns calories goals
    public int getCalorieGoal() {
        return calorieGoal;
    }

    // REQUIRES: calorie > 0
    // MODIFIES; this
    // EFFECTS: sets new calorie goal
    public void setNewGoal(int calorie) {
        calorieGoal = calorie;
        EventLog.getInstance().logEvent(new Event("A calorie goal was set"));
    }

    // EFFECTS: determines difference between amount consumed and amount burned
    public int difference(ListOfFoodItems lofi, ListExercise loe) {
        int foodCals = lofi.totalNumOfCalories();
        int exCals = loe.getTotalCaloriesBurnt();
        return foodCals - exCals;
    }

    // EFFECTS: returns how much calories are required to reach your goal, if went
    // over goal, will return 0
    public int neededAmount(ListOfFoodItems lofi, ListExercise loe) {
        if (calorieGoal - difference(lofi, loe) > 0) {
            return calorieGoal - difference(lofi, loe);
        } else {
            return 0;
        }
    }

    // EFFECTS: returns how many calories you went over, if no calories over, will
    // return 0
    public int overAmount(ListOfFoodItems lofi, ListExercise loe) {
        if (calorieGoal - difference(lofi, loe) < 0) {
            return Math.abs(calorieGoal - difference(lofi, loe));
        } else {
            return 0;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate() {
        date = LocalDate.now();
    }

    // EFFECTS: returns json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Calories", calorieGoal);
        return json;
    }
}
