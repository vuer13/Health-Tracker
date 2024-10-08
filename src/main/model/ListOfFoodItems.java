package model;

import static model.FoodGroup.DAIRY;
import static model.FoodGroup.FRUIT;
import static model.FoodGroup.GRAIN;
import static model.FoodGroup.PROTEIN;
import static model.FoodGroup.VEGETABLE;

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

    // EFFECTS: returns total fruit calories consumed
    public int totalFruitCalories() {
        int fruitCalories = 0;
        for (FoodItems f : listFoodItems) {
            if (f.getFoodGroup() == FRUIT) {
                fruitCalories += f.getCalories();
            }
        }
        return fruitCalories;
    }

    // EFFECTS: returns total vegetable calories consumed
    public int totalVegetableCalories() {
        int vegatableCalories = 0;
        for (FoodItems f : listFoodItems) {
            if (f.getFoodGroup() == VEGETABLE) {
                vegatableCalories += f.getCalories();
            }
        }
        return vegatableCalories;
    }

    // EFFECTS: returns total fruit calories consumed
    public int totalProteinCalories() {
        int proteinCalories = 0;
        for (FoodItems f : listFoodItems) {
            if (f.getFoodGroup() == PROTEIN) {
                proteinCalories += f.getCalories();
            }
        }
        return proteinCalories;
    }

    // EFFECTS: returns total grain calories consumed
    public int totalGrainCalories() {
        int grainCalories = 0;
        for (FoodItems f : listFoodItems) {
            if (f.getFoodGroup() == GRAIN) {
                grainCalories += f.getCalories();
            }
        }
        return grainCalories;
    }

    // EFFECTS: returns total diary calories consumed
    public int totalDairyCalories() {
        int dairyCalories = 0;
        for (FoodItems f : listFoodItems) {
            if (f.getFoodGroup() == DAIRY) {
                dairyCalories += f.getCalories();
            }
        }
        return dairyCalories;
    }
}
