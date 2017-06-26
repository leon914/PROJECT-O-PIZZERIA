import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Menu {

     private final List<Food> foodMenu = new ArrayList<Food>() {{
         add(new Food("Margherita Pizza", 7.00));
         add(new Food("Pepperoni Pizza", 8.00));
         add(new Food("Hawaiian  Pizza", 8.50));
         add(new Food("Lucio Special Pizza", 10.00));
         add(new Food("Kebab Pizza", 8.00));
         add(new Food("Chips", 1.50));
         add(new Food("Cheesy Chips", 2.00));
         add(new Food("Garlic Bread", 3.00));
         add(new Food("Spaghetti Bolognese", 8.25));
         add(new Food("Lasagna", 9.25));
     }};
     private final List<Drink> drinkMenu = new ArrayList<Drink>()  {{
         add(new Drink("Tap Water", 0.00));
         add(new Drink("Bottled Water", 0.50));
         add(new Drink("Orange Juice", 1.20));
         add(new Drink("Apple Juice", 1.20));
         add(new Drink("Pineapple Juice", 1.20));
         add(new Drink("San Pellegrino", 1.50));
         add(new Drink("Peroni", 2.50));
         add(new Drink("Red Wine", 4.50));
         add(new Drink("White Wine", 4.50));
     }};

     public List<Food> getFoodMenu() {
         return this.foodMenu;
     }

    public List<Drink> getDrinkMenu() {
        return this.drinkMenu;
    }

     public void addFood(Food food) {
         foodMenu.add(food);
     }

    public void addDrink(Drink drink) {
        drinkMenu.add(drink);
    }

    public void listFoodMenu() {
        DecimalFormat df = new DecimalFormat("#.00");
         int i = 1;
        for (Food food : getFoodMenu()) {
            System.out.println(i + "." + food.getName() + " £" + df.format(food.getPrice()));
            i++;
        }
    }

    public void listDrinkMenu() {
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (Drink drink : getDrinkMenu()) {
            System.out.println(i + "." + drink.getName() + " £" + df.format(drink.getPrice()));
            i++;
        }
    }
}
