import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Order {

    private List<Purchaseable> items = new ArrayList<>();
    @JsonIgnore
    private Table tableOrder;

    public Order() {}

    public Order(Table tableOrder) {
        this.tableOrder = tableOrder;
    }

    public List<Purchaseable> getItems() {
        return items;
    }

    public Table getTableOrder() {
        return tableOrder;
    }

    public void setItems(List<Purchaseable> items) {
        this.items = items;
    }

    public void setTableOrder(Table tableOrder) {
        this.tableOrder = tableOrder;
    }
}
