import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Restaurant {

    private List<Table> tables = new ArrayList<>();

    private List<Waiter> waiters = new ArrayList<>();

    private Menu menu = Menu.getInstance();

    public void addWaiter(Waiter waiter) {
        waiters.add(waiter);
    }

    public List<Table> getTables() {
        return tables;
    }


    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public Menu getMenu() {
        return menu;
    }
}
