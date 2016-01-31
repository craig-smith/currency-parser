package pl.parser.nbp.exchangerate;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.parser.nbp.http.DocumentFetcher;
import pl.parser.nbp.xml.entity.RootTable;
import pl.parser.nbp.xml.entity.RootTableUnmarsheler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Craig on 1/28/2016.
 */
@Component
class ExchangeRateXmlDirectory {

    static Logger log = Logger.getLogger(ExchangeRateXmlDirectory.class);
    private final String PATH_TO_DIRECTORY = "http://www.nbp.pl/kursy/xml/";
    private final String EXTENSION = ".xml";
    private final String TODAY_lISTING_FILE = "LastC";
    private List<String> exchangeRateFiles = new ArrayList<>();
    private DateTime from;
    private DateTime to;


    @Autowired
    private DocumentFetcher documentFetcher;

    @Autowired
    private RootTableUnmarsheler rootTableUnmarsheler;

    protected List<RootTable> getExchangeRateXmlObjectsByType(final DateTime from, final DateTime to, final List<BufferedReader> directoryReaders) {
        log.debug("Starting reading year directories total " + directoryReaders.size());
        this.from = from;
        this.to = to;

        for (BufferedReader directoryReader : directoryReaders) {
            if (directoryReader != null) {
                getExchangeRateXmlPaths(directoryReader);
            }
        }
        if (to.toLocalDate().equals(new LocalDate())) {
            addTodayToList();
        }
        return getObjects();
    }

    private void addTodayToList() {
        StringBuilder todayPath = new StringBuilder();
        todayPath.append(PATH_TO_DIRECTORY);
        todayPath.append(TODAY_lISTING_FILE);
        todayPath.append(EXTENSION);
        exchangeRateFiles.add(todayPath.toString());
    }

    private void getExchangeRateXmlPaths(BufferedReader reader) {
        log.debug("Reading directory for " + reader.toString());
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                formatPath(line);
            }
        } catch (IOException e) {
            log.error("Error reading file ", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                log.error("Error closing buffered reader ", e);
            }
        }
    }

    private void formatPath(String line) {
        if (line != null && line.startsWith("c")) {
            log.debug("found currency xml listing " + line);
            String[] split = line.split("z");
            String series = split[0];
            String date = split[1];

            DateTimeFormatter format = DateTimeFormat.forPattern("yyMMdd");
            DateTime xmlFileDate = format.parseDateTime(date);
            if (from.isBefore(xmlFileDate.plusDays(1)) && to.isAfter(xmlFileDate.minusDays(1))) {
                String path = PATH_TO_DIRECTORY + series + "z" + date + EXTENSION;
                exchangeRateFiles.add(path);
                log.debug("added path to list " + path);
            }
        }
    }

    private List<RootTable> getObjects() {
        List<RootTable> exchangeRates = new ArrayList<>();
        for (String path : exchangeRateFiles) {
            BufferedReader xmlReader = documentFetcher.getDocument(path);
            exchangeRates.add(rootTableUnmarsheler.unmarshel(xmlReader));
        }
        return exchangeRates;
    }
}
