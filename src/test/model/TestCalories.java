package model;

import static model.FoodGroup.DAIRY;
import static model.FoodGroup.FRUIT;
import static model.FoodGroup.GRAIN;
import static model.FoodGroup.PROTEIN;
import static model.FoodGroup.VEGETABLE;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

public class TestCalories {
    
    private Calories cal;
    private ListExercise exs;
    private ListOfFoodItems fis;
    private FoodItems food1;
    private FoodItems food2;
    private Exercise ex1;
    private Exercise ex2;

    @BeforeEach
    void setUp() {
        cal = new Calories(300);
        exs = new ListExercise();
        fis = new ListOfFoodItems();
        food1 = new FoodItems("Banana", 70, FRUIT);
        food2 = new FoodItems("Broccoli", 30, VEGETABLE);
        ex1 = new Exercise("Bicep Curls", 10);
        ex2 = new Exercise("Tricep Curls", 20);
    }

    @Test
    void testConstructor() {
        assertEquals(3000, cal.getCalorieGoal());
        assertEquals(LocalDate.now(), cal.getDate());
    }

    @Test
    void testSetNewGoal() {
        cal.setNewGoal(2500);
        assertEquals(2500, cal.getCalorieGoal());
    }

    @Test
    void testDifference() {
        fis.addFood(food1);
        fis.addFood(food2);
        exs.addExercise(ex1);
        exs.addExercise(ex2);
        assertEquals(70, cal.difference(fis, exs));
    }

    @Test
    void testNeededAmount() {
        fis.addFood(food1);
        fis.addFood(food2);
        exs.addExercise(ex1);
        exs.addExercise(ex2);
        assertEquals(230, cal.neededAmount(fis, exs));
    }

    @Test
    void testNeededAmountZero() {
        FoodItems food3 = new FoodItems("Chips", 230, GRAIN);

        fis.addFood(food1);
        fis.addFood(food2);
        fis.addFood(food3);
        exs.addExercise(ex1);
        exs.addExercise(ex2);
        assertEquals(0, cal.neededAmount(fis, exs));
    }

    @Test
    void testNeededAmountNeg() {
        FoodItems food3 = new FoodItems("Chips", 330, GRAIN);

        fis.addFood(food1);
        fis.addFood(food2);
        fis.addFood(food3);
        exs.addExercise(ex1);
        exs.addExercise(ex2);
        assertEquals(0, cal.neededAmount(fis, exs));
    }

    @Test 
    void testOverAmountNone() {
        fis.addFood(food1);
        fis.addFood(food2);
        exs.addExercise(ex1);
        exs.addExercise(ex2);
        assertEquals(0, cal.overAmount());
    }

    @Test 
    void testOverAmountEqual() {
        FoodItems food3 = new FoodItems("Chips", 330, GRAIN);
        Exercise ex3 = new Exercise("Squat", 30);

        fis.addFood(food3);
        exs.addExercise(ex3);

        assertEquals(0, cal.overAmount());
    }

    @Test 
    void testOverAmountGreater() {
        FoodItems food3 = new FoodItems("Chips", 330, GRAIN);
        Exercise ex3 = new Exercise("Squat", 20);

        fis.addFood(food3);
        exs.addExercise(ex3);

        assertEquals(10, cal.overAmount());
    }

    @Test
    void testSetDate() {
        cal.setDate();
        assertEquals(cal.getDate(), LocalDate.now());
    }
}
