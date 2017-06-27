import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Restaurant {

    private List<Table> tables = new ArrayList<>();

    private static final Scanner SCANNER = new Scanner(System.in);

    private final Waiter carlos = new Waiter("Carlos");

    public void initRestaurant() {

        for (int i = 1; i <= 6; i++) {
            tables.add(new Table(i));
        }

    }

    public void run() {

        System.out.println("Hello guys, its a funky pizza parlour!");

        while (true) {

            printOptions();

            int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    printTables();
                    break;
                case 2:
                    printFood();
                    break;
                case 3:
                    printDrink();
                    break;
                case 4:
                    addCustomer();
                    break;
                case 5:
                    removeCustomer();
                    break;
                case 6:
                    makeOrder();
                    break;
                case 7:
                    printOrders();
                    break;
                case 8:
                    addItemToOrder();
                    break;
                case 9:
                    saveTables();
                    break;
                case 10:
                    loadTables();
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }
        }
    }

    private void printOptions() {
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

    private void printTables() {
        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            String status = "Unoccupied";
            if (table.getCustomer() != null) {
                status = "Occupied by " + table.getCustomer().getName();
            }
            int e = i + 1;
            System.out.println(e + ". Table no " + table.getTableNumber() + " - " + status);
        }
    }

    private void printFood() {
        carlos.foodMenuPrint();
    }

    private void printDrink() {
        carlos.drinkMenuPrint();
    }

    private void printOrders() {
        for (int i = 1; i < tables.size(); i++) {
            if (this.tables.get(i).getCustomer() == null || this.tables.get(i).getCustomer().getOrder() == null) {
                System.out.println(i + ". Table no " + this.tables.get(i).getTableNumber() + " - Currently has no order.");
            } else {
                System.out.println(i + ". Table no " + this.tables.get(i).getTableNumber() + " has the following order:");
                this.tables.get(i).getCustomer().getOrder().orderSummary();
            }
        }
    }

    private void addCustomer() {
        System.out.println("What is this persons name?");
        final String name = SCANNER.next();
        System.out.println("How old is this person?");
        final int age = SCANNER.nextInt();
        printTables();
        System.out.println("And what table would you like to sit at? (You can only have one person per table)");
        final int table = SCANNER.nextInt() - 1;
        tables.get(table).addCustomer(new Customer(name, age));
    }

    private void removeCustomer() {
        printTables();
        System.out.println("Which table should be vacated?");
        int nominated = SCANNER.nextInt() - 1;
        tables.get(nominated).removeCustomer();
        nominated++;
        System.out.println("Table no " + nominated + " has been vacated.");
    }

    private void makeOrder() {
        int table;
        while (true) {
            System.out.println("Which table would you like to make an order to? (Select an Option)");
            printTables();
            table = SCANNER.nextInt() - 1;
            if (this.tables.get(table).getCustomer() == null) {
                System.out.println("This table is Unoccupied, please select an Occupied table.");

            } else {
                break;
            }
        }

        Order custOrder = new Order(tables.get(table));
        tables.get(table).getCustomer().setOrder(custOrder);

        System.out.println("What would you like to order? (Press the corresponding button to add it to your order)");
        System.out.println("0.Proceed to Ordering Drinks");
        printFood();
        while (true) {

            final int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }



            switch (input) {
                case 1:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(0));
                    System.out.println(carlos.getMenu().getFoodMenu().get(0).getName() + " was added to your order.");
                    break;
                case 2:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(1));
                    System.out.println(carlos.getMenu().getFoodMenu().get(1).getName() + " was added to your order.");
                    break;
                case 3:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(2));
                    System.out.println(carlos.getMenu().getFoodMenu().get(2).getName() + " was added to your order.");
                    break;
                case 4:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(3));
                    System.out.println(carlos.getMenu().getFoodMenu().get(3).getName() + " was added to your order.");
                    break;
                case 5:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(4));
                    System.out.println(carlos.getMenu().getFoodMenu().get(4).getName() + " was added to your order.");
                    break;
                case 6:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(5));
                    System.out.println(carlos.getMenu().getFoodMenu().get(5).getName() + " was added to your order.");
                    break;
                case 7:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(6));
                    System.out.println(carlos.getMenu().getFoodMenu().get(6).getName() + " was added to your order.");
                    break;
                case 8:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(7));
                    System.out.println(carlos.getMenu().getFoodMenu().get(7).getName() + " was added to your order.");
                    break;
                case 9:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(8));
                    System.out.println(carlos.getMenu().getFoodMenu().get(8).getName() + " was added to your order.");
                    break;
                case 10:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(9));
                    System.out.println(carlos.getMenu().getFoodMenu().get(9).getName() + " was added to your order.");
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }

        }
        System.out.println("What would you like to drink? (Press the corresponding button to add it to your order)");
        System.out.println("0.Finish your Order");
        printDrink();
        while (true) {

            final int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(0));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(0).getName() + " was added to your order.");
                    break;
                case 2:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(1));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(1).getName() + " was added to your order.");
                    break;
                case 3:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(2));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(2).getName() + " was added to your order.");
                    break;
                case 4:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(3));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(3).getName() + " was added to your order.");
                    break;
                case 5:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(4));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(4).getName() + " was added to your order.");
                    break;
                case 6:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(5));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(5).getName() + " was added to your order.");
                    break;
                case 7:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(6));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(6).getName() + " was added to your order.");
                    break;
                case 8:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(7));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(7).getName() + " was added to your order.");
                    break;
                case 9:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(8));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(8).getName() + " was added to your order.");
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }
        }
        System.out.println("Here is the order:");
        tables.get(table).getCustomer().getOrder().orderSummary();
    }

    public void addItemToOrder() {
        int table;
        while (true) {
            System.out.println("Which table order would you like to add to? (Select an Option)");
            printTables();
            table = SCANNER.nextInt() - 1;
            if (this.tables.get(table).getCustomer() == null) {
                System.out.println("This table is Unoccupied, please select an Occupied table.");

            } else {
                break;
            }
        }

        Order custOrder = new Order(tables.get(table));
        tables.get(table).getCustomer().setOrder(custOrder);

        System.out.println("What food would you like to add? (Press the corresponding button to add it to your order)");
        System.out.println("0.Proceed to Ordering Drinks");
        printFood();
        while (true) {

            final int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }



            switch (input) {
                case 1:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(0));
                    System.out.println(carlos.getMenu().getFoodMenu().get(0).getName() + " was added to your order.");
                    break;
                case 2:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(1));
                    System.out.println(carlos.getMenu().getFoodMenu().get(1).getName() + " was added to your order.");
                    break;
                case 3:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(2));
                    System.out.println(carlos.getMenu().getFoodMenu().get(2).getName() + " was added to your order.");
                    break;
                case 4:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(3));
                    System.out.println(carlos.getMenu().getFoodMenu().get(3).getName() + " was added to your order.");
                    break;
                case 5:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(4));
                    System.out.println(carlos.getMenu().getFoodMenu().get(4).getName() + " was added to your order.");
                    break;
                case 6:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(5));
                    System.out.println(carlos.getMenu().getFoodMenu().get(5).getName() + " was added to your order.");
                    break;
                case 7:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(6));
                    System.out.println(carlos.getMenu().getFoodMenu().get(6).getName() + " was added to your order.");
                    break;
                case 8:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(7));
                    System.out.println(carlos.getMenu().getFoodMenu().get(7).getName() + " was added to your order.");
                    break;
                case 9:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(8));
                    System.out.println(carlos.getMenu().getFoodMenu().get(8).getName() + " was added to your order.");
                    break;
                case 10:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getFoodMenu().get(9));
                    System.out.println(carlos.getMenu().getFoodMenu().get(9).getName() + " was added to your order.");
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }

        }
        System.out.println("What drinks would you like to add? (Press the corresponding button to add it to your order)");
        System.out.println("0.Finish your Order");
        printDrink();
        while (true) {

            final int input = SCANNER.nextInt();

            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(0));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(0).getName() + " was added to your order.");
                    break;
                case 2:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(1));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(1).getName() + " was added to your order.");
                    break;
                case 3:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(2));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(2).getName() + " was added to your order.");
                    break;
                case 4:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(3));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(3).getName() + " was added to your order.");
                    break;
                case 5:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(4));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(4).getName() + " was added to your order.");
                    break;
                case 6:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(5));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(5).getName() + " was added to your order.");
                    break;
                case 7:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(6));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(6).getName() + " was added to your order.");
                    break;
                case 8:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(7));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(7).getName() + " was added to your order.");
                    break;
                case 9:
                    tables.get(table).getCustomer().getOrder().addItem(carlos.getMenu().getDrinkMenu().get(8));
                    System.out.println(carlos.getMenu().getDrinkMenu().get(8).getName() + " was added to your order.");
                    break;
                default:
                    System.out.println("That wasn't a valid option, Try again.");
                    break;
            }
        }
        System.out.println("Here is the updated order:");
        tables.get(table).getCustomer().getOrder().orderSummary();
    }

    public void saveTables() {
        savePerson(tables);
    }

    public void loadTables() {
        File restaurant = new File("restaurant.json");
        tables = loadPeople(restaurant);
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
