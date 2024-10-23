// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistance;

import java.io.FileNotFoundException;

import model.ListExercise;
import model.ListOfFoodItems;

// Represents writer that writes JSON representation of list of food items/exercises to file
public class JsonWriter {
    
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: opens writer
    // throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of list of exercises to file
    public void writeExercise(ListExercise loe) {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of list of food items to file
    public void writeFoodItem(ListOfFoodItems lofi) {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        // TODO
    }
}
