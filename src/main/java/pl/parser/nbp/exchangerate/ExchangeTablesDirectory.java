package pl.parser.nbp.exchangerate;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.parser.nbp.http.DocumentFetcher;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Craig on 1/24/2016.
 */
@Component
class ExchangeTablesDirectory {

    private static final String PATH_TO_DIRECTORY =  "http://www.nbp.pl/kursy/xml/";
    private static final String FILE_NAME = "dir";
    private static final String EXTENSION = ".txt";

    @Autowired
    private DocumentFetcher documentFetcher;


    private DateTime from;
    private DateTime to;
    private final DateTime currentYear = new DateTime();

    private List<String> directoryPaths = new ArrayList<String>();



    protected void setDates(final DateTime from, final  DateTime to){
        this.from = new DateTime(from);
        this.to = new DateTime(to);

    }

    protected List<BufferedReader> getDirectoryReaders() {
        boolean quit = false;
        do{
            if(Years.yearsBetween(from, to).getYears() == 0){
                quit = true;
            }
            if(Years.yearsBetween(from, currentYear).getYears() == 0){
                getCurrentYearDirectories();
            }else{
                getPreviousYearDirectories(String.valueOf(from.getYear()));
                from = from.plusYears(1);
            }


        }while(!quit);

        return getReaders();
    }

    private void getPreviousYearDirectories(String year){
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(PATH_TO_DIRECTORY);
        pathBuilder.append(FILE_NAME);
        pathBuilder.append(year);
        pathBuilder.append(EXTENSION);

        directoryPaths.add(pathBuilder.toString());
    }

    private void getCurrentYearDirectories(){
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(PATH_TO_DIRECTORY);
        pathBuilder.append(FILE_NAME);
        pathBuilder.append(EXTENSION);
        directoryPaths.add(pathBuilder.toString());
    }

    private List<BufferedReader> getReaders(){
        List<BufferedReader> readers = new ArrayList<>();
        for(String path: directoryPaths){
            readers.add(documentFetcher.getDocument(path));
        }

        return readers;
    }
}
