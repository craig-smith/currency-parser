package pl.parser.nbp.xml.entity;

import junit.framework.TestCase;
import org.junit.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.util.List;

/**
 * Created by Craig on 1/30/2016.
 */
public class RootTableUnmarshelerTest extends TestCase {

    public void testUnmarshel() throws Exception {

        JAXBContext context = JAXBContext.newInstance(RootTable.class);
        Unmarshaller um = context.createUnmarshaller();
        RootTable table = (RootTable) um.unmarshal(new FileReader(Thread.currentThread().getContextClassLoader().getResource("c001z160104.xml").getFile()));

        List<TypeCTable> typeCTables = table.getCurrencyListings();

        Assert.assertEquals(13, typeCTables.size());
        Assert.assertEquals("USD", typeCTables.get(0).getCurrencyCode());
        Assert.assertEquals("1", typeCTables.get(0).getExchangeMultiple().toString());
        Assert.assertEquals("3.8621", typeCTables.get(0).getBuyRate().toString());
        Assert.assertEquals("3.9401", typeCTables.get(0).getSellRate().toString());
    }
}