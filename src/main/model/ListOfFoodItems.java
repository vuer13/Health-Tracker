package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfFoodItems {

    private List<FoodItems> listFoodItems;

    // EFFECTS : constructs empty list of food items
    public ListOfFoodItems() {
        listFoodItems = new ArrayList<FoodItems>();
    }

    // MODIFIES: this
    // EFFECTS: adds food to list of food items
    public void addFood(FoodItems food) {
        listFoodItems.add(food);
    }

    // MODIFIES: this
    // EFFECTS: removes food from list of food items
    public void removeFood(FoodItems food) {
        listFoodItems.remove(food);
    }

    // EFFECTS: returns list of food items
    public List<FoodItems> getListOfFoodItems() {
        return listFoodItems;
    }

    // EFFECTS: returns number of food items consumed today
    public int sizeOfList() {
        return listFoodItems.size();
    }

    // EFFECTS: returns total number of calories consumed
    public int totalNumOfCalories() {
        int calories = 0;
        for (FoodItems fi : listFoodItems) {
            calories += fi.getCalories();
        }
        return calories;
    }

    // EFFECTS: clears the list
    public void clearList() {
        listFoodItems.clear();
    }
}
