// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.Writeable;

import java.time.LocalDate;

// creates a list of all the exercises added
public class ListExercise implements Writeable {

    private List<Exercise> exercises;
    private LocalDate date;

    // EFFECTS: constructs list of exercise with today's date associate with it
    public ListExercise() {
        exercises = new ArrayList<Exercise>();
        date = LocalDate.now();
    }

    // EFFECTS: returns list of Exercise
    public List<Exercise> getListExercise() {
        return exercises;
    }

    // EFFECTS: returns total number of calories burnt
    public int getTotalCaloriesBurnt() {
        int calories = 0;
        for (Exercise e : exercises) {
            calories += e.getCaloriesBurned();
        }
        return calories;
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to the list
    public void addExercise(Exercise e) {
        exercises.add(e);
        EventLog.getInstance().logEvent(new Event("An exercise has been added"));
    }

    // MODIFIES: this
    // EFFECTS: removes exercise to the list
    public void removeExercise(Exercise e) {
        exercises.remove(e);
        EventLog.getInstance().logEvent(new Event("An exercise has been removed"));
    }

    // EFFECTS: returns date of list
    public LocalDate getDate() {
        return date;
    }

    // MODIFIES: this
    // EFFECTS: sets date of list to today's list
    public void setDate() {
        date = LocalDate.now();
    }

    // MODIFIES: this
    // EFFECTS: clears list
    public void clearList() {
        exercises.clear();
        EventLog.getInstance().logEvent(new Event("An exercise list has been cleared"));
    }

    // EFFECTS: returns size of list
    public int sizeList() {
        return exercises.size();
    }

    // EFFECTS: returns as a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Exercises", exercisesToJson());
        return json;
    }

    // EFFECTS: returns things in list of exercise as JSON array
    private JSONArray exercisesToJson() {
        JSONArray array = new JSONArray();
        for (Exercise e : exercises) {
            array.put(e.toJson());
        }

        return array;
    }
}
