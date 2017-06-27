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
                    restaurant.printTables();
                    break;
                case 2:
                    restaurant.printFood();
                    break;
                case 3:
                    restaurant.printDrink();
                    break;
                case 4:
                    restaurant.addCustomer();
                    break;
                case 5:
                    restaurant.removeCustomer();
                    break;
                case 6:
                    restaurant.makeOrder();
                    break;
                case 7:
                    restaurant.printOrders();
                    break;
                case 8:
                    restaurant.addItemToOrder();
                    break;
                case 9:
                    restaurant.saveTables();
                    break;
                case 10:
                    restaurant.loadTables();
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
}
