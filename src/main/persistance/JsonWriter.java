// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistance;

import java.io.*;
import org.json.JSONObject;

import model.ListExercise;
import model.ListOfFoodItems;

// Represents writer that writes JSON representation of list of food items/exercises to file
public class JsonWriter {
    
    private static final int TAB = 4;

    private String destination;
    private PrintWriter writer;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer
    // throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of list of food items to file
    public void write(ListOfFoodItems lofi) {
        JSONObject jsone = loe.toJson();
        JSONObject jsonf = lofi.toJson();
        saveToFile(jsone.toString(TAB));
        saveToFile(jsonf.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of list of exercises to file
    public void writeExercise(ListExercise loe) {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
