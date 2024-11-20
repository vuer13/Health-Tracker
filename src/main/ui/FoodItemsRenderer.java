package ui;

import javax.swing.*;

import model.FoodGroup;
import model.FoodItems;

import java.awt.*;

// Custom cell renderer for rendering FoodItems objects in JList
public class FoodItemsRenderer extends DefaultListCellRenderer {

    // MODIFIES: list
    // EFFECTS: Configures rendering for FoodItems objects in JList
    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        FoodItems food = (FoodItems) value;
        String name = food.getName();
        int cal = food.getCalories();
        FoodGroup group = food.getFoodGroup();
        String foodText = "Food: " + name + ", Calories: " + cal + ", Food Group: " + group;
        setText(foodText);
        return this;
    }

}
