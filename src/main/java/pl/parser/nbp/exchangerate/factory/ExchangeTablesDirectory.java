package pl.parser.nbp.exchangerate.factory;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.Years;

import java.util.List;

/**
 * Created by Craig on 1/24/2016.
 */
class ExchangeTablesDirectory {

    private static final String PATH_TO_DIRECTORY =  "http://www.nbp.pl/kursy/xml/";
    private static final String FILE_NAME = "dir.txt";


    private DateTime from;
    private DateTime to;
    private final DateTime currentYear;

    private List<String> directoryPaths;

    protected ExchangeTablesDirectory(final DateTime from, final DateTime to){
        this.from = new DateTime(from);
        this.to = new DateTime(to);
        currentYear = new DateTime();
        directoryPaths = new ArrayList<String>();

    }

    protected List<String> getDirectoryPaths() {
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

        return directoryPaths;
    }

    private void getPreviousYearDirectories(String year){
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(PATH_TO_DIRECTORY);
        pathBuilder.append(year);
        pathBuilder.append(FILE_NAME);

        directoryPaths.add(pathBuilder.toString());
    }

    private void getCurrentYearDirectories(){
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(PATH_TO_DIRECTORY);
        pathBuilder.append(FILE_NAME);
        directoryPaths.add(pathBuilder.toString());
    }
}
