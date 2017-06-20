/**
 * Created by lhi06 on 15/06/2017.
 */
public class Customer {

    private String name;
    private int age;

    private Order order = null;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public Order getOrder() {
        return order;
    }

    public void addOrder(Order order) {
        this.order = order;
    }
}
