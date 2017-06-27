/**
 * Created by lhi06 on 15/06/2017.
 */

public class Customer {

    private String name;
    private int age;
    private Order order;

    public Customer(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public Customer() {

    }

    public void setAge(final int age) {
        this.age = age;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Order getOrder() {
        return order;
    }

}
