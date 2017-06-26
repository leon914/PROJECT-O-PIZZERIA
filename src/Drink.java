/**
 * Created by lhi06 on 15/06/2017.
 */
public class Drink extends Purchaseable {

    public Drink(final String name,final double price) {
        super(name, price);
    }

    public String getName() {
        return super.getName();
    }

    public double getPrice() {
        return super.getPrice();
    }

    public void setName(String name){
        super.setName(name);
    }

    public void setPrice(double price){
        super.setPrice(price);
    }
}
