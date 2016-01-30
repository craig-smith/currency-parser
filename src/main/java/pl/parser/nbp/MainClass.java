package pl.parser.nbp;

import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.exchangerate.factory.ExchangeRates;
import pl.parser.nbp.spring.SpringConfig;
import pl.parser.nbp.utils.ExchangeCalculator;
import pl.parser.nbp.xml.entity.CurrencyCode;
import pl.parser.nbp.xml.entity.RootTable;
import pl.parser.nbp.xml.entity.TypeCTable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Craig on 1/28/2016.
 */
public class MainClass {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        ExchangeRates exchangeRates = (ExchangeRates) ctx.getBean("exchangeRates");
        List<RootTable> allCurrencyListings = exchangeRates.getExchangeListings(new DateTime(2013, 1, 28, 0, 0), new DateTime(2013, 1, 31, 0, 0));

        List<TypeCTable> typeCTables = new ArrayList<>();
        for (RootTable rootTable : allCurrencyListings) {
            typeCTables.addAll(rootTable.getFilteredCurrencyList(CurrencyCode.EUR));
        }

        BigDecimal buyRateAverage = new ExchangeCalculator().calculateBuyRateAverage(typeCTables);
        BigDecimal sellRateStandardDeviation = new ExchangeCalculator().calculateSellRateStandardDeviation(typeCTables);

        System.out.println("Buy Rate Average: " + buyRateAverage.toPlainString());
        System.out.println("Sell Rate Standard Deviation: " + sellRateStandardDeviation.toPlainString());

    }
}
