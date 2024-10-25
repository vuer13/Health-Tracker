package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Calories;
import model.Exercise;
import model.FoodGroup;
import model.FoodItems;

public class TestJson {
    
    // EFFECTS: helper method to check correct exercise
    protected void checkExercise(String name, int calories, Exercise e) {
        assertEquals(name, e.getExercise());
        assertEquals(calories, e.getCaloriesBurned());
    }

    // EFFECTS: helper method to check correct food item
    protected void checkFoodItem(String name, int calories, FoodGroup group, FoodItems f) {
        assertEquals(name, f.getName());
        assertEquals(calories, f.getCalories());
        assertEquals(group, f.getFoodGroup());
    }

    // EFFECTS: helper method to check correct calorie goal
    protected void checkCalories(int amt, Calories c) {
        assertEquals(amt, c.getCalorieGoal());
    }
}
