package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.FoodGroup.*;

public class TestFoodItems {

    private FoodItems food;

    @BeforeEach
    void runBefore() {
        food = new FoodItems("Bob", 1000, VEGETABLE);
    }

    @Test
    void testConstructor() {
        assertEquals("Bob", food.getName());
        assertEquals(1000, food.getCalories());
        assertEquals(VEGETABLE, food.getFoodGroup());
    }

    @Test
    void testEditName() {
        food.editName("Pat");
        assertEquals("Pat", food.getName());
    }

    @Test
    void testEditCalories() {
        food.editCalories(100);
        assertEquals(100, food.getCalories());
    }

    @Test
    void testEditFoodGroups() {
        food.editFoodGroup(FRUIT);
        assertEquals(FRUIT, food.getFoodGroup());
    }
}
