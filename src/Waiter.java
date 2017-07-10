import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Waiter {

    private final String name;

    private final List<Table> assignedTables = new ArrayList<>();

    public Waiter(final String name) {
        this.name = name;
    }

    public List<Table> getAssignedTables() {
        return assignedTables;
    }

    public void assignTable(Table table) {
        assignedTables.add(table);
    }

    public void removeTable(Table table) {
        assignedTables.remove(table);
    }

    public String getName() {
        return name;
    }

}
