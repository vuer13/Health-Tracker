package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static model.FoodGroup.FRUIT;
import static model.FoodGroup.VEGETABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
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
        food1 = new FoodItems("Banana", 70, FRUIT);
        food2 = new FoodItems("Broccoli", 30, VEGETABLE);
    }

    @Test
    void testConstructor() {
        assertEquals(listFoods.size(), 0);
        assertTrue(listFoods.isEmpty());
    }

    @Test
    void testAddFoodSingle() {
        listFoods.add(food1);
        assertEquals(listFoods.get(0), food1);
        assertTrue(listFoods.contains(food1));
        assertEquals(listFoods.size(), 1);
        assertFalse(listFoods.isEmpty());
    }

    @Test
    void testAddFoodMultiple() {
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
    void testAddFoodMultipleSame() {
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
    void testRemoveFoodSingle() {
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
    void testRemoveFoodMultiple() {
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
    void testRemoveFoodsMultiple() {
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
    void testSizeOfListEmpty() {
        assertEquals(lofi.sizeOfList(), 0);
    }

    @Test
    void testSizeOfList() {
        lofi.addFood(food1);
        lofi.addFood(food2);

        assertEquals(lofi.sizeOfList(), 2);
    }

    @Test
    void testTotalNumOfCaloriesAddition() {
        lofi.addFood(food1);
        lofi.addFood(food2);

        assertEquals(lofi.totalNumOfCalories(), 100);
    }

    @Test
    void testTotalNumOfCaloriesSubtraction() {
        lofi.addFood(food1);
        lofi.addFood(food2);

        assertEquals(lofi.totalNumOfCalories(), 100);

        lofi.removeFood(food1);

        assertEquals(lofi.totalNumOfCalories(), 30);
    }

    @Test
    void testTotalNumOfCaloriesEmpty() {
        assertEquals(lofi.totalNumOfCalories(), 0);
    }

    @Test
    void testClearList() {
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
