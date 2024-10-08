package model;
import static model.FoodGroup.DAIRY;
import static model.FoodGroup.FRUIT;
import static model.FoodGroup.GRAIN;
import static model.FoodGroup.PROTEIN;
import static model.FoodGroup.VEGETABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
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
        lofi = new ListOfFoodItems(LocalDate.now());
        listFoods = lofi.getListOfFoodItems();
        food1 = new FoodItems("Banana", 70, FRUIT);
        food2 = new FoodItems("Broccoli", 30, VEGETABLE);
    }

    @Test
    void testConstructor() {
        assertEquals(listFoods.size(), 0);
        assertTrue(listFoods.isEmpty());
        assertEquals(LocalDate.now(), lofi.getDate());
    }

    @Test
    void testAddFoodSingle() {
        lofi.addFood(food1);
        assertEquals(listFoods.get(0), food1);
        assertTrue(listFoods.contains(food1));
        assertEquals(listFoods.size(), 1);
        assertFalse(listFoods.isEmpty());

        assertTrue(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 1);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertEquals(lofi.getVegetableList().size(), 0);

        assertFalse(lofi.getProteinList().contains(food1));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertEquals(lofi.getDairyList().size(), 0);
    }

    @Test
    void testAddFoodMultiple() {
        FoodItems food3 = new FoodItems("Pasta", 30, GRAIN);

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

        lofi.addFood(food3);
        assertEquals(listFoods.get(0), food1);
        assertEquals(listFoods.get(1), food2);
        assertEquals(listFoods.get(2), food3);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));
        assertEquals(listFoods.size(), 3);
        assertFalse(listFoods.isEmpty());

        assertTrue(lofi.getFruitList().contains(food1));
        assertFalse(lofi.getFruitList().contains(food2));
        assertFalse(lofi.getFruitList().contains(food3));
        assertEquals(lofi.getFruitList().size(), 1);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertTrue(lofi.getVegetableList().contains(food2));
        assertFalse(lofi.getVegetableList().contains(food3));
        assertEquals(lofi.getVegetableList().size(), 1);

        assertFalse(lofi.getProteinList().contains(food1));
        assertFalse(lofi.getProteinList().contains(food2));
        assertFalse(lofi.getProteinList().contains(food3));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertFalse(lofi.getGrainList().contains(food2));
        assertTrue(lofi.getGrainList().contains(food3));
        assertEquals(lofi.getGrainList().size(), 1);

        assertFalse(lofi.getDairyList().contains(food1));
        assertFalse(lofi.getDairyList().contains(food2));
        assertFalse(lofi.getDairyList().contains(food3));
        assertEquals(lofi.getDairyList().size(), 0);
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

        assertTrue(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 2);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertEquals(lofi.getVegetableList().size(), 0);

        assertFalse(lofi.getProteinList().contains(food1));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertEquals(lofi.getDairyList().size(), 0);
    }

    @Test
    void testFoodGroupsList() {
        FoodItems food3 = new FoodItems("Beef", 20, PROTEIN);
        FoodItems food4 = new FoodItems("Orange", 15, DAIRY);
        FoodItems food5 = new FoodItems("Pasta", 70, GRAIN);
        FoodItems food6 = new FoodItems("Rice", 30, GRAIN);

        lofi.addFood(food1);
        lofi.addFood(food2);
        lofi.addFood(food3);
        lofi.addFood(food4);
        lofi.addFood(food5);
        lofi.addFood(food6);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));
        assertTrue(listFoods.contains(food4));
        assertTrue(listFoods.contains(food5));
        assertTrue(listFoods.contains(food6));

        assertTrue(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 1);

        assertTrue(lofi.getVegetableList().contains(food2));
        assertEquals(lofi.getVegetableList().size(), 1);

        assertFalse(lofi.getProteinList().contains(food1));
        assertFalse(lofi.getProteinList().contains(food2));
        assertTrue(lofi.getProteinList().contains(food3));
        assertEquals(lofi.getProteinList().size(), 1);

        assertTrue(lofi.getGrainList().contains(food5));
        assertTrue(lofi.getGrainList().contains(food6));
        assertEquals(lofi.getGrainList().size(), 2);

        assertTrue(lofi.getDairyList().contains(food4));
        assertEquals(lofi.getDairyList().size(), 1);
    }

    @Test
    void testRemoveFoodSingle() {
        lofi.addFood(food1);
        assertEquals(listFoods.get(0), food1);
        assertTrue(listFoods.contains(food1));
        assertEquals(listFoods.size(), 1);
        assertFalse(listFoods.isEmpty());

        assertTrue(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 1);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertEquals(lofi.getVegetableList().size(), 0);

        assertFalse(lofi.getProteinList().contains(food1));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertEquals(lofi.getDairyList().size(), 0);

        lofi.removeFood(food1);
        assertEquals(listFoods.size(), 0);
        assertTrue(listFoods.isEmpty());

        assertFalse(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 0);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertEquals(lofi.getVegetableList().size(), 0);

        assertFalse(lofi.getProteinList().contains(food1));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertEquals(lofi.getDairyList().size(), 0);
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

        assertTrue(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 1);

        assertTrue(lofi.getVegetableList().contains(food2));
        assertEquals(lofi.getVegetableList().size(), 1);

        assertFalse(lofi.getProteinList().contains(food1));
        assertFalse(lofi.getProteinList().contains(food2));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertFalse(lofi.getGrainList().contains(food2));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertFalse(lofi.getDairyList().contains(food2));
        assertEquals(lofi.getDairyList().size(), 0);

        lofi.removeFood(food1);
        assertEquals(listFoods.size(), 1);
        assertEquals(listFoods.get(0), food2);
        assertTrue(listFoods.contains(food2));
        assertFalse(listFoods.contains(food1));
        assertFalse(listFoods.isEmpty());

        assertFalse(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 0);

        assertTrue(lofi.getVegetableList().contains(food2));
        assertEquals(lofi.getVegetableList().size(), 1);

        assertFalse(lofi.getProteinList().contains(food1));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertEquals(lofi.getDairyList().size(), 0);
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

        assertFalse(lofi.getFruitList().contains(food1));
        assertFalse(lofi.getFruitList().contains(food2));
        assertEquals(lofi.getFruitList().size(), 0);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertTrue(lofi.getVegetableList().contains(food2));
        assertEquals(lofi.getVegetableList().size(), 1);

        assertFalse(lofi.getProteinList().contains(food1));
        assertFalse(lofi.getProteinList().contains(food2));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertFalse(lofi.getGrainList().contains(food2));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertFalse(lofi.getDairyList().contains(food2));
        assertEquals(lofi.getDairyList().size(), 0);

        lofi.removeFood(food2);
        assertEquals(listFoods.size(), 0);
        assertTrue(listFoods.isEmpty());
        assertFalse(listFoods.contains(food1));
        assertFalse(listFoods.contains(food2));

        assertFalse(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 0);

        assertFalse(lofi.getVegetableList().contains(food2));
        assertEquals(lofi.getVegetableList().size(), 0);

        assertFalse(lofi.getProteinList().contains(food1));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertEquals(lofi.getDairyList().size(), 0);
    }

    @Test
    void removeFoodGroups() {
        FoodItems food3 = new FoodItems("Pasta", 20, GRAIN);
        FoodItems food4 = new FoodItems("Beef", 70, PROTEIN);
        FoodItems food5 = new FoodItems("Milk", 10, DAIRY);

        lofi.addFood(food1);
        lofi.addFood(food2);
        lofi.addFood(food3);
        lofi.addFood(food4);
        lofi.addFood(food5);

        assertTrue(lofi.getFruitList().contains(food1));
        assertFalse(lofi.getFruitList().contains(food2));
        assertFalse(lofi.getFruitList().contains(food3));
        assertFalse(lofi.getFruitList().contains(food4));
        assertFalse(lofi.getFruitList().contains(food5));
        assertEquals(lofi.getFruitList().size(), 1);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertTrue(lofi.getVegetableList().contains(food2));
        assertFalse(lofi.getVegetableList().contains(food3));
        assertFalse(lofi.getVegetableList().contains(food4));
        assertFalse(lofi.getVegetableList().contains(food5));
        assertEquals(lofi.getVegetableList().size(), 1);

        assertFalse(lofi.getProteinList().contains(food1));
        assertFalse(lofi.getProteinList().contains(food2));
        assertFalse(lofi.getProteinList().contains(food3));
        assertTrue(lofi.getProteinList().contains(food4));
        assertFalse(lofi.getProteinList().contains(food5));
        assertEquals(lofi.getProteinList().size(), 1);

        assertFalse(lofi.getGrainList().contains(food1));
        assertFalse(lofi.getGrainList().contains(food2));
        assertTrue(lofi.getGrainList().contains(food3));
        assertFalse(lofi.getGrainList().contains(food4));
        assertFalse(lofi.getGrainList().contains(food5));
        assertEquals(lofi.getGrainList().size(), 1);

        assertFalse(lofi.getDairyList().contains(food1));
        assertFalse(lofi.getDairyList().contains(food2));
        assertFalse(lofi.getDairyList().contains(food3));
        assertFalse(lofi.getDairyList().contains(food4));
        assertTrue(lofi.getDairyList().contains(food5));
        assertEquals(lofi.getDairyList().size(), 1);

        lofi.removeFood(food1);
        lofi.removeFood(food2);
        lofi.removeFood(food3);
        lofi.removeFood(food4);
        lofi.removeFood(food5);

        assertFalse(lofi.getFruitList().contains(food1));
        assertFalse(lofi.getFruitList().contains(food2));
        assertFalse(lofi.getFruitList().contains(food3));
        assertFalse(lofi.getFruitList().contains(food4));
        assertFalse(lofi.getFruitList().contains(food5));
        assertEquals(lofi.getFruitList().size(), 0);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertFalse(lofi.getVegetableList().contains(food2));
        assertFalse(lofi.getVegetableList().contains(food3));
        assertFalse(lofi.getVegetableList().contains(food4));
        assertFalse(lofi.getVegetableList().contains(food5));
        assertEquals(lofi.getVegetableList().size(), 0);

        assertFalse(lofi.getProteinList().contains(food1));
        assertFalse(lofi.getProteinList().contains(food2));
        assertFalse(lofi.getProteinList().contains(food3));
        assertFalse(lofi.getProteinList().contains(food4));
        assertFalse(lofi.getProteinList().contains(food5));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertFalse(lofi.getGrainList().contains(food2));
        assertFalse(lofi.getGrainList().contains(food3));
        assertFalse(lofi.getGrainList().contains(food4));
        assertFalse(lofi.getGrainList().contains(food5));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertFalse(lofi.getDairyList().contains(food2));
        assertFalse(lofi.getDairyList().contains(food3));
        assertFalse(lofi.getDairyList().contains(food4));
        assertFalse(lofi.getDairyList().contains(food5));
        assertEquals(lofi.getDairyList().size(), 0);
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

        assertTrue(lofi.getFruitList().contains(food1));
        assertEquals(lofi.getFruitList().size(), 1);

        assertTrue(lofi.getVegetableList().contains(food2));
        assertEquals(lofi.getVegetableList().size(), 1);

        assertFalse(lofi.getProteinList().contains(food1));
        assertFalse(lofi.getProteinList().contains(food2));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertFalse(lofi.getGrainList().contains(food2));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertFalse(lofi.getDairyList().contains(food2));
        assertEquals(lofi.getDairyList().size(), 0);

        lofi.clearList();
        assertTrue(listFoods.isEmpty());
        assertEquals(lofi.totalNumOfCalories(), 0);
        assertTrue(listFoods.isEmpty());
        assertFalse(listFoods.contains(food1));
        assertFalse(listFoods.contains(food2));

        assertFalse(lofi.getFruitList().contains(food1));
        assertFalse(lofi.getFruitList().contains(food2));
        assertEquals(lofi.getFruitList().size(), 0);

        assertFalse(lofi.getVegetableList().contains(food1));
        assertFalse(lofi.getVegetableList().contains(food2));
        assertEquals(lofi.getVegetableList().size(), 0);

        assertFalse(lofi.getProteinList().contains(food1));
        assertFalse(lofi.getProteinList().contains(food2));
        assertEquals(lofi.getProteinList().size(), 0);

        assertFalse(lofi.getGrainList().contains(food1));
        assertFalse(lofi.getGrainList().contains(food2));
        assertEquals(lofi.getGrainList().size(), 0);

        assertFalse(lofi.getDairyList().contains(food1));
        assertFalse(lofi.getDairyList().contains(food2));
        assertEquals(lofi.getDairyList().size(), 0);
    }

    @Test
    void testTotalFruitCalories() {
        FoodItems food3 = new FoodItems("Apple", 20, FRUIT);
        FoodItems food4 = new FoodItems("Orange", 15, FRUIT);
        FoodItems food5 = new FoodItems("Pasta", 70, GRAIN);

        listFoods.add(food1);
        listFoods.add(food2);
        listFoods.add(food3);
        listFoods.add(food4);
        listFoods.add(food5);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));
        assertTrue(listFoods.contains(food4));
        assertTrue(listFoods.contains(food5));

        assertEquals(lofi.totalFruitCalories(), 105);
    }

    @Test
    void testTotalFruitCaloriesNone() {
        FoodItems food5 = new FoodItems("Pasta", 70, GRAIN);

        listFoods.add(food5);
        assertTrue(listFoods.contains(food5));

        assertEquals(lofi.totalFruitCalories(), 0);
    }

    @Test
    void testTotalVegetableCalories() {
        FoodItems food3 = new FoodItems("Apple", 20, FRUIT);
        FoodItems food4 = new FoodItems("Lettuce", 10, VEGETABLE);
        FoodItems food5 = new FoodItems("Pasta", 70, GRAIN);

        listFoods.add(food1);
        listFoods.add(food2);
        listFoods.add(food3);
        listFoods.add(food4);
        listFoods.add(food5);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));
        assertTrue(listFoods.contains(food4));
        assertTrue(listFoods.contains(food5));

        assertEquals(lofi.totalVegetableCalories(), 40);
    }

    @Test
    void testTotalVegetableCaloriesNone() {
        FoodItems food5 = new FoodItems("Pasta", 70, GRAIN);

        listFoods.add(food5);
        assertTrue(listFoods.contains(food5));

        assertEquals(lofi.totalVegetableCalories(), 0);
    }

    @Test
    void testTotalProteinCalories() {
        FoodItems food3 = new FoodItems("Apple", 20, FRUIT);
        FoodItems food4 = new FoodItems("Chicken", 100, PROTEIN);
        FoodItems food5 = new FoodItems("Beef", 50, PROTEIN);

        listFoods.add(food1);
        listFoods.add(food2);
        listFoods.add(food3);
        listFoods.add(food4);
        listFoods.add(food5);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));
        assertTrue(listFoods.contains(food4));
        assertTrue(listFoods.contains(food5));

        assertEquals(lofi.totalProteinCalories(), 150);
    }

    @Test
    void testTotalProteinCaloriesNone() {
        FoodItems food3 = new FoodItems("Apple", 20, FRUIT);

        listFoods.add(food1);
        listFoods.add(food2);
        listFoods.add(food3);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));

        assertEquals(lofi.totalProteinCalories(), 0);
    }

    @Test
    void testTotalGrainCalories() {
        FoodItems food3 = new FoodItems("Pasta", 20, GRAIN);
        FoodItems food4 = new FoodItems("Rice", 50, GRAIN);
        FoodItems food5 = new FoodItems("Noodles", 50, GRAIN);

        listFoods.add(food1);
        listFoods.add(food2);
        listFoods.add(food3);
        listFoods.add(food4);
        listFoods.add(food5);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));
        assertTrue(listFoods.contains(food4));
        assertTrue(listFoods.contains(food5));

        assertEquals(lofi.totalGrainCalories(), 120);
    }

    @Test
    void testTotalGrainCaloriesNone() {

        listFoods.add(food1);
        listFoods.add(food2);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));

        assertEquals(lofi.totalGrainCalories(), 0);
    }

    @Test
    void testTotalDairyCalories() {
        FoodItems food3 = new FoodItems("Milk", 20, DAIRY);
        FoodItems food4 = new FoodItems("Rice", 50, GRAIN);
        FoodItems food5 = new FoodItems("Yogurt", 50, DAIRY);

        listFoods.add(food1);
        listFoods.add(food2);
        listFoods.add(food3);
        listFoods.add(food4);
        listFoods.add(food5);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));
        assertTrue(listFoods.contains(food4));
        assertTrue(listFoods.contains(food5));

        assertEquals(lofi.totalDairyCalories(), 70);
    }

    @Test
    void testTotalDairyCaloriesNone() {
        FoodItems food3 = new FoodItems("Rice", 50, GRAIN);

        listFoods.add(food1);
        listFoods.add(food2);
        listFoods.add(food3);
        assertTrue(listFoods.contains(food1));
        assertTrue(listFoods.contains(food2));
        assertTrue(listFoods.contains(food3));

        assertEquals(lofi.totalDairyCalories(), 0);
    }

    @Test
    void testSameDay() {
        assertTrue(lofi.sameDay(LocalDate.now()));
        assertFalse(lofi.sameDay(LocalDate.of(2021, 12, 25)));
    }
}