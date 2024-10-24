package persistance;

import persistance.JsonWriter;

import static model.FoodGroup.FRUIT;
import static model.FoodGroup.PROTEIN;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Exercise;
import model.FoodItems;
import model.ListExercise;
import model.ListOfFoodItems;

import org.junit.jupiter.api.BeforeEach;

public class TestJsonWriter extends TestJson {
    
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
            writer.writeExercise(loe);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListExercise.json");
            loe = reader.readExercise();
            assertEquals(0, loe.getListExercise().size());
        } catch (IOException e) {
            fail("Exception is wrongfully thrown");
        }
    }

    @Test
    void testWriterEmptyListFoodItems() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListFoodItems.json");
            writer.open();
            writer.writeFood(lofi);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListFoodItems.json");
            lofi = reader.readFootItems();
            assertEquals(0, lofi.getListOfFoodItems().size());
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
            writer.writeExercise(loe);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListExercise.json");
            loe = reader.readExercise();
            List<Exercise> exs = loe.getListExercise();
            checkExercise("Push Ups", 10, exs.get(0));
            checkExercise("Sit Ups", 20, exs.get(1));
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
            writer.writeFood(lofi);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListFoodItem.json");
            lofi = reader.readFootItems();
            List<FoodItems> fis = lofi.getListOfFoodItems();
            checkFoodItem("Apple", 15, FRUIT, fis.get(0));
            checkFoodItem("Beef", 30, PROTEIN, fis.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
