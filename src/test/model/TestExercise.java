package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestExercise {
    
    private Exercise item;

    @BeforeEach
    void setUp() {
        item = new Exercise("Bicep Curls", 10);
    }

    @Test
    void testConstructor() {
        assertEquals("Bicep Curls", item.getExercise());
        assertEquals(10, item.getCaloriesBurned());
    }

    @Test
    void testEditName() {
        item.editExercise("Tricep Curls");
        assertEquals("Tricep Curls", item.getExercise());
    }

    @Test
    void testEditCalories() {
        item.editCalories(100);
        assertEquals(100, item.getCaloriesBurned());
    }
}
