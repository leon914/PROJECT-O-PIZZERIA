/**
 * Created by lhi06 on 15/06/2017.
 */
public class Purchaseable {

    private String name;
    private double price;

    public Purchaseable(final String name, final double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Purchaseable(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
