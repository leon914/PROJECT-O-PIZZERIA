import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Table {

    private int tableNumber;

    private Customer customer;

    public Table(final int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Table() {
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
}
