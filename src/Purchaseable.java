/**
 * Created by lhi06 on 15/06/2017.
 */
public class Purchaseable {

    private String name;
    private double price;

    public Purchaseable() {
    }

    public Purchaseable(final String name, final double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
