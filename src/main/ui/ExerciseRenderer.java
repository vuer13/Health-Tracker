package ui;

import java.awt.*;
import javax.swing.*;

import model.Exercise;

// Custom cell renderer for rendering Exercise objects in JList
public class ExerciseRenderer extends DefaultListCellRenderer {

    // MODIFIES: list
    // EFFECTS: Configures rendering for FoodItems objects in JList
    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Exercise ex = (Exercise) value;
        String name = ex.getExercise();
        int cal = ex.getCaloriesBurned();
        String exerciseString = "Name: " + name + ", Calories Burnt: " + cal;
        setText(exerciseString);
        return this;
    }

}
