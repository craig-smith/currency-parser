package pl.parser.nbp.http;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Craig on 1/27/2016.
 */
public class DocumentFetcher {
static Logger log = Logger.getLogger(DocumentFetcher.class);
    private HttpURLConnection httpURLConnection;
    private BufferedReader bufferedReader;


    public BufferedReader getDocument(String path){
        BufferedReader bufferedReader = null;
        URL url = getUrl(path);
        HttpURLConnection httpURLConnection = getHttpConnection(url);
        bufferedReader = getStreamReader(httpURLConnection);
        return bufferedReader;
    }

    private BufferedReader getStreamReader(HttpURLConnection httpURLConnection) {
        BufferedReader bufferedReader = null;
        try{
            log.debug("getting Reader for " + httpURLConnection);
           bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        }catch (IOException e){
            log.error("Error getting input stream ", e);
        }
       return bufferedReader;
    }

    private URL getUrl(String path){
        URL documentUrl = null;
        try{
            log.debug("getting url for " + path);
            documentUrl = new URL(path);
        }catch (MalformedURLException e){
            log.error("Invalid URL. " + path, e);
        }

        return documentUrl;
    }

    private HttpURLConnection getHttpConnection(URL url){
        HttpURLConnection httpURLConnection = null;
        try{
            log.debug("Opening Connection to " + url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }catch (IOException e){
            log.error("Error opening connection to " + url, e);
        }

        return httpURLConnection;
    }
}
