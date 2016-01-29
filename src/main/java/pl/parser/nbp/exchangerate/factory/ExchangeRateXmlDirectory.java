package pl.parser.nbp.exchangerate.factory;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Craig on 1/28/2016.
 */
@Component
public class ExchangeRateXmlDirectory {

    static Logger log = Logger.getLogger(ExchangeRateXmlDirectory.class);
    private List<String> exchangeRateFiles = new ArrayList<String>();

    private final String PATH_TO_DIRECTORY = "http://www.nbp.pl/kursy/xml/";
    private final String EXTENSION = ".xml";

    private DateTime from;
    private DateTime to;

    protected List<String> getExchangeRateXmlDirectories(final DateTime from, final DateTime to, final List<BufferedReader> directoryReaders){
        log.debug("Starting reading year directories total " + directoryReaders.size());
        this.from = from;
        this.to = to;
        for(BufferedReader directoryReader : directoryReaders){
            if(directoryReader != null){
                getExchangeRateXmlPaths(directoryReader);
            }
        }

        return exchangeRateFiles;
    }

    private void getExchangeRateXmlPaths(BufferedReader reader) {
        log.debug("Reading directory for " + reader.toString());
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                formatPath(line);
            }
        }catch (IOException e){
            log.error("Error reading file ", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                log.error("Error closing buffered reader ", e);
            }
        }
    }

    private void formatPath(String line){
        if(line != null && line.startsWith("c")) {
            log.debug("found currency xml listing " + line);
            String[] split = line.split("z");
            String series = split[0];
            String date = split[1];

            DateTimeFormatter format = DateTimeFormat.forPattern("yyMMdd");
            DateTime xmlFileDate = format.parseDateTime(date);
            if(from.isBefore(xmlFileDate.minusDays(1)) && to.isAfter(xmlFileDate.plusDays(1))){
                String path = PATH_TO_DIRECTORY + series + "z" + date + EXTENSION;
                exchangeRateFiles.add(path);
                log.debug("added path to list " + path);
            }
        }
    }
}
