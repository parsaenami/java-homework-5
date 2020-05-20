package ir.aut.ceit.app.utility;

import ir.aut.ceit.app.io.InputFileReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * using to handle api
 */
public class ApiWeather {
    private String url = "";

    public ApiWeather(String URL) {
        this.url = URL;
    }

    /**
     * opens url from internet and gives back json content
     *
     * @return json objects
     * @throws IOException check errors
     */
    public String openUrl() throws IOException {
        java.net.URL urlWeather = new URL(url);
        InputFileReader reader = new InputFileReader();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlWeather.openConnection();
        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            return reader.readUrl(httpURLConnection);
        else
            return "Error in httpURLConnection.getResponseCode()!";
    }


}
