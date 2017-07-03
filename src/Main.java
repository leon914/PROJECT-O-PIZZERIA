import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.fastinfoset.tools.PrintTable;

import java.io.File;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lhi06 on 20/06/2017.
 */
public class Main {

    private static final int AMOUNT_OF_TABLES = 6;

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(final String[] args) {

        final Restaurant restaurant = new Restaurant();

        for (int i = 1; i <= AMOUNT_OF_TABLES; i++) {
            restaurant.getTables().add(new Table(i));
        }

        System.out.println("Hello guys, its a funky pizza parlour!");

        while (true) {

            printOptions();

            int input;

            try {
                input = SCANNER.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("This is not a number, Please insert a number.");
                continue;
            }

            switch (input) {
                case 0:
                    return;
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
                    printOrder(restaurant);
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
        System.out.println("\nSelect an option:");
        System.out.println("0. Exit");
        System.out.println("1. Print Tables");
        System.out.println("2. Print Food Menu");
        System.out.println("3. Print Drink Menu");
        System.out.println("4. Add a Customer");
        System.out.println("5. Remove a Customer");
        System.out.println("6. Make an Order");
        System.out.println("7. Print a Customers Order");
        System.out.println("8. Add Items to and Existing Order");
        System.out.println("9. Save Current Data");
        System.out.println("10. Load Past Data");
    }

    public static void printTables(Restaurant restaurant) {
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            Table table = restaurant.getTables().get(i);
            StringBuilder status = new StringBuilder();
            if (!table.getCustomers().isEmpty()) {
                status.append("Occupied by ");
                int counter = 1;
                for (Customer customer : restaurant.getTables().get(i).getCustomers()) {
                    if (counter != 1) {
                        if (restaurant.getTables().get(i).getCustomers().size() == counter) {
                            status.append(" and ");
                        } else {
                            status.append(", ");
                        }
                    }
                    status.append(customer.getName());
                    counter++;
                }
            } else {
                status.append("Unoccupied");
            }
            int tableNumberReadable = i + 1;
            System.out.println(tableNumberReadable + ". Table no " + table.getTableNumber() + " - " + status);
        }
    }

    public static void printCustomers(Restaurant restaurant, int tableNumberIndex) {
        if (!restaurant.getTables().get(tableNumberIndex).getCustomers().isEmpty()) {
            int counter = 1;
            for (Customer customer : restaurant.getTables().get(tableNumberIndex).getCustomers()) {
                System.out.println(counter + ". " + customer.getName());
            }
        } else {
            System.out.println("Nobody is at this table, Try again please");
        }
    }

    public static void printFood(Restaurant restaurant) {
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (Food food : restaurant.getMenu().getFoodMenu()) {
            System.out.println(i + "." + food.getName() + " £" + df.format(food.getPrice()));
            i++;
        }
    }

    public static void printDrink(Restaurant restaurant) {
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (Drink drink : restaurant.getMenu().getDrinkMenu()) {
            System.out.println(i + "." + drink.getName() + " £" + df.format(drink.getPrice()));
            i++;
        }
    }

    public static void printOrder(Restaurant restaurant) {
        System.out.println("What table is the customer sitting at? (Enter Corresponding Number");
        printTables(restaurant);
        int table;
        try {
            table = SCANNER.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That wasn't a table number, Please try again");
            return;
        }
        System.out.println("Which Customers Order would you like to see? (Enter Corresponding Number)");
        try {
            printCustomers(restaurant, table - 1);
        } catch (Exception e){
            System.out.println("That wasn't a table number, Please try again");
            return;
        }
        int nameIndex;
        try {
            nameIndex = SCANNER.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That wasn't an existing customer, Please try again");
            return;
        }
        orderSummary(restaurant, table - 1, nameIndex - 1);
        }

    public static void orderSummary(Restaurant restaurant, int tableNumber, int customerIndex) {
        DecimalFormat df = new DecimalFormat("#.00");
        double price = 0;
        for (Purchaseable item : restaurant.getTables().get(tableNumber).getCustomers().get(customerIndex).getOrder().getItems()) {
            System.out.println(item.getName() + " £" + df.format(item.getPrice()));
            price += item.getPrice();
        }
        System.out.println("Total Cost :- £" + df.format(price));
    }


    public static void addCustomer(final Restaurant restaurant) {
        System.out.println("What is this persons name?");
        String name;
        try {
            name = SCANNER.next();
        } catch (InputMismatchException e) {
            System.out.println("That name wasn't valid, please try again.");
            return;
        }
        System.out.println("How old is this person?");
         int age;
        try {
            age = SCANNER.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That age wasn't valid, please try again.");
            return;
        }
        printTables(restaurant);
        System.out.println("And what table would you like to sit at?");
        int table;
        try {
            table = SCANNER.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("That table number wasn't valid, please try again.");
            return;
        }
        restaurant.getTables().get(table).getCustomers().add(new Customer(name, age));
        System.out.println("Customer added.");
    }

    public static void removeCustomer(Restaurant restaurant) {
        printTables(restaurant);
        System.out.println("Which table is the person at that should be vacated?");
        int nominated;
        try {
            nominated = SCANNER.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That name wasn't valid, please try again.");
            return;
        }
        System.out.println("Who would should be vacated? (Enter Corresponding Number)");
        printCustomers(restaurant, nominated - 1);
        int customerIndex;
        try {
            customerIndex = SCANNER.nextInt();
        } catch(InputMismatchException e) {
            System.out.println("This wasn't a valid option, please try again.");
            return;
        }
        restaurant.getTables().get(nominated - 1).getCustomers().remove(customerIndex - 1);
        System.out.println("Table no " + nominated + " has been vacated.");
    }

    public static void makeOrder(Restaurant restaurant) {
        System.out.println("Which table would you like to make an order to? (Select an Option)");
        printTables(restaurant);
        int table;
        try {
            table = SCANNER.nextInt();
        } catch(InputMismatchException e) {
            System.out.println("This wasn't a valid option, please try again.");
            return;
        }
        System.out.println("Who's Order would you like to take? (Enter Corresponding Number)");
        printCustomers(restaurant, table - 1);
        int customerIndex;
        try {
            customerIndex = SCANNER.nextInt();
        } catch(InputMismatchException e) {
            System.out.println("This wasn't a valid option, please try again.");
            return;
        }
        Order custOrder = new Order(restaurant.getTables().get(table - 1));
        restaurant.getTables().get(table - 1).getCustomers().get(customerIndex - 1).setOrder(custOrder);

        System.out.println("What food you like to order? (Press the corresponding button to add it to your order)");
        System.out.println("0.Proceed to Ordering Drinks");
        printFood(restaurant);

        while (true) {

            int input;
            try {
                input = SCANNER.nextInt();
            } catch (InputMismatchException e){
                System.out.println("That wasn't a valid option, Try again.");
                break;
            }
            if (input == 0) {
                break;
            }
            if (input <= restaurant.getMenu().getFoodMenu().size() && input - 1 >= 0) {
                restaurant.getTables().get(table - 1).getCustomers().get(customerIndex - 1).getOrder().getItems().add(restaurant.getMenu().getFoodMenu().get(input - 1));
                System.out.println(restaurant.getMenu().getFoodMenu().get(input - 1).getName() + " was added to your order.");
            } else {
                System.out.println("That wasn't a valid option, Try again.");
            }
        }

        System.out.println("What would you like to drink? (Press the corresponding button to add it to your order)");
        System.out.println("0.Finish your Order");
        printDrink(restaurant);

        while (true) {

            int input;
            try {
                input = SCANNER.nextInt();
            } catch (InputMismatchException e){
                System.out.println("That wasn't a valid option, Try again.");
                break;
            }
            if (input == 0) {
                break;
            }
            if (input <= restaurant.getMenu().getDrinkMenu().size() && input - 1 >= 0) {
                restaurant.getTables().get(table - 1).getCustomers().get(customerIndex - 1).getOrder().getItems().add(restaurant.getMenu().getDrinkMenu().get(input - 1));
                System.out.println(restaurant.getMenu().getDrinkMenu().get(input - 1).getName() + " was added to your order.");
            } else {
                System.out.println("That wasn't a valid option, Try again.");
            }
        }
        System.out.println("Here is the order:");
        orderSummary(restaurant, table - 1, customerIndex - 1);
    }

    public static void addItemToOrder(Restaurant restaurant) {

        System.out.println("Which table order would you like to add to? (Select an Option)");
        printTables(restaurant);
        int table;
        try {
            table = SCANNER.nextInt();
            if (restaurant.getTables().get(table - 1).getCustomers().isEmpty()) {
                System.out.println("This table is Unoccupied, please select an Occupied table.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("This wasn't a valid option, please try again.");
            return;
        }

        System.out.println("Who's Order would you like to add to? (Enter Corresponding Number)");
        printCustomers(restaurant, table - 1);
        int customerIndex;
        try {
            customerIndex = SCANNER.nextInt();
            if (restaurant.getTables().get(table).getCustomers().get(customerIndex).getOrder() == null) {
                System.out.println("This wasn't a valid option, please try again.");
                return;
            }
        } catch(InputMismatchException e) {
            System.out.println("This wasn't a valid option, please try again.");
            return;
        }

        System.out.println("What food you like to add to your order? (Press the corresponding button to add it to your order)");
        System.out.println("0.Proceed to Ordering Drinks");
        printFood(restaurant);
        while (true) {

            int input;
            try {
                input = SCANNER.nextInt();
            } catch (InputMismatchException e){
                System.out.println("That wasn't a valid option, Try again.");
                break;
            }
            if (input == 0) {
                break;
            }
            if (input <= restaurant.getMenu().getFoodMenu().size() && input - 1 >= 0) {
                restaurant.getTables().get(table - 1).getCustomers().get(customerIndex - 1).getOrder().getItems().add(restaurant.getMenu().getFoodMenu().get(input - 1));
                System.out.println(restaurant.getMenu().getFoodMenu().get(input - 1).getName() + " was added to your order.");
            } else {
                System.out.println("That wasn't a valid option, Try again.");
            }
        }

        System.out.println("What drinks would you like to add? (Press the corresponding button to add it to your order)");
        System.out.println("0.Finish your Order");
        printDrink(restaurant);

        while (true) {

            int input;
            try {
                input = SCANNER.nextInt();
            } catch (InputMismatchException e){
                System.out.println("That wasn't a valid option, Try again.");
                break;
            }
            if (input == 0) {
                break;
            }
            if (input <= restaurant.getMenu().getDrinkMenu().size() && input - 1 >= 0) {
                restaurant.getTables().get(table - 1).getCustomers().get(customerIndex - 1).getOrder().getItems().add(restaurant.getMenu().getDrinkMenu().get(input - 1));
                System.out.println(restaurant.getMenu().getDrinkMenu().get(input - 1).getName() + " was added to your order.");
            } else {
                System.out.println("That wasn't a valid option, Try again.");
            }
        }
        System.out.println("Here is the updated order:");
        orderSummary(restaurant, table - 1, customerIndex - 1);
    }

    public static void saveTables(Restaurant restaurant) {
        savePerson(restaurant.getTables());
    }

    public static void loadTables(Restaurant restaurant) {
        File pizzeria = new File("restaurant.json");
        restaurant.setTables(loadPeople(pizzeria));
    }


    public static void savePerson(final List<Table> tables) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("restaurant.json"), tables);
            System.out.println("Saved current data.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<Table> loadPeople(final File json) {
        List<Table> tables;
        try {
            ObjectMapper mapper = new ObjectMapper();
            tables = mapper.readValue(json, new TypeReference<List<Table>>() {
            });
            System.out.println("Loaded current data.");
        } catch (Exception e) {
            System.out.println(e);
            tables = null;
        }
        return tables;

    }
}
