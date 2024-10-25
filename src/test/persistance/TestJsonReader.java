// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistance;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Calories;
import model.Exercise;
import model.FoodGroup;
import model.FoodItems;
import model.ListExercise;
import model.ListOfFoodItems;

public class TestJsonReader extends TestJson {
    
    @Test
    void testReaderNoFileFoodItems() {
        JsonReader reader = new JsonReader("./data/fileNotRealFood.json");
        try {
            ListOfFoodItems lofi = reader.readFootItems();
            fail("Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoFileExercise() {
        JsonReader reader = new JsonReader("./data/fileNotRealEx.json");
        try {
            ListExercise lofi = reader.readExercise();
            fail("Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFoodItems() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListFoodItem.json");
        try {
            ListOfFoodItems lofi = reader.readFootItems();
            assertEquals(0, lofi.getListOfFoodItems().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyExercises() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListExercises.json");
        try {
            ListExercise loe = reader.readExercise();
            assertEquals(0, loe.getListExercise().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test 
    void testReaderGeneralListFoodItems() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListFoodItems.json");
        try {
            ListOfFoodItems lofi = reader.readFootItems();
            List<FoodItems> fi = lofi.getListOfFoodItems();
            assertEquals(2, fi.size());
            checkFoodItem("Beef", 30, FoodGroup.PROTEIN, fi.get(0));
            checkFoodItem("Apple", 10, FoodGroup.FRUIT, fi.get(1));
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }

    @Test
    void testReaderGeneralListExercise() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListExercise.json");

        try {
            ListExercise loe = reader.readExercise();
            List<Exercise> e = loe.getListExercise();
            assertEquals(2, e.size());
            checkExercise("Sit Ups", 30, e.get(0));
            checkExercise("Push Ups", 5, e.get(1));
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }

    @Test
    void testReaderEmptyCalories() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCalories.json");
        try {
            Calories e = reader.readCalories();
            assertNull(e.getCalorieGoal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test 
    void testReaderGeneralCalories() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCalories.json");
        try {
            Calories c = reader.readCalories();
            checkCalories(200, c);
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }
}
