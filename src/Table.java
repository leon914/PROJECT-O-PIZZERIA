import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Table {

    private int seats;
    private List<Customer> customers = new ArrayList();

    public Table(int seats){
        this.seats = seats;
    }

    public void addCustomerExisting(Customer customer) {
        if (this.customers.size() <= this.seats) {
        customers.add(customer);
        } else {
            System.out.println("There isn't enough space on this table to add another customer");
        }
    }

    public void removeCustomerExisting(Customer customer) {
            customers.remove(customer);
    }


    public int getSeats() {
      return this.seats;
    }

    public List<Customer> getCustomerList() {
        return this.customers;
    }
}
