/**
 * Created by lhi06 on 15/06/2017.
 */
public class Waiter {

    private final String name;
    private Menu menu;

    public Waiter(final String name) {
        this.name = name;
        menu.getInstance();
    }

    public String getName() {
        return name;
    }

    public Menu getMenu() {
        return menu;
    }
}
