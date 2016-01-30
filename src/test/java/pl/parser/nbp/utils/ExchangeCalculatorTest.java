package pl.parser.nbp.utils;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.parser.nbp.xml.entity.TypeCTable;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Craig on 1/30/2016.
 */
public class ExchangeCalculatorTest {

    private ArrayList<TypeCTable> tables;

    private final double delta = .002;

    @Before
    public void setUp() {
        tables = new ArrayList<>();
        TypeCTable table1 = new TypeCTable();
        table1.setBuyRate(new BigDecimal(1.34));
        table1.setSellRate(new BigDecimal(1.34));
        TypeCTable table2 = new TypeCTable();
        table2.setBuyRate(new BigDecimal(2.22));
        table2.setSellRate(new BigDecimal(2.22));
        TypeCTable table3 = new TypeCTable();
        table3.setBuyRate(new BigDecimal(4.44));
        table3.setSellRate(new BigDecimal(4.44));

        tables.add(table1);
        tables.add(table2);
        tables.add(table3);
    }

    @Test
    public void testCalculateBuyRateAverage() throws Exception {
        BigDecimal buyRateAverage = new ExchangeCalculator().calculateBuyRateAverage(tables);

        Assert.assertEquals(2.666666666666667, buyRateAverage.doubleValue(),delta );
    }

    @Test
    public void testCalculateSellRateAverage() throws Exception {
        BigDecimal sellRateAverage = new ExchangeCalculator().calculateSellRateAverage(tables);

        Assert.assertEquals(2.666666666666667, sellRateAverage.doubleValue(), delta);
    }

    @Test
    public void testCalculateBuyRateStandardDeviation() throws Exception {
        BigDecimal buyRateStandardDeviation = new ExchangeCalculator().calculateBuyRateStandardDeviation(tables);

        Assert.assertEquals(1.3043857643435943, buyRateStandardDeviation.doubleValue(), delta);
    }

    @Test
    public void testCalculateSellRateStandardDeviation() throws Exception {
        BigDecimal sellRateStandardDeviation = new ExchangeCalculator().calculateSellRateStandardDeviation(tables);

        Assert.assertEquals(1.3043857643435943, sellRateStandardDeviation.doubleValue(), delta);
    }
}