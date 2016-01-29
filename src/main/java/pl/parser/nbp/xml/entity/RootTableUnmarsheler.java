package pl.parser.nbp.xml.entity;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Craig on 1/29/2016.
 */
@Component
public class RootTableUnmarsheler {
    static Logger log = Logger.getLogger(RootTableUnmarsheler.class);

    public RootTable unmarshel(BufferedReader reader){
        RootTable table = null;
        log.debug("unmarsheling buffered reader: " + reader.toString());
        try{
            JAXBContext context = JAXBContext.newInstance(RootTable.class);
            Unmarshaller um = context.createUnmarshaller();
            table = (RootTable) um.unmarshal(reader);
            log.debug("Unmarsheled file " + table.toString());
        } catch (JAXBException e){
            log.error("Error unmarsheling file ", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
               log.error("Error closing buffered reader ", e);
            }
        }
        return table;
    }

}
