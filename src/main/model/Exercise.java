package model;

public class Exercise {

    private String exercise;
    private int caloriesburnt;
    
    // EFFECTS: constucts exercise item with name of exercise and number of calories it burns
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


}
