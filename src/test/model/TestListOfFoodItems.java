package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestListOfFoodItems {
    
    private ListOfFoodItems lofi;
    private List<FoodItems> listFoods;
    private FoodItems food1;
    private FoodItems food2;

    @BeforeEach
    void setUp() {
        lofi = new ListOfFoodItems();
        listFoods = lofi.getListOfFoodItems();
        food1 = new FoodItems("Banana", 70, "fruit");
        food2 = new FoodItems("Broccoli", 30, "vegatables");
    }

    @Test
    private void testConstructor() {
        assertEquals(listFoods.size(), 0);
        assertTrue(listFoods.isEmpty());
    }

    @Test
    private void testAddFoodSingle() {
        listFoods.add(food1);
        assertEquals(listFoods.get(0), food1);
        assertTrue(listFoods.contains(food1));
        assertEquals(listFoods.size(), 1);
        assertFalse(listFoods.isEmpty());
    }

    @Test
    private void testAddFoodMultiple() {
        lofi.addFood(food1);
        assertEquals(listFoods.get(0), food1);
        assertTrue(listFoods.contains(food1));
        assertEquals(listFoods.size(), 1);
        assertFalse(listFoods.isEmpty());

        lofi.addFood(food2);
        assertEquals(listFoods.get(0), food1);
        assertEquals(listFoods.get(1), food2);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertEquals(listFoods.size(), 2);
        assertFalse(listFoods.isEmpty());
    }

    @Test
    private void testAddFoodMultipleSame() {
        lofi.addFood(food1);
        assertEquals(listFoods.get(0), food1);
        assertTrue(listFoods.contains(food1));
        assertEquals(listFoods.size(), 1);
        assertFalse(listFoods.isEmpty());

        lofi.addFood(food1);
        assertEquals(listFoods.get(0), food1);
        assertEquals(listFoods.get(1), food1);
        assertTrue(listFoods.contains(food1));
        assertEquals(listFoods.size(), 2);
        assertFalse(listFoods.isEmpty());
    }

    @Test
    private void testRemoveFoodSingle() {
        lofi.addFood(food1);
        assertEquals(listFoods.get(0), food1);
        assertTrue(listFoods.contains(food1));
        assertEquals(listFoods.size(), 1);
        assertFalse(listFoods.isEmpty());

        lofi.removeFood(food1);
        assertEquals(listFoods.size(), 0);
        assertTrue(listFoods.isEmpty());
    }

    @Test
    private void testRemoveFoodMultiple() {
        lofi.addFood(food1);
        lofi.addFood(food2);
        assertEquals(listFoods.get(0), food1);
        assertEquals(listFoods.get(1), food2);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertEquals(listFoods.size(), 2);
        assertFalse(listFoods.isEmpty());

        lofi.removeFood(food1);
        assertEquals(listFoods.size(), 1);
        assertEquals(listFoods.get(0), food2);
        assertTrue(listFoods.contains(food2));
        assertFalse(listFoods.contains(food1));
        assertFalse(listFoods.isEmpty());
    }

    @Test
    private void testRemoveFoodsMultiple() {
        lofi.addFood(food1);
        lofi.addFood(food2);
        assertEquals(listFoods.get(0), food1);
        assertEquals(listFoods.get(1), food2);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertEquals(listFoods.size(), 2);
        assertFalse(listFoods.isEmpty());

        lofi.removeFood(food1);
        assertEquals(listFoods.size(), 1);
        assertEquals(listFoods.get(0), food2);
        assertTrue(listFoods.contains(food2));
        assertFalse(listFoods.contains(food1));
        assertFalse(listFoods.isEmpty());

        lofi.removeFood(food2);
        assertEquals(listFoods.size(), 0);
        assertTrue(listFoods.isEmpty());
        assertFalse(listFoods.contains(food1));
        assertFalse(listFoods.contains(food2));
    }

    @Test
    private void testSizeOfListEmpty() {
        assertEquals(lofi.sizeOfList(), 0);
    }

    @Test
    private void testSizeOfList() {
        lofi.addFood(food1);
        lofi.addFood(food2);

        assertEquals(lofi.sizeOfList(), 2);
    }

    @Test
    private void testTotalNumOfCaloriesAddition() {
        lofi.addFood(food1);
        lofi.addFood(food2);

        assertEquals(lofi.totalNumOfCalories(), 100);
    }

    @Test
    private void testTotalNumOfCaloriesSubtraction() {
        lofi.addFood(food1);
        lofi.addFood(food2);

        assertEquals(lofi.totalNumOfCalories(), 100);

        lofi.removeFood(food1);

        assertEquals(lofi.totalNumOfCalories(), 30);
    }

    @Test
    private void testTotalNumOfCaloriesEmpty() {
        assertEquals(lofi.totalNumOfCalories(), 0);
    }

    @Test
    private void testClearList() {
        lofi.addFood(food1);
        lofi.addFood(food2);
        assertEquals(listFoods.get(0), food1);
        assertEquals(listFoods.get(1), food2);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertEquals(lofi.sizeOfList(), 2);
        assertFalse(listFoods.isEmpty());
        assertEquals(lofi.totalNumOfCalories(), 100);

        lofi.clearList();
        assertTrue(listFoods.isEmpty());
        assertEquals(lofi.totalNumOfCalories(), 0);
        assertTrue(listFoods.isEmpty());
        assertFalse(listFoods.contains(food1));
        assertFalse(listFoods.contains(food2));
    }
}
