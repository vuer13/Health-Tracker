// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistance;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Calories;
import model.Exercise;
import model.FoodGroup;
import model.FoodItems;
import model.ListExercise;
import model.ListOfFoodItems;

// Represents reader that reads list of exercise and food items from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads food items list from file and returns it;
    // throws IOException if error occurs reading data
    public ListOfFoodItems readFootItems() throws IOException {
        String data = readFile(source);
        JSONObject object = new JSONObject(data);
        return parseListFoodItems(object);
    }

    // EFFECTS: reads exercise list from file and returns it;
    // throws IOException if error occurs reading data
    public ListExercise readExercise() throws IOException {
        String data = readFile(source);
        JSONObject object = new JSONObject(data);
        return parseListExercise(object);
    }

    // EFFECTS: reads calories goal from file and returns it
    // throws IOException if error occurs reading data
    public Calories readCalories() throws IOException {
        String data = readFile(source);
        JSONObject object = new JSONObject(data);
        return parseCalories(object);
    }

    // EFFECTS: read source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }

    // EFFECTS: parses list of exercises from JSON object and returns it
    private ListExercise parseListExercise(JSONObject jsonObject) {
        ListExercise loe = new ListExercise();
        addExercises(loe, jsonObject);
        return loe;
    }

    // MODIFIES: loe
    // EFFECTS: parses exercises from JSON object and adds them to list of exercises
    private void addExercises(ListExercise loe, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Exercises");
        for (Object json : jsonArray) {
            JSONObject nextEx = (JSONObject) json;
            addExercise(loe, nextEx);
        }
    }

    // MODIFIES: loe
    // EFFECTS: parses exercises from JSON object and adds it to list of exercises
    private void addExercise(ListExercise loe, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        int calories = jsonObject.getInt("Calories");
        Exercise e = new Exercise(name, calories);
        loe.addExercise(e);
    }

    // EFFECTS: parses list of food items from JSON object and returns it
    private ListOfFoodItems parseListFoodItems(JSONObject jsonObject) {
        ListOfFoodItems lofi = new ListOfFoodItems();
        addFoodItems(lofi, jsonObject);
        return lofi;
    }

    // MODIFIES: lofi
    // EFFECTS: parses food items from JSON object and adds them to list of food items
    private void addFoodItems(ListOfFoodItems lofi, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Food Items");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFoodItem(lofi, nextFood);
        }
    }

    // MODIFIES: lofi
    // EFFECTS: parses food items from JSON object and adds it to list of food items
    private void addFoodItem(ListOfFoodItems lofi, JSONObject jsonObject) {
        String name = jsonObject.getString("Food");
        int calories = jsonObject.getInt("Calories");
        FoodGroup group = FoodGroup.valueOf(jsonObject.getString("Food Group"));
        FoodItems fi = new FoodItems(name, calories, group);
        lofi.addFood(fi);
    }

    // EFFECTS: parses calories from JSON object and returns it
    private Calories parseCalories(JSONObject jsonObject) {
        int cals = jsonObject.getInt("Calories");
        Calories c = new Calories(cals);
        return c;
    }
}
