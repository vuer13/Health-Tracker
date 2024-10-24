package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Exercise;
import model.FoodGroup;
import model.FoodItems;

public class TestJson {
    
    protected void checkExercise(String name, int calories, Exercise e) {
        assertEquals(name, e.getExercise());
        assertEquals(calories, e.getCaloriesBurned());
    }

    protected void checkFoodItem(String name, int calories, FoodGroup group, FoodItems f) {
        assertEquals(name, f.getName());
        assertEquals(calories, f.getCalories());
        assertEquals(group, f.getFoodGroup());
    }
}
