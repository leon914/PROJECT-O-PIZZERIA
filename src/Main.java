import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Tab;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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

            try {

                int inputMenu = consoleInterpreterInt();

                switch (inputMenu) {
                    case 0:
                        return;
                    case 1:
                        printTables(restaurant.getTables());
                        break;
                    case 2:
                        printMenu(restaurant.getMenu().getMenu(true));
                        break;
                    case 3:
                        printMenu(restaurant.getMenu().getMenu(false));
                        break;
                    case 4:
                        addCustomer(restaurant.getTables());
                        break;
                    case 5:
                        removeCustomer(restaurant.getTables());
                        break;
                    case 6:
                        makeOrder(restaurant);
                        break;
                    case 7:
                        printOrder(restaurant.getTables());
                        break;
                    case 8:
                        addItemToOrder(restaurant);
                        break;
                    case 9:
                        saveTables(restaurant.getTables());
                        break;
                    case 10:
                        loadTables(restaurant);
                        break;
                    default:
                        System.out.println("That wasn't a valid option, Try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("That wasn't a valid option, Try again.");
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

    public static void printTables(final List<Table> tables) {
        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
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
        for (int i = 1; i <= table.getCustomers().size(); i++) {
            System.out.println(i + ". " + table.getCustomers().get(i - 1).getName());
        }
    }

    public static void printMenu(List<? extends Purchaseable> menu) {
        for (int i = 1; i <= menu.size(); i++) {
            System.out.println(i + "." + menu.get(i - 1));
        }
     }

    public static void printOrder(final List<Table> tables) {
        Table table;
        System.out.println("What table is the customer sitting at? (Enter Corresponding Number");
        table = whatTableRemove(tables);
        Customer customer;
        System.out.println("Which Customers Order? (Enter Corresponding Number)");
        customer = whatCustomerOrder(table);
        orderSummary(customer);
    }

    public static Table whatTable(final List<Table> tables) throws NumberFormatException {
        printTables(tables);
        int table = consoleInterpreterInt();
        if (table >= 1 && table <= AMOUNT_OF_TABLES) {
            table--;
            return tables.get(table);
        } else {
            throw new NumberFormatException();
        }
    }

    public static Table whatTableRemove(final List<Table> tables) throws NumberFormatException {
        printTables(tables);
        int table = consoleInterpreterInt();
        if (table >= 1 && table <= AMOUNT_OF_TABLES && !tables.get(table - 1).getCustomers().isEmpty()) {
            table--;
            return tables.get(table);
        } else {
            throw new NumberFormatException();
        }
    }

    public static Customer whatCustomer(Table table) throws NumberFormatException {
        printCustomers(table);
        int customerIndex = consoleInterpreterInt();
        List<Customer> customers = table.getCustomers();
        if (customerIndex <= customers.size() && customerIndex > 0) {
            customerIndex--;
            return table.getCustomers().get(customerIndex);
        } else {
            throw new NumberFormatException();
        }
    }

    public static Customer whatCustomerOrder(Table table) throws NumberFormatException {
        printCustomers(table);
        int customerIndex = consoleInterpreterInt();
        List<Customer> customers = table.getCustomers();
        if (customerIndex <= customers.size() && customerIndex > 0 && customers.get(customerIndex-1).getOrder() != null) {
            customerIndex--;
            return table.getCustomers().get(customerIndex);
        } else {
            throw new NumberFormatException();
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


    public static void addCustomer(final List<Table> tables) {
        System.out.println("What is this persons name?");
        String name = SCANNER.nextLine();
        System.out.println("How old is this person?");
        int age = consoleInterpreterInt();
        System.out.println("Which table would you like to sit at?");
        Table table = whatTable(tables);
        table.getCustomers().add(new Customer(name, age));
        System.out.println("Customer added.");
    }

    public static void removeCustomer(final List<Table> tables) {
        System.out.println("Which table is the person at that should be vacated?");
        Table table = whatTableRemove(tables);
        System.out.println("Who would should be vacated from table? (Enter Corresponding Number)");
        Customer customer = whatCustomer(table);
        System.out.println(customer.getName() + " has left.");
        table.getCustomers().remove(customer);
    }

    public static void makeOrder(Restaurant restaurant) {
        System.out.println("Which table would you like to make an order to? (Select an Option)");
        Table table = whatTableRemove(restaurant.getTables());
        System.out.println("Who's Order would you like to take?");
        Customer customer = whatCustomer(table);
        customer.setOrder(new Order(table));
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
                    customer.getOrder().getItems().add(menu.get(input - 1));
                    System.out.println(menu.get(input - 1).getName() + " was added to your order.");
                } else {
                    System.out.println("That wasn't a valid option, Try again.");
                }
            }
            order++;
        }
        System.out.println("Here is the order:");
        orderSummary(customer);
    }

    public static void addItemToOrder(Restaurant restaurant) {
        System.out.println("Which table order would you like to add to? (Select an Option)");
        Table table = whatTableRemove(restaurant.getTables());
        System.out.println("Who's Order would you like to add to? (Enter Corresponding Number)");
        Customer customer = whatCustomerOrder(table);
        //Ordering
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
                    customer.getOrder().getItems().add(menu.get(input - 1));
                    System.out.println(menu.get(input - 1).getName() + " was added to your order.");
                } else {
                    System.out.println("That wasn't a valid option, Try again.");
                }
            }
            order++;
        }
        System.out.println("Here is the updated order:");
        orderSummary(customer);
    }

    public static void saveTables(final List<Table> tables) {
        savePerson(tables);
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
        } catch (IOException e) {
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

    public static int consoleInterpreterInt() throws NumberFormatException {
        return Integer.valueOf(SCANNER.nextLine());
    }

    public static String consoleInterpreterString() throws NumberFormatException {
        return SCANNER.next();
    }
}
