package pl.parser.nbp.exchangerate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.parser.nbp.http.DocumentFetcher;
import pl.parser.nbp.xml.entity.RootTable;
import pl.parser.nbp.xml.entity.RootTableUnmarsheler;

import java.io.BufferedReader;

/**
 * Created by Craig on 1/31/2016.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RootTableRunnable implements Runnable {

    static Logger log = Logger.getLogger(RootTableRunnable.class);

    @Autowired
    private DocumentFetcher documentFetcher;

    @Autowired
    private RootTableUnmarsheler rootTableUnmarsheler;

    private String path;

    private ExchangeRateList list;

    public void setPath(String path, ExchangeRateList list) {
        log.debug("Path Set for " + path);
        this.path = path;
        this.list = list;
    }

    @Override
    public void run() {
        log.debug(Thread.currentThread().getName() + " Running for path: " + path);
        BufferedReader reader = documentFetcher.getDocument(path);
        RootTable table = rootTableUnmarsheler.unmarshel(reader);
        list.addToList(table);
    }
}
