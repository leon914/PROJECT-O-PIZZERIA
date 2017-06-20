import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Order {

    private List<Purchaseable> order = new ArrayList<>();
    private Table tableOrder;

    public Order(Table tableOrder) {
        this.tableOrder = tableOrder;

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

}
