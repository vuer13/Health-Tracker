# Health Tracker

## Resource for Healthy Lifestyle

This project is a calorie tracker that tracks the amount of calories you gain from food and the amount of calories you lose based on a variety of factors, including exercise and day-to-day walking. This application is open to all types of users. Those interested in understanding their daily calorie intake or wanting to live a healthier livestyle should definitely try this application. Others who have been going to the gym or meal prepping can also use this to track their calories. Users will be able to:
- Set goals for **daily** calorie intake and loss (calorie deficit) 
- Add **food items** to calculate the total amount of calorie intake 
- Add **exercises** done to calculate total calorie burned throughout the day
- Find the **difference** between the *calorie intake and loss*, and see if the goal has been *met*
- Keep track of the **food groups** eaten from

An active, healthy lifestyle can help individuals lead to an maximum performance and an optimal life. Counting calories is something extremely overlooked by the general population but important in our society today. As an athlete myself, I understand performance not only on the court but at a workplace or school can depend on our diet and activity. I strongly believe a health tracker like this can help people set goals and achieve them. The goal of this project is for people to start caring for their calorie intake and find ways to achieve their optimal lifestyle.

## User Stories
- As a User, I want to be able to create food items that has the number of calories and the food groups involved 
- As a User, I want to add multiple food items to a list of food items and a list containing the food group it is part of
- As a User, I want to be able to create exercies with the number of calories it burns 
- As a User, I want to add multiple exercises to a list of exercies
- As a User, I want to set a daily calorie goal that I have to reach 
- As a User, I want to view the total amount of calories consumed and burned, and the difference between the two
- As a User, I want to view the amount of calories required to reach the goal set for the day
- As a User, I want to view the amount of items in each Food Group.
- As a User, I want to view a list all of the names and calories for each food item added in my list of food items
- As a User, I want to view a list of all the names and calories for each food group in the list
- As a User, I want to view a list all of the names and calories burned for each exercise added in my list of exercises
- As a User, I want to be able to delete food items or exercises from the lists
- As a User, I want the option to save my food items & exercise lists, as well as my calorie goal (if I choose to do so)
- As a User, I want the option to load the my previous lists, and calorie goal when I start the application (if I choose to do so)

# Instructions for the User
- You can generate the first required action (adding) related to the user story "adding multiple Food Items to a List of Food Items" by clicking the "add food (+)" button in the main menu, and inputting the name, amount of calories and food group, and clicking "add food (+)"
- You can generate the first required action (adding) related to the user story "adding multiple Exercises to a List of Exercises" by clicking the "add exercise (+)" button in the main menu, and inputting the name and calories burnt, and click "add exercise (+)"
- You can generate the second required action (removing) related to the user story "adding multiple Food Items to a List of Food Items" by clicking on the item you want to remove in the list (must have all food items selected in the drop-down menu), and clicking the "remove food (-)" button
- You can generate the second required action (removing) related to the user story "adding multiple Exercises to a List of Exercises" by clicking on the item you want to remove in the list, and clicking the "remove exercise (-)" button
- You can filter out the list of food items by food group simply by clicking the drop down menu and selecting the food group of your choice
- You can see the statistics of the food items added, exercises added and how they compare to your calorie goal by clicking "food statistics", "exercise statistics" and "calorie statistics" respectively
- You can locate my visual component in a variety of places, in particular:
    - When starting the application, a loading icon will show up in the pop up menu 
    - When closing the application, a save icon will show up in the pop up menu
    - There are visual components in the add food panel and the add exercise panel
    - When opening up the statistics for each category (food items, exercise and calories)
    - When setting your calorie goal
    - When commiting an error (ie. not selecting a item to remove, insufficient inputs when adding an item)
- You can load the state by clicking "yes" when the pop up menu shows up when you begin the tracker
- You can save the state by clicking "yes" when the pop up menu shows up when you close the tracker 

# Phase 4: Task 2
A calorie goal was set

A fruit has been added to the food items list

An exercise has been added

A protein has been added to the food items list

An exercise has been added

A protein has been removed to the food items list

An exercise has been removed

The list of exercises has been saved

The calorie goal has been saved

The list of food items has been saved


# Phase 4: Task 3
After reflecting on my UML diagram, implementing the Singleton Pattern was something that crossed my mind. This would ensure that the program only accesses one instance of the Calories class, List of Food Items class, and List of Exercises class. This would allow us to avoid any inconsistencies, and help improve memory usage and runtime. This would also ensure that we are only accessing one instance, and as a result, keeping an accurate track of our calories.

However, many issues can arise from this. Although it would help my program at it's current state currently, future modifications of the program can be affected by the Singleton Pattern. If I wanted to calorie track every day, that would require a map, and thus multiple instances of the Calories class, List of Food Items class, and List of Exercises class, as I would map it to each day. Having the program access one class would not allow me to add this feature to this program. Testing would also become more difficult since the singleton object is shared across the whole program. Changing the state while testing can affect how the program runs, which makes it difficult to write independent tests to make sure our methods are correctly implemented. 

* This project was created as part of the CPSC 210 course at the University of British Columbia. 