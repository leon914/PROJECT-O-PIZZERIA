import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Restaurant {

    public List<Table> tables = new ArrayList<>();
    private List<Order> orders;


    public static void main (String[] args) {

        Restaurant lucios = new Restaurant();
        Menu luciosMenu = new Menu();

        Waiter carlos = new Waiter("Carlos");

        Table one = new Table(4);
        Table two = new Table(2);
        Table three = new Table(3);
        Table four = new Table(4);
        Table five = new Table(4);
        Table six = new Table(6);

        lucios.tables.add(one);
        lucios.tables.add(two);
        lucios.tables.add(three);
        lucios.tables.add(four);
        lucios.tables.add(five);
        lucios.tables.add(six);


        Customer customer01 = new Customer("Harry", 18);
        Customer customer02 = new Customer("Jason", 32);
        Customer customer03 = new Customer("Jake", 54);
        Customer customer04 = new Customer("Oliver", 12);
        Customer customer05 = new Customer("Joe", 21);
        Customer customer06 = new Customer("Fred", 33);
        Customer customer07 = new Customer("Christina", 25);
        Customer customer08 = new Customer("Tina", 13);
        Customer customer09 = new Customer("Tommy", 8);
        Customer customer10 = new Customer("Madonna", 42);
        Customer customer11 = new Customer("Jeremy", 43);
        Customer customer12 = new Customer("Matthew", 18);
        Customer customer13 = new Customer("Leon", 19);

        carlos.addCustomerToTable(customer01, one);
        carlos.addCustomerToTable(customer02, one);
        carlos.addCustomerToTable(customer03, one);
        carlos.addCustomerToTable(customer04, one);
        carlos.addCustomerToTable(customer05, two);
        carlos.addCustomerToTable(customer07, two);
        carlos.addCustomerToTable(customer06, three);
        carlos.addCustomerToTable(customer08, four);
        carlos.addCustomerToTable(customer09, six);
        carlos.addCustomerToTable(customer10, six);
        carlos.addCustomerToTable(customer11, six);
        carlos.addCustomerToTable(customer12, six);
        carlos.addCustomerToTable(customer13, six);

        luciosMenu.listFoodMenu();
        luciosMenu.listDrinkMenu();


    }

    public String getCustomers(Table table) {
        StringBuilder builder = new StringBuilder("On this table there is ");
        int counter = 1;
        if (table.getCustomerList().size() > 0) {
            for (Customer customer : table.getCustomerList()) {
                if (table.getCustomerList().size()== counter) {
                    if (counter == 1) {
                        builder.append(customer.getName());
                        counter++;
                    } else {
                        builder.append(" and " + customer.getName());
                        counter++;
                    }
                } else {
                    if (counter == 1) {
                        builder.append(customer.getName());
                        counter++;
                    }   else {
                        builder.append( ", " + customer.getName());
                        counter++;
                    }
                }
            }
        } else {
            return "There is no one at this table";
        }

        return  builder.toString();
    }

    public void getContentsOfRestaurant(List<Table> tables) {
        for (Table table : tables) {
            System.out.println(getCustomers(table));
        }
    }
    public List getOrders() {
        return orders;
    }
    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
