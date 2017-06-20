import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Restaurant {

    private List<Table> tables = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    private Waiter carlos = new Waiter("Carlos");

    public void initRestaurant() {


        Table one = new Table(1);
        Table two = new Table(2);
        Table three = new Table(3);
        Table four = new Table(4);
        Table five = new Table(5);
        Table six = new Table(6);

        tables.add(one);
        tables.add(two);
        tables.add(three);
        tables.add(four);
        tables.add(five);
        tables.add(six);
    }

    public void run() {

        System.out.println("Hello guys, its a funky pizza parlour!");

        while (true) {

            printOptions();

            int input = scanner.nextInt();

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
                default:
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
    }

    private void printTables() {
        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            String status = "Unoccupied";
            if (table.getCustomer() != null) {
                status = "Occupied by " + table.getCustomer().getName();
            }
            int e = i + 1;
            System.out.println(e + ". Table no " + table.tableNumber + " - " + status);
        }
    }

    private void printFood() {
        this.carlos.foodMenuPrint();
    }

    private void printDrink() {
        this.carlos.drinkMenuPrint();
    }

    private void printOrders() {
        for (int i = 0; i < tables.size(); i++) {
            if (this.tables.get(i).getCustomer().getOrder() == null) {
                System.out.println(i + ". Table no " + this.tables.get(i).tableNumber + " - Currently has no order.");
            } else {
                System.out.println(i + ". Table no " + this.tables.get(i).tableNumber + " has the following order:");
                this.tables.get(i).getCustomer().getOrder().orderSummary();
            }
        }
    }

    private void addCustomer() {
        System.out.println("What is this persons name?");
        String name = scanner.next();
        System.out.println("How old is this person?");
        int age = scanner.nextInt();
        printTables();
        System.out.println("And what table would you like to sit at? (You can only have one person per table)");
        int table = scanner.nextInt();
        table--;
        Customer customer = new Customer(name, age);
        tables.get(table).addCustomer(customer);
            }

    private void removeCustomer() {
        printTables();
        System.out.println("Which table should be vacated?");
        int nominated = scanner.nextInt();
        nominated--;
        this.tables.remove(nominated);
        System.out.println("Table no " + nominated + " has been vacated.");
    }

    private void makeOrder() {
            
    }
}
