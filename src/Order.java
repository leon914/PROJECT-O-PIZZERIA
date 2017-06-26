import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Order {

    private List<Purchaseable> order = new ArrayList<>();
    @JsonIgnore private Table tableOrder;

    public Order(Table tableOrder) {
        this.tableOrder = tableOrder;

    }

    public Order(){

    }

    public void addItem(Purchaseable purchaseable){
        this.order.add(purchaseable);
    }

    public void orderSummary() {
        DecimalFormat df = new DecimalFormat("#.00");
        double price = 0;
        for (Purchaseable item: order) {
                System.out.println(item.getName() + " £" + df.format(item.getPrice()));
                price += item.getPrice();
        }
        System.out.println("Total Cost :- £" + df.format(price));
    }

    public List<Purchaseable> getOrder() {
        return order;
    }

    public Table getTableOrder() {
        return tableOrder;
    }

    public void setOrder(List<Purchaseable> order) {
        this.order = order;
    }

    public void setTableOrder(Table tableOrder) {
        this.tableOrder = tableOrder;
    }
}
