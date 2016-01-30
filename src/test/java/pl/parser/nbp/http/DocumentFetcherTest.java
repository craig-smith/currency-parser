package pl.parser.nbp.http;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Craig on 1/28/2016.
 */
public class DocumentFetcherTest {
    static Logger log = Logger.getLogger(DocumentFetcherTest.class);
    @Test
    public void testGetDocument(){

        DocumentFetcher documentFetcher = new DocumentFetcher();

        try( BufferedReader bufferedReader = documentFetcher.getDocument("http://www.google.com")){
            Assert.assertNotNull(bufferedReader);
        }catch (IOException e){
            log.error(e);
        }



    }

}