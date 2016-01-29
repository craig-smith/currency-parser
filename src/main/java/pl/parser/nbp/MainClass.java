package pl.parser.nbp;

import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.exchangerate.factory.ExchangeRates;
import pl.parser.nbp.spring.SpringConfig;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by Craig on 1/28/2016.
 */
public class MainClass {

    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        ExchangeRates exchangeRates = (ExchangeRates) ctx.getBean("exchangeRates");
        List<BufferedReader> exchangeDirectories =  exchangeRates.getExchangeDirectories(new DateTime(2012,1,1,1,1), new DateTime(DateTime.now()));
    }
}
