package pl.parser.nbp.exchangerate.factory;

/**
 * Created by Craig on 1/24/2016.
 */

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.parser.nbp.http.DocumentFetcher;
import pl.parser.nbp.xml.entity.RootTable;
import pl.parser.nbp.xml.entity.RootTableUnmarsheler;


import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeRates {

    @Autowired
    ExchangeRateXmlDirectory exchangeRateXmlDirectory;

    @Autowired
    private ExchangeTablesDirectory exchangeTablesDirectory;

    @Autowired
    private DocumentFetcher documentFetcher;

    @Autowired
    private RootTableUnmarsheler rootTableUnmarsheler;

    public List<BufferedReader> getExchangeDirectories(final DateTime from, final DateTime to){

        exchangeTablesDirectory.setDates(from, to);

        List<String> directories =  exchangeTablesDirectory.getDirectoryPaths();
        List<BufferedReader> directoryReaders = new ArrayList<BufferedReader>();
        for(String directory : directories){
            directoryReaders.add(documentFetcher.getDocument(directory));
        }
        List<String> xmlFilePaths = exchangeRateXmlDirectory.getExchangeRateXmlDirectories(from, to, directoryReaders);


        List<BufferedReader> xmlReaders = new ArrayList<>();
        for(String path : xmlFilePaths){
          xmlReaders.add(documentFetcher.getDocument(path));
        }

        List<RootTable> unmarsheledTables = new ArrayList<RootTable>();
        for(BufferedReader xmlReader : xmlReaders){
           unmarsheledTables.add(rootTableUnmarsheler.unmarshel(xmlReader));
        }
        return directoryReaders;
    }


}
