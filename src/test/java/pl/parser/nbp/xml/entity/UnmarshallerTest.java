package pl.parser.nbp.xml.entity;

import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void testJaxUnmarshal() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(RootTable.class);
        Unmarshaller um = context.createUnmarshaller();
        RootTable table = (RootTable) um.unmarshal(new FileReader("C:\\Users\\Craig.HOME\\IdeaProjects\\currency-parser\\target\\classes\\c001z160104.xml"));

        List<TypeCTable> typeCTables =  table.getCurrencyListings();

        Assert.assertEquals(13, typeCTables.size());
        Assert.assertEquals("USD", typeCTables.get(0).getCurrencyCode());
        Assert.assertEquals("1", typeCTables.get(0).getExchangeMultiple().toString());
        Assert.assertEquals("3.8621", typeCTables.get(0).getBuyRate().toString());
        Assert.assertEquals("3.9401", typeCTables.get(0).getSellRate().toString());

    }
}
