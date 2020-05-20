package ir.aut.ceit.app.io;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * using to read from web
 */
public class InputFileReader {
    /**
     * this method reads url from web
     *
     * @param httpURLConnection read url
     * @return json content
     */
    public String readUrl(HttpURLConnection httpURLConnection) {
        String result = "";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
        } catch (IOException | JSONException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
