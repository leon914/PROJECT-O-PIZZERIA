/**
 * Created by lhi06 on 15/06/2017.
 */
public class Food extends Purchaseable {

    public Food(final String name, double price) {
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
