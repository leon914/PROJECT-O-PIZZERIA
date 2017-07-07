import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Tab;

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

            //the scanner keeps going because nothing will accept the value so it keeps going in a circle

            printOptions();

            int inputMenu = consoleInterpreterInt();

            switch (inputMenu) {
                case 0:
                    return;
                case 1:
                    printTables(restaurant);
                    break;
                case 2:
                    printMenu(restaurant.getMenu().getMenu(true));
                    break;
                case 3:
                    printMenu(restaurant.getMenu().getMenu(false));
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
                for (int counter = 1; counter <= table.getCustomers().size(); counter++) {
                    if (counter != 1) {
                        if (table.getCustomers().size() == counter) {
                            status.append(" and ");
                        } else {
                            status.append(", ");
                        }
                    }
                    status.append(table.getCustomers().get(counter-1).getName());
                }
            } else {
                status.append("Unoccupied");
            }
            System.out.println((i + 1) + ". Table no " + table.getTableNumber() + " - " + status);
        }
    }

    public static void printCustomers(Table table) {
        for (int counter = 1; counter <= table.getCustomers().size(); counter++) {
            System.out.println(counter + ". " + table.getCustomers().get(counter - 1).getName());
        }
    }

    public static void printMenu(List<? extends Purchaseable> menuType) {
        int i = 1;
        for (Purchaseable purchaseable : menuType) {
            System.out.println(i + "." + purchaseable);
            i++;
        }
    }

    public static void printOrder(Restaurant restaurant) {
        System.out.println("What table is the customer sitting at? (Enter Corresponding Number");
        printTables(restaurant);
        int table = consoleInterpreterInt();
        if (table == -1) {
            return;
        }
        if (table <= AMOUNT_OF_TABLES && table >= 1 && !restaurant.getTables().get(table - 1).getCustomers().isEmpty()) {
            System.out.println("Which Customers Order would you like to see? (Enter Corresponding Number)");
            table--;
            printCustomers(restaurant.getTables().get(table));
        } else {
            System.out.println("That wasn't valid, please try again.");
            return;
        }
        int customerIndex = consoleInterpreterInt();
        if (customerIndex == -1) {
            return;
        }
        List<Customer> customers = restaurant.getTables().get(table).getCustomers();
        if (customerIndex <= customers.size() && customerIndex > 0 && customers.get(customerIndex-1).getOrder() != null) {
            customerIndex--;
            orderSummary(restaurant.getTables().get(table).getCustomers().get(customerIndex));
        } else {
            System.out.println("That wasn't valid/existing order, please try again.");
        }
    }

    public static void orderSummary(Customer customer) {
        DecimalFormat df = new DecimalFormat("#.00");
        double price = 0;
        if (customer.getOrder() != null) {
            for (Purchaseable item : customer.getOrder().getItems()) {
                System.out.println(item);
                price += item.getPrice();
            }
            System.out.println("Total Cost :- £" + df.format(price));
        } else {
            System.out.println("That wasn't valid/existing order, please try again.");
        }
    }


    public static void addCustomer(final Restaurant restaurant) {
        System.out.println("What is this persons name?");
        String name;
        try {
            name = SCANNER.next();
        } catch (InputMismatchException e) {
            System.out.println("Sorry That name isn't available, please try again.");
            return;
        }
        System.out.println("How old is this person?");
        int age = consoleInterpreterInt();
        if (age == -1) {
            return;
        }
        System.out.println("And what table would you like to sit at?");
        printTables(restaurant);
        int table = consoleInterpreterInt();
        if (table == -1) {
            return;
        }
        if (table <= AMOUNT_OF_TABLES && table - 1 >= 0) {
            restaurant.getTables().get(table - 1).getCustomers().add(new Customer(name, age));
            System.out.println("Customer added.");
        } else {
            System.out.println("That table wasn't valid, please try again.");
        }
    }

    public static void removeCustomer(Restaurant restaurant) {
        System.out.println("Which table is the person at that should be vacated?");
        printTables(restaurant);
        int table = consoleInterpreterInt(); //so it isnt a string
        if (table == -1) {
            return;
        }
        if (table <= AMOUNT_OF_TABLES && table >= 1 && !restaurant.getTables().get(table - 1).getCustomers().isEmpty()) {
            System.out.println("Who would should be vacated from table? (Enter Corresponding Number)");
            table--;
            printCustomers(restaurant.getTables().get(table));
        } else {
            System.out.println("That wasn't valid, please try again.");
            return;
        }
        int customerIndex = consoleInterpreterInt();
        if (customerIndex == -1) {
            return;
        }
        List<Customer> customers = restaurant.getTables().get(table).getCustomers();
        if (customerIndex <= customers.size() && customerIndex - 1 > -1 && customers.get(customerIndex - 1) != null) {
            customerIndex--;
            System.out.println(customers.get(customerIndex).getName() + " has left.");
            restaurant.getTables().get(table).getCustomers().remove(customerIndex);
            return;
        } else {
            System.out.println("That wasn't valid, please try again.");
            return;
        }
    }

    public static void makeOrder(Restaurant restaurant) {
        System.out.println("Which table would you like to make an order to? (Select an Option)");
        printTables(restaurant);
        int table = consoleInterpreterInt();
        if (table == -1) {
            return;
        }
        //table Check
        if (table <= AMOUNT_OF_TABLES && table >= 1 && !restaurant.getTables().get(table - 1).getCustomers().isEmpty()) {
            System.out.println("Who's Order would you like to take?");
            table--;
            printCustomers(restaurant.getTables().get(table));
        } else {
            System.out.println("That wasn't valid, please try again.");
            return;
        }
        int customerIndex = consoleInterpreterInt();
        if (customerIndex == -1) {
            return;
        }
        //customerIndex Check
        List<Customer> customers = restaurant.getTables().get(table).getCustomers();
        if (customerIndex <= customers.size() && customerIndex - 1 > -1 && customers.get(customerIndex - 1) != null) {
            customerIndex--;
            Order custOrder = new Order(restaurant.getTables().get(table));
            restaurant.getTables().get(table).getCustomers().get(customerIndex).setOrder(custOrder);
        } else {
            System.out.println("That wasn't valid, please try again.");
            return;
        }
        //Ordering
        int order = 0;
        List<Purchaseable> menu;
        while (order < 2) {
            if (order == 0) {
                System.out.println("What food would you like to order? (Press the corresponding button to add it to your order)");
                System.out.println("0.Proceed to Ordering Drinks");
                menu = restaurant.getMenu().getMenu(true);
            } else {
                System.out.println("What would you like to drink? (Press the corresponding button to add it to your order)");
                System.out.println("0.Finish your Order");
                menu = restaurant.getMenu().getMenu(false);
            }
            printMenu(menu);
            int input;
            while ((input = consoleInterpreterInt()) != 0) {
                if (input <= menu.size() && input - 1 >= 0) {
                    restaurant.getTables().get(table).getCustomers().get(customerIndex).getOrder().getItems().add(menu.get(input - 1));
                    System.out.println(menu.get(input - 1).getName() + " was added to your order.");
                } else {
                    System.out.println("That wasn't a valid option, Try again.");
                }
            }
            order++;
        }
        System.out.println("Here is the order:");
        orderSummary(restaurant.getTables().get(table).getCustomers().get(customerIndex));
    }

    public static void addItemToOrder(Restaurant restaurant) {

        System.out.println("Which table order would you like to add to? (Select an Option)");
        printTables(restaurant);
        int table = consoleInterpreterInt();
        //table Check
        if (table <= AMOUNT_OF_TABLES && table >= 1 && !restaurant.getTables().get(table - 1).getCustomers().isEmpty()) {
            System.out.println("Who's Order would you like to add to? (Enter Corresponding Number)");
            table--;
            printCustomers(restaurant.getTables().get(table));
        } else {
            System.out.println("This table is Unoccupied, please select an Occupied table.");
            return;
        }
        int customerIndex;
        //customerIndex Check
        try {
            customerIndex = consoleInterpreterInt();
            if (restaurant.getTables().get(table).getCustomers().get(customerIndex).getOrder() == null) {
                System.out.println("This wasn't a valid option, please try again.");
                return;
            } else {
                customerIndex--;
            }
        } catch (InputMismatchException e) {
            System.out.println("This wasn't a valid option, please try again.");
            return;
        }
        int order = 0;
        List<Purchaseable> menu;
        while (order < 2) {
            if (order == 0) {
                System.out.println("What food you like to add to your order? (Press the corresponding button to add it to your order)");
                System.out.println("0.Proceed to Ordering Drinks");
                menu = restaurant.getMenu().getMenu(true);
            } else {
                System.out.println("What drinks would you like to add? (Press the corresponding button to add it to your order)");
                System.out.println("0.Finish your Order");
                menu = restaurant.getMenu().getMenu(false);
            }
            printMenu(menu);
            int input;
            while ((input = consoleInterpreterInt()) != 0) {
                if (input <= menu.size() && input - 1 >= 0) {
                    restaurant.getTables().get(table).getCustomers().get(customerIndex).getOrder().getItems().add(menu.get(input - 1));
                    System.out.println(menu.get(input - 1).getName() + " was added to your order.");
                } else {
                    System.out.println("That wasn't a valid option, Try again.");
                }
            }
            order++;
        }
        System.out.println("Here is the updated order:");
        orderSummary(restaurant.getTables().get(table - 1).getCustomers().get(customerIndex - 1));
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

    public static int consoleInterpreterInt() {
        try {
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            SCANNER.next();
            System.out.println("That wasn't valid, please try again.");
            return -1;
        }
    }
}
