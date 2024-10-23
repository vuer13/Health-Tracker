// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistance;

import java.io.IOException;

import javax.swing.JSpinner.ListEditor;

import org.json.JSONObject;

import model.ListExercise;
import model.ListOfFoodItems;

// Represents reader that reads list of exercise and food items from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // TODO
    }

    // EFFECTS: reads food items list from file and returns it;
    // throws IOException if error occurs reading data
    public ListOfFoodItems readFootItems() throws IOException {
        return null;
        // TODO
    }

    // EFFECTS: reads exercise list from file and returns it;
    // throws IOException if error occurs reading data
    public ListExercise readExercise() throws IOException {
        return null;
        // TODO
    }

    // EFFECTS: read source file as string and returns it
    private String readFile(String source) throws IOException {
        return "";
        // TODO
    }

    // EFFECTS: parses list of exercises from JSON object and returns it
    private ListExercise parseListExercise(JSONObject jsonObject) {
        return null;
        // TODO
    }

    // MODIFIES: loe
    // EFFECTS: parses exercises from JSON object and adds them to list of exercises
    private void addExercises(ListExercise loe, JSONObject jsonObject) {
        // TODO
    }

    // MODIFIES: loe
    // EFFECTS: parses exercises from JSON object and adds it to list of exercises
    private void addExercise(ListExercise loe, JSONObject jsonObject) {
        // TODO
    }

    // EFFECTS: parses list of food items from JSON object and returns it
    private ListOfFoodItems parseListFoodItems(JSONObject jsonObject) {
        return null;
        // TODO
    }

    // MODIFIES: lofi
    // EFFECTS: parses food items from JSON object and adds them to list of food items
    private void addFoodItems(ListOfFoodItems lofi, JSONObject jsonObject) {
        // TODO
    }

        // MODIFIES: lofi
    // EFFECTS: parses food items from JSON object and adds it to list of food items
    private void addFoodItem(ListOfFoodItems lofi, JSONObject jsonObject) {
        // TODO
    }

}
