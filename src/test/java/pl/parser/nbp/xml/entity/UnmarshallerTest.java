package pl.parser.nbp.xml.entity;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pl.parser.nbp.utils.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;


/**
 * Created by Craig on 1/24/2016.
 */

public class UnmarshallerTest {

    static Logger log = Logger.getLogger(UnmarshallerTest.class);

    @Test
    public void testJaxUnmarshal() throws JAXBException, FileNotFoundException {
        Log.configureLogger();
        JAXBContext context = JAXBContext.newInstance(RootTable.class);
        Unmarshaller um = context.createUnmarshaller();
        RootTable table = (RootTable) um.unmarshal(new FileReader(Thread.currentThread().getContextClassLoader().getResource("c001z160104.xml").getFile()));
        log.debug("Unmarshaled file " + table.toString());
        List<TypeCTable> typeCTables =  table.getCurrencyListings();

        Assert.assertEquals(13, typeCTables.size());
        Assert.assertEquals("USD", typeCTables.get(0).getCurrencyCode());
        Assert.assertEquals("1", typeCTables.get(0).getExchangeMultiple().toString());
        Assert.assertEquals("3.8621", typeCTables.get(0).getBuyRate().toString());
        Assert.assertEquals("3.9401", typeCTables.get(0).getSellRate().toString());

    }
}
