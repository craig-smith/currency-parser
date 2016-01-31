package pl.parser.nbp;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.exchangerate.ExchangeRates;
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

        DateTime from = null;
        DateTime to = null;
        CurrencyCode code = null;

        if (args.length > 0 && args.length == 3) {
            code = CurrencyCode.findCurrencyByCode(args[0]);
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            from = formatter.parseDateTime(args[1]);
            to = formatter.parseDateTime(args[2]);
        } else {
            System.out.println("You must supply the correct arguments!!");
            System.out.print("Currency type");
            System.out.println("Date from");
            System.out.println("Date to");
        }
        List<RootTable> allCurrencyListings = exchangeRates.getExchangeListings(from, to);

        List<TypeCTable> typeCTables = new ArrayList<>();
        for (RootTable rootTable : allCurrencyListings) {
            typeCTables.addAll(rootTable.getFilteredCurrencyList(code));
        }

        BigDecimal buyRateAverage = new ExchangeCalculator().calculateBuyRateAverage(typeCTables);
        BigDecimal sellRateStandardDeviation = new ExchangeCalculator().calculateSellRateStandardDeviation(typeCTables);

        System.out.format("Buy Rate Average: %18.4f%n" , buyRateAverage.doubleValue());
        System.out.format("Sell Rate Standard Deviation: %.4f%n", sellRateStandardDeviation.doubleValue());

    }
}
