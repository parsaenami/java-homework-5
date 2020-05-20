package ir.aut.ceit.app.core;

import ir.aut.ceit.app.io.OutputFileWriter;
import ir.aut.ceit.app.utility.ApiWeather;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ForecastWeather extends BaseWeather {
    private String api = "";
    private int days = 0;
    private OutputFileWriter writer = new OutputFileWriter();
    private ApiWeather weather;
    private String json;
    private JSONObject jsonObject;
    private String city;
    private double humidity;
    private String description;
    private String wind;
    private String coord;
    private String totalTable = "";

    public ForecastWeather(int days) {
        super();
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    /**
     * gathers the whole information about weather
     */
    private void getInfo() {
        city = getCity();
        humidity = getHumidity();
        description = getDescription();
        wind = getWind();
        coord = getCoord();
    }

    /**
     * this method shows forecast weather info by city name
     *
     * @param name city name
     * @throws Exception check errors
     */
    public void forecastByName(String name) throws Exception {
        api = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + name + "&cnt=" + days + "&appid=c1a36da97fc27d92e8f856387a984e06";
        weather = new ApiWeather(api);
        json = weather.openUrl();
        jsonObject = new JSONObject(json);
        getInfo();
        showTable();
    }

    /**
     * this method shows forecast weather info by city id
     *
     * @param id city id
     * @throws Exception check errors
     */
    public void forecastById(int id) throws IOException {
        api = "http://api.openweathermap.org/data/2.5/forecast/daily?id=" + id + "&cnt=" + days + "&appid=c1a36da97fc27d92e8f856387a984e06";
        weather = new ApiWeather(api);
        json = weather.openUrl();
        jsonObject = new JSONObject(json);
        getInfo();
        showTable();
    }

    /**
     * this method shows forecast weather info by city coordination
     *
     * @param lon city coordination
     * @param lat city coordination
     * @throws IOException check errors
     */
    public void forecastByCoordinate(double lon, double lat) throws IOException {
        api = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + lat + "&lon=" + lon + "&cnt=" + days + "&appid=c1a36da97fc27d92e8f856387a984e06";
        weather = new ApiWeather(api);
        json = weather.openUrl();
        jsonObject = new JSONObject(json);
        getInfo();
        showTable();
    }

    /**
     * this method gets city name
     *
     * @return city name
     */
    private String getCity() {
        JSONObject jsonObjectCity = jsonObject.getJSONObject("city");
        return jsonObjectCity.getString("name");
    }

    /**
     * this method gets city humidity
     *
     * @return city humidity
     */
    private double getHumidity() {
        JSONArray list = jsonObject.getJSONArray("list");
        double resultHumidity = -1;
        for (int i = 0; i < list.length(); i++) {
            JSONObject date = list.getJSONObject(i);
            resultHumidity = date.getDouble("humidity");
        }
        return resultHumidity;
    }

    /**
     * this method gets city wind info
     *
     * @return city wind info
     */
    private String getWind() {
        JSONArray list = jsonObject.getJSONArray("list");
        double resultSpeed = -1;
        double resultDeg = -1;
        for (int i = 0; i < list.length(); i++) {
            JSONObject date = list.getJSONObject(i);
            resultSpeed = date.getDouble("speed");
            resultDeg = date.getDouble("deg");
        }
        return "speed: " + resultSpeed + "  /  deg: " + resultDeg;
    }

    /**
     * this method gets city description
     *
     * @return city description
     */
    private String getDescription() {
        JSONArray list = jsonObject.getJSONArray("list");
        String resultDescription = "";
        for (int i = 0; i < list.length(); i++) {
            JSONObject date = list.getJSONObject(i);
            JSONArray weather = date.getJSONArray("weather");
            JSONObject info = weather.getJSONObject(0);
            resultDescription = info.getString("description");
        }
        return resultDescription;
    }

    /**
     * this method gets city coordination
     *
     * @return city coordination
     */
    private String getCoord() {
        double resultLon;
        double resultLat;
        JSONObject city = jsonObject.getJSONObject("city");
        JSONObject coord = city.getJSONObject("coord");
        resultLon = coord.getDouble("lon");
        resultLat = coord.getDouble("lat");
        return "lon: " + resultLon + "  /  lat: " + resultLat;
    }

    /**
     * this method  prints table
     */
    private void showTable() {
//        System.out.println("day" + days);
//        System.out.println("--------------------------------------");
//        System.out.println("city: " + city
//                + "\n" + "humidity: " + humidity
//                + "\n" + "description: " + description
//                + "\n" + "wind-info: " + wind
//                + "\n" + "coordination: " + coord);
//        System.out.println("**************************************");


        String table = "========================================================================================================================================" +
                "\ncity			    humidity                description                     wind info                           coordination			    " +
                "\n........................................................................................................................................." +
                "\n" + city + "\t\t\t\t" + humidity + "\t\t\t\t\t" + description + "\t\t\t\t\t" + wind + "\t\t\t\t" + coord +
                "\n========================================================================================================================================\n";
        totalTable += table;

        System.out.println("day " + days + "\n" + table);
    }

    /**
     * this method save the table to a file
     *
     * @throws IOException check errors
     */
    public void saveToFile() throws IOException {
        writer.saveToLogFile(totalTable, "forecast log");
    }
}
