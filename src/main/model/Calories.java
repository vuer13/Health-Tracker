package model;

import java.time.LocalDate;
import java.lang.Math;

public class Calories {

    private int calorieGoal;
    private LocalDate date;

    // EFFECTS: Constructs object with calorie goal and today's date
    public Calories(int cal) {
        calorieGoal = cal;
        date = LocalDate.now();
    }

    // EFFECTS: returns calories goals
    public int getCalorieGoal() {
        return calorieGoal;
    }

    // MODIFIES; this
    // EFFECTS: sets new calorie goal
    public void setNewGoal(int calorie) {
        calorieGoal = calorie;
    }

    // EFFECTS: determines difference between amount consumed and amount burned
    public int difference(ListOfFoodItems lofi, ListExercise loe) {
        int foodCals = lofi.totalNumOfCalories();
        int exCals = loe.getTotalCaloriesBurnt();
        return foodCals - exCals;
    }

    // EFFECTS: returns how much calories are required to reach your goal, if went over goal, will return 0
    public int neededAmount(ListOfFoodItems lofi, ListExercise loe) {
        if (calorieGoal - difference(lofi, loe) > 0) {
            return calorieGoal - difference(lofi, loe);
        } else {
            return 0;
        }
    }

    // EFFECTS: returns how many calories you went over, if no calories over, will return 0
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
}
