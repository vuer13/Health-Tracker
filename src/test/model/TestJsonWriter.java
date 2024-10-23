package model;

import persistance.JsonWriter;

import static model.FoodGroup.FRUIT;
import static model.FoodGroup.PROTEIN;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestJsonWriter {
    
    private ListOfFoodItems lofi;
    private ListExercise loe; 

    @BeforeEach
    void setUp() {
        lofi = new ListOfFoodItems();
        loe = new ListExercise();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/illegal:invalid/filename.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListExercise() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListExercise.json");
            writer.open();
            writer.write(loe, lofi);
            writer.close();

            // TODO: Requires Reader
        } catch (IOException e) {
            fail("Exception is wrongfully thrown");
        }
    }

    @Test
    void testWriterEmptyListFoodItems() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListFoodItems.json");
            writer.open();
            writer.write(loe, lofi);
            writer.close();

            // TODO: Requires Reader
        } catch (IOException e) {
            fail("Exception is wrongfully thrown");
        }
    }

    @Test
    void testWriterGeneralListExercise() {
        try {
            Exercise e1 = new Exercise("Push Ups", 10);
            Exercise e2 = new Exercise("Sit Ups", 20);
            loe.addExercise(e1);
            loe.addExercise(e2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListExercise.json");
            writer.open();
            writer.write(loe, lofi);
            writer.close();

            // TODO: Requires Reader
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListFoodItem() {
        try {
            FoodItems f1 = new FoodItems("Apple", 15, FRUIT);
            FoodItems f2 = new FoodItems("Beef", 30, PROTEIN);
            lofi.addFood(f1);
            lofi.addFood(f2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListFoodItem.json");
            writer.open();
            writer.write(loe, lofi);
            writer.close();

            // TODO: Requires Reader
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
