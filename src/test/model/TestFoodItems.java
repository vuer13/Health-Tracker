package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFoodItems {

    private FoodItems food;

    @BeforeEach
    void runBefore() {
        food = new FoodItems();
    }

    @Test
    void testConstructor() {
        assertEquals("Bob", food.getName());
        assertEquals("1000", food.getCalories());
        assertEquals("Vegtables", food.getFoodGroup());
    }

    @Test
    void testEditName() {
        food.editName("Pat");
        assertEquals("Pat", food.getName());
    }

    @Test
    void testEditCalories() {
        food.editCalories(100);
        assertEquals("100", food.getCalories());
    }

    @Test
    void testEditFoodGroups() {
        food.editFoodGroup("Fruit");
        assertEquals("Fruit", food.getFoodGroup());
    }
}
