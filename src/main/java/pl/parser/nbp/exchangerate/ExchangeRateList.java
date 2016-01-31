package pl.parser.nbp.exchangerate;

import pl.parser.nbp.xml.entity.RootTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Craig on 1/31/2016.
 */
public class ExchangeRateList {

    private List<RootTable> tables = new ArrayList<>();

    public synchronized void addToList(RootTable table) {
        tables.add(table);
    }

    public synchronized List<RootTable> getList() {
        return tables;
    }
}
