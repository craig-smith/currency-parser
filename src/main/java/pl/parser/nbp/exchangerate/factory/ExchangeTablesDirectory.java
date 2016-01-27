package pl.parser.nbp.exchangerate.factory;

import java.util.ArrayList;
import org.joda.time.DateTime;
import java.util.List;

/**
 * Created by Craig on 1/24/2016.
 */
class ExchangeTablesDirectory {

    private static final String PATH_TO_DIRECTORY =  "http://www.nbp.pl/kursy/xml/";
    private static final String FILE_NAME = "dir.txt";


    private final DateTime from;
    private final DateTime to;
    private final DateTime currentYear;

    private List<String> directoryPaths;

    protected ExchangeTablesDirectory(DateTime from, DateTime to){
        this.from = from;
        this.to = to;
        currentYear = new DateTime();
        directoryPaths = new ArrayList<String>();
        getDirectoryPaths();

    }

    public List<String> getDirectoryPaths() {
        do{
            if(from.year().equals(currentYear.year())){
                getCurrentYearDirectories();
            }else{
                getPreviousYearDirectories(from.year().toString());
                from.plusYears(1);
            }

        }while(!from.year().equals(to.year()));

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
