/**
 * Created by lhi06 on 15/06/2017.
 */
public class Waiter {

    private String name;
    private Order order;
    private Menu luciosMenu = new Menu();

    public Waiter(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
//
//    public void addCustomerToTable(Customer customer, Table table) {
//        table.addCustomerExisting(customer);
//    }
//    public void removeCustomerFromTable(Customer customer, Table table) {
//        table.removeCustomerExisting(customer);
//    }
    public void startOrder(Table table, Restaurant restaurant) {
        System.out.println("Hello, my name is " + this.name + ", how may I help?");
      //  restaurant.addOrder(new Order(table));
    }
    public void addItem(Purchaseable purchaseable) {
        this.order.addItem(purchaseable);
    }

    public void foodMenuPrint() {
        this.luciosMenu.listFoodMenu();
    }
    public void drinkMenuPrint() {
        this.luciosMenu.listDrinkMenu();
    }


}
