package pl.parser.nbp.exchangerate;


import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.spring.SpringConfig;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by Craig on 1/27/2016.
 */
public class ExchangeTablesDirectoryTest {

    ApplicationContext ctx;
    @Before
    public void testSetup(){
        ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    @Test
    public void testExchangeTablesDirectories(){
        DateTime beginTime = new DateTime(2014, 2, 20, 13, 0);
        DateTime endTime = new DateTime(2016, 1, 21, 2, 0);

        ExchangeTablesDirectory tablesDirectory = (ExchangeTablesDirectory) ctx.getBean("exchangeTablesDirectory");
        tablesDirectory.setDates(beginTime, endTime);
        List<BufferedReader> directoryPaths = tablesDirectory.getDirectoryReaders();

        Assert.assertEquals(2, directoryPaths.size());

    }


}