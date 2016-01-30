package pl.parser.nbp.exchangerate;

/**
 * Created by Craig on 1/24/2016.
 */

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.parser.nbp.xml.entity.RootTable;

import java.io.BufferedReader;
import java.util.List;

@Component
public class ExchangeRates {

    @Autowired
    ExchangeRateXmlDirectory exchangeRateXmlDirectory;

    @Autowired
    private ExchangeTablesDirectory exchangeTablesDirectory;

    public List<RootTable> getExchangeListings(final DateTime from, final DateTime to) {

        exchangeTablesDirectory.setDates(from, to);

        List<BufferedReader> directoryReaders = exchangeTablesDirectory.getDirectoryReaders();

        List<RootTable> xmlFileObjects = exchangeRateXmlDirectory.getExchangeRateXmlObjectsByType(from, to, directoryReaders);


        return xmlFileObjects;
    }


}
