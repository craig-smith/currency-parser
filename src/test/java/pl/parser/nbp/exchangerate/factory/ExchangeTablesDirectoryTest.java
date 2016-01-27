package pl.parser.nbp.exchangerate.factory;


import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Craig on 1/27/2016.
 */
public class ExchangeTablesDirectoryTest {
    @Test
    public void testExchangeTablesDirectories(){
        DateTime beginTime = new DateTime(2014, 2, 20, 13, 0);
        DateTime endTime = new DateTime(2016, 1, 21, 2, 0);

        ExchangeTablesDirectory tablesDirectory = new ExchangeTablesDirectory(beginTime, endTime);
        List<String> directoryPaths = tablesDirectory.getDirectoryPaths();

        Assert.assertEquals(2, directoryPaths.size());
        Assert.assertEquals("http://www.nbp.pl/kursy/xml/2014dir.txt", directoryPaths.get(0));
    }

}