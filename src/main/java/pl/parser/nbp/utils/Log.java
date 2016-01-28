package pl.parser.nbp.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by Craig on 1/28/2016.
 */
public class Log {


    public static void configureLogger(){
        PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("log4j.properties"));
    }
}
