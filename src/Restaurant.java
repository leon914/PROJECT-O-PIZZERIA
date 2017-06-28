import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lhi06 on 15/06/2017.
 */
public class Restaurant {

    private List<Table> tables = new ArrayList<>();

    private final Waiter carlos = new Waiter("Carlos");

    public void initRestaurant() {

        for (int i = 1; i <= 6; i++) {
            tables.add(new Table(i));
        }

    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public Waiter getWaiter(){
        return carlos;
    }
}
