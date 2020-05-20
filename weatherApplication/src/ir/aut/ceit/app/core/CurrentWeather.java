package ir.aut.ceit.app.core;

import ir.aut.ceit.app.io.OutputFileWriter;
import ir.aut.ceit.app.utility.ApiWeather;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * shows current weather
 */
public class CurrentWeather {
    private String api = "";
    private ApiWeather weather;
    private String json;
    private JSONObject jsonObject;
    private Double humidity;
    private String description;
    private String wind;
    private String coord;
    private String name;

    /**
     * this method shows current weather info by city name
     *
     * @param name city name
     * @throws Exception check errors
     */
    public void currentByName(String name) throws Exception {
        api = "http://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=c1a36da97fc27d92e8f856387a984e06";
        weather = new ApiWeather(api);
        json = weather.openUrl();
        jsonObject = new JSONObject(json);
        getInfo();
        showTable();
    }

    /**
     * this method shows current weather info by city id
     *
     * @param id city id
     * @throws IOException cjeck errors
     */
    public void currentById(int id) throws IOException {
        api = "http://api.openweathermap.org/data/2.5/weather?id=" + id + "&appid=c1a36da97fc27d92e8f856387a984e06";
        weather = new ApiWeather(api);
        json = weather.openUrl();
        jsonObject = new JSONObject(json);
        getInfo();
        showTable();
    }

    /**
     * this method shows current weather info by city coordinate
     *
     * @param lon city coordinate
     * @param lat city coordinate
     * @throws IOException check errors
     */
    public void currentByCoordinate(double lon, double lat) throws IOException {
        api = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=c1a36da97fc27d92e8f856387a984e06";
        weather = new ApiWeather(api);
        json = weather.openUrl();
        jsonObject = new JSONObject(json);
        getInfo();
        showTable();
    }

    /**
     * gathers the whole information about weather
     */
    private void getInfo() {
        JSONObject jsonObjectCoord = jsonObject.getJSONObject("coord");
        Double resultLon = jsonObjectCoord.getDouble("lon");
        Double resultLat = jsonObjectCoord.getDouble("lat");
        String resultCoord = "lon=" + resultLon + " , lat=" + resultLat;
        JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
        JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
        String resultDescription = jsonObjectWeather.getString("description");
        JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
        Double resultHumidity = jsonObjectMain.getDouble("humidity");
        JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
        Double resultSpeed = jsonObjectWind.getDouble("speed");
        Double resultDeg = jsonObjectWind.getDouble("deg");
        String resultWind = "speed: " + resultSpeed + "\tdeg: " + resultDeg;
        String resultName = jsonObject.getString("name");
        humidity = resultHumidity;
        name = resultName;
        description = resultDescription;
        wind = resultWind;
        coord = resultCoord;
    }

    /**
     * shows table of weather
     *
     * @throws IOException check errors
     */
    private void showTable() throws IOException {
//        System.out.println("--------------------------------------");
//        System.out.println("city: " + city
//                + "\n" + "humidity: " + humidity
//                + "\n" + "description: " + description
//                + "\n" + "wind-info: " + wind
//                + "\n" + "coordination: " + coord);
//        System.out.println("**************************************");
        OutputFileWriter writer = new OutputFileWriter();
        String table = "========================================================================================================================================" +
                "\ncity			    humidity                description                     wind info                           coordination			    " +
                "\n........................................................................................................................................." +
                "\n" + name + "\t\t\t\t" + humidity + "\t\t\t\t\t" + description + "\t\t\t\t\t" + wind + "\t\t\t\t" + coord +
                "\n========================================================================================================================================";
        writer.saveToLogFile(table, "current log");
        System.out.println(table);
    }
}
