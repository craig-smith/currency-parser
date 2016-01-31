package pl.parser.nbp.exchangerate;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.spring.SpringConfig;
import pl.parser.nbp.xml.entity.RootTable;

import java.util.List;

/**
 * Created by Craig on 1/31/2016.
 */
public class ExchangeRatesTest {

    private ApplicationContext applicationContext;
    private ExchangeRates exchangeRates;

    @Before
    public void testSetUp() {
        applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        exchangeRates = (ExchangeRates) applicationContext.getBean("exchangeRates");
    }

    @Test
    public void dateRange() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime from = formatter.parseDateTime("2013-01-28");
        DateTime to = formatter.parseDateTime("2013-01-31");
        List<RootTable> tables = exchangeRates.getExchangeListings(from, to);

        Assert.assertEquals(tables.size(), 4);

    }

}