/**
 * Created by lhi06 on 15/06/2017.
 */
public class Customer {

    private String name;
    private int age;
    private Order order;

    public Customer(final String name,final int age) {
        this.name = name;
        this.age = age;
    }

    public Customer(){

    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {

        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public void addOrder(final Order order) {
        this.order = order;
    }


}
