import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Table {

    int tableNumber;

    private Customer customer = null;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void addCustomer(Customer newCustomer) {
        if (this.customer == null) { // nobody sitting here yet
            this.customer = newCustomer;
        } else {
            System.out.println("There isn't enough space on this table to add another customer");
        }
    }

    public void removeCustomer() {
        this.customer = null;
    }

    public Customer getCustomer() {
        return this.customer;
    }
}
