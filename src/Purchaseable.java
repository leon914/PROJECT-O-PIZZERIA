/**
 * Created by lhi06 on 15/06/2017.
 */
public abstract class Purchaseable {

    private String name;
    private double price;

    public Purchaseable(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

}
