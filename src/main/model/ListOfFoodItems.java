// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package model;

import static model.FoodGroup.DAIRY;
import static model.FoodGroup.FRUIT;
import static model.FoodGroup.GRAIN;
import static model.FoodGroup.PROTEIN;
import static model.FoodGroup.VEGETABLE;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import log.Event;
import log.EventLog;
import persistance.Writeable;

import java.time.LocalDate;

// creates a list of all the food items created along with it's sub food groups
public class ListOfFoodItems implements Writeable {

    private List<FoodItems> listFoodItems;
    private List<FoodItems> fruitList;
    private List<FoodItems> vegetableList;
    private List<FoodItems> proteinList;
    private List<FoodItems> dairyList;
    private List<FoodItems> grainList;
    private LocalDate date;

    // EFFECTS : constructs empty list of food items, fruit list, vegetable list,
    // protein list, grain list and dairy list, and date of list
    public ListOfFoodItems() {
        listFoodItems = new ArrayList<FoodItems>();
        fruitList = new ArrayList<FoodItems>();
        vegetableList = new ArrayList<FoodItems>();
        proteinList = new ArrayList<FoodItems>();
        dairyList = new ArrayList<FoodItems>();
        grainList = new ArrayList<FoodItems>();
        this.date = LocalDate.now();
    }

    // MODIFIES: this
    // EFFECTS: adds food to list of food items and list of the food group it
    // belongs too
    public void addFood(FoodItems food) {
        listFoodItems.add(food);
        FoodGroup foodGroup = food.getFoodGroup();
        if (foodGroup == FRUIT) {
            fruitList.add(food);
            EventLog.getInstance().logEvent(new Event("A fruit has been added to the food items list"));
        } else if (foodGroup == VEGETABLE) {
            vegetableList.add(food);
            EventLog.getInstance().logEvent(new Event("A vegetable has been added to the food items list"));
        } else if (foodGroup == GRAIN) {
            grainList.add(food);
            EventLog.getInstance().logEvent(new Event("A grain has been added to the food items list"));
        } else if (foodGroup == DAIRY) {
            dairyList.add(food);
            EventLog.getInstance().logEvent(new Event("A dairy has been added to the food items list"));
        } else {
            proteinList.add(food);
            EventLog.getInstance().logEvent(new Event("A protein has been added to the food items list"));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes food from list of food items and list of food group it
    // belongs too
    public void removeFood(FoodItems food) {
        listFoodItems.remove(food);
        FoodGroup foodGroup = food.getFoodGroup();
        if (foodGroup == FRUIT) {
            fruitList.remove(food);
            EventLog.getInstance().logEvent(new Event("A fruit has been removed to the food items list"));
        } else if (foodGroup == VEGETABLE) {
            vegetableList.remove(food);
            EventLog.getInstance().logEvent(new Event("A vegetable has been removed to the food items list"));
        } else if (foodGroup == GRAIN) {
            grainList.remove(food);
            EventLog.getInstance().logEvent(new Event("A grain has been removed to the food items list"));
        } else if (foodGroup == DAIRY) {
            dairyList.remove(food);
            EventLog.getInstance().logEvent(new Event("A dairy has been removed to the food items list"));
        } else {
            proteinList.remove(food);
            EventLog.getInstance().logEvent(new Event("A protein has been removed to the food items list"));
        }
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

    // MODIFIES: this
    // EFFECTS: clears the list
    public void clearList() {
        listFoodItems.clear();
        fruitList.clear();
        vegetableList.clear();
        grainList.clear();
        proteinList.clear();
        dairyList.clear();
        EventLog.getInstance().logEvent(new Event("All food items have been cleared"));
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

    // EFFECTS: returns list of fruits added
    public List<FoodItems> getFruitList() {
        return fruitList;
    }

    // EFFECTS: returns list of vegetables added
    public List<FoodItems> getVegetableList() {
        return vegetableList;
    }

    // EFFECTS: returns list of protein added
    public List<FoodItems> getProteinList() {
        return proteinList;
    }

    // EFFECTS: returns list of grains added
    public List<FoodItems> getGrainList() {
        return grainList;
    }

    // EFFECTS: returns list of dairy added
    public List<FoodItems> getDairyList() {
        return dairyList;
    }

    // EFFECTS: return today's date
    public LocalDate getDate() {
        return date;
    }

    // MODIFIES: this
    // EFFECTS: sets date to today's date
    public void setDate() {
        date = LocalDate.now();
    }

    // EFFECTS: returns json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Food Items", foodItemsToJson());
        return json;
    }

    // EFFECTS: returns food items in list as JSON array
    private JSONArray foodItemsToJson() {
        JSONArray array = new JSONArray();
        for (FoodItems f : listFoodItems) {
            array.put(f.toJson());
        }
        return array;
    }
}
