import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lhi06 on 20/06/2017.
 */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(final String[] args) {

        final Restaurant restaurant = new Restaurant();
        restaurant.initRestaurant();
        System.out.println("Hello guys, its a funky pizza parlour!");

        while (true) {

            printOptions();

            int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    printTables(restaurant);
                    break;
                case 2:
                    printFood(restaurant);
                    break;
                case 3:
                    printDrink(restaurant);
                    break;
                case 4:
                    addCustomer(restaurant);
                    break;
                case 5:
                    removeCustomer(restaurant);
                    break;
                case 6:
                    makeOrder(restaurant);
                    break;
                case 7:
                    printOrders(restaurant);
                    break;
                case 8:
                    addItemToOrder(restaurant);
                    break;
                case 9:
                    saveTables(restaurant);
                    break;
                case 10:
                    loadTables(restaurant);
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }
        }
    }

    private static void printOptions() {
        System.out.println("\nSelecto an optiono:");
        System.out.println("0. Exit");
        System.out.println("1. Print Tables");
        System.out.println("2. Print Food Menu");
        System.out.println("3. Print Drink Menu");
        System.out.println("4. Add a Customer");
        System.out.println("5. Remove a Customer");
        System.out.println("6. Make an Order");
        System.out.println("7. Print Current Orders");
        System.out.println("8. Add Items to and Existing Order");
        System.out.println("9. Save Current Data");
        System.out.println("10. Load Past Data");

    }

    public static void printTables(Restaurant restaurant) {
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            Table table = restaurant.getTables().get(i);
            String status = "Unoccupied";
            if (table.getCustomer() != null) {
                status = "Occupied by " + table.getCustomer().getName();
            }
            int e = i + 1;
            System.out.println(e + ". Table no " + table.getTableNumber() + " - " + status);
        }
    }

    public static void printFood(Restaurant restaurant) {
        restaurant.getWaiter().getMenu().listFoodMenu();
    }

    public static void printDrink(Restaurant restaurant) {
        restaurant.getWaiter().getMenu().listDrinkMenu();
    }

    public static void printOrders(Restaurant restaurant) {
        for (int i = 1; i < restaurant.getTables().size(); i++) {
            if (restaurant.getTables().get(i).getCustomer() == null || restaurant.getTables().get(i).getCustomer().getOrder() == null) {
                System.out.println(i + ". Table no " + restaurant.getTables().get(i).getTableNumber() + " - Currently has no order.");
            } else {
                System.out.println(i + ". Table no " + restaurant.getTables().get(i).getTableNumber() + " has the following order:");
                restaurant.getTables().get(i).getCustomer().getOrder().orderSummary();
            }
        }
    }

    public static void addCustomer(Restaurant restaurant) {
        System.out.println("What is this persons name?");
        final String name = SCANNER.next();
        System.out.println("How old is this person?");
        final int age = SCANNER.nextInt();
        printTables(restaurant);
        System.out.println("And what table would you like to sit at? (You can only have one person per table)");
        final int table = SCANNER.nextInt() - 1;
        restaurant.getTables().get(table).addCustomer(new Customer(name, age));
    }

    public static void removeCustomer(Restaurant restaurant) {
        printTables(restaurant);
        System.out.println("Which table should be vacated?");
        int nominated = SCANNER.nextInt() - 1;
        restaurant.getTables().get(nominated).removeCustomer();
        nominated++;
        System.out.println("Table no " + nominated + " has been vacated.");
    }

    public static void makeOrder(Restaurant restaurant) {
        int table;
        while (true) {
            System.out.println("Which table would you like to make an order to? (Select an Option)");
            printTables(restaurant);
            table = SCANNER.nextInt() - 1;
            if (restaurant.getTables().get(table).getCustomer() == null) {
                System.out.println("This table is Unoccupied, please select an Occupied table.");

            } else {
                break;
            }
        }

        Order custOrder = new Order(restaurant.getTables().get(table));
        restaurant.getTables().get(table).getCustomer().setOrder(custOrder);

        System.out.println("What would you like to order? (Press the corresponding button to add it to your order)");
        System.out.println("0.Proceed to Ordering Drinks");
        printFood(restaurant);
        while (true) {

            final int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }



            switch (input) {
                case 1:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(0));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(0).getName() + " was added to your order.");
                    break;
                case 2:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(1));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(1).getName() + " was added to your order.");
                    break;
                case 3:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(2));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(2).getName() + " was added to your order.");
                    break;
                case 4:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(3));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(3).getName() + " was added to your order.");
                    break;
                case 5:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(4));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(4).getName() + " was added to your order.");
                    break;
                case 6:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(5));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(5).getName() + " was added to your order.");
                    break;
                case 7:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(6));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(6).getName() + " was added to your order.");
                    break;
                case 8:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(7));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(7).getName() + " was added to your order.");
                    break;
                case 9:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(8));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(8).getName() + " was added to your order.");
                    break;
                case 10:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(9));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(9).getName() + " was added to your order.");
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }

        }
        System.out.println("What would you like to drink? (Press the corresponding button to add it to your order)");
        System.out.println("0.Finish your Order");
        printDrink(restaurant);
        while (true) {

            final int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(0));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(0).getName() + " was added to your order.");
                    break;
                case 2:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(1));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(1).getName() + " was added to your order.");
                    break;
                case 3:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(2));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(2).getName() + " was added to your order.");
                    break;
                case 4:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(3));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(3).getName() + " was added to your order.");
                    break;
                case 5:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(4));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(4).getName() + " was added to your order.");
                    break;
                case 6:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(5));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(5).getName() + " was added to your order.");
                    break;
                case 7:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(6));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(6).getName() + " was added to your order.");
                    break;
                case 8:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(7));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(7).getName() + " was added to your order.");
                    break;
                case 9:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(8));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(8).getName() + " was added to your order.");
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }
        }
        System.out.println("Here is the order:");
        restaurant.getTables().get(table).getCustomer().getOrder().orderSummary();
    }

    public static void addItemToOrder(Restaurant restaurant) {
        int table;
        while (true) {
            System.out.println("Which table order would you like to add to? (Select an Option)");
            printTables(restaurant);
            table = SCANNER.nextInt() - 1;
            if (restaurant.getTables().get(table).getCustomer() == null) {
                System.out.println("This table is Unoccupied, please select an Occupied table.");

            } else {
                break;
            }
        }

        Order custOrder = new Order(restaurant.getTables().get(table));
        restaurant.getTables().get(table).getCustomer().setOrder(custOrder);

        System.out.println("What food would you like to add? (Press the corresponding button to add it to your order)");
        System.out.println("0.Proceed to Ordering Drinks");
        printFood(restaurant);
        while (true) {

            final int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }



            switch (input) {
                case 1:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(0));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(0).getName() + " was added to your order.");
                    break;
                case 2:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(1));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(1).getName() + " was added to your order.");
                    break;
                case 3:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(2));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(2).getName() + " was added to your order.");
                    break;
                case 4:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(3));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(3).getName() + " was added to your order.");
                    break;
                case 5:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(4));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(4).getName() + " was added to your order.");
                    break;
                case 6:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(5));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(5).getName() + " was added to your order.");
                    break;
                case 7:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(6));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(6).getName() + " was added to your order.");
                    break;
                case 8:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(7));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(7).getName() + " was added to your order.");
                    break;
                case 9:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(8));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(8).getName() + " was added to your order.");
                    break;
                case 10:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getFoodMenu().get(9));
                    System.out.println(restaurant.getWaiter().getMenu().getFoodMenu().get(9).getName() + " was added to your order.");
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }

        }
        System.out.println("What drinks would you like to add? (Press the corresponding button to add it to your order)");
        System.out.println("0.Finish your Order");
        printDrink(restaurant);
        while (true) {

            final int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(0));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(0).getName() + " was added to your order.");
                    break;
                case 2:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(1));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(1).getName() + " was added to your order.");
                    break;
                case 3:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(2));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(2).getName() + " was added to your order.");
                    break;
                case 4:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(3));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(3).getName() + " was added to your order.");
                    break;
                case 5:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(4));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(4).getName() + " was added to your order.");
                    break;
                case 6:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(5));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(5).getName() + " was added to your order.");
                    break;
                case 7:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(6));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(6).getName() + " was added to your order.");
                    break;
                case 8:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(7));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(7).getName() + " was added to your order.");
                    break;
                case 9:
                    restaurant.getTables().get(table).getCustomer().getOrder().addItem(restaurant.getWaiter().getMenu().getDrinkMenu().get(8));
                    System.out.println(restaurant.getWaiter().getMenu().getDrinkMenu().get(8).getName() + " was added to your order.");
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }
        }
        System.out.println("Here is the updated order:");
        restaurant.getTables().get(table).getCustomer().getOrder().orderSummary();
    }

    public static void saveTables(Restaurant restaurant) {
        savePerson(restaurant.getTables());
    }

    public static void loadTables(Restaurant restaurant) {
        File pizzeria = new File("pizzeria.json");
        restaurant.setTables(loadPeople(pizzeria));
    }


    public static void savePerson(final List<Table> tables) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("restaurant.json"), tables);
            System.out.println("Saved current data.");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static List<Table> loadPeople(final File json) {
        List<Table> tables;
        try {
            ObjectMapper mapper = new ObjectMapper();
            tables = mapper.readValue(json, new TypeReference<List<Table>>(){});
            System.out.println("Loaded current data.");
        } catch(Exception e) {
            System.out.println(e);
            tables = null;
        }
        return tables;

    }
}
