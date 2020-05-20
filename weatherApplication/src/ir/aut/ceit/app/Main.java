package ir.aut.ceit.app;

import ir.aut.ceit.app.core.CurrentWeather;
import ir.aut.ceit.app.core.ForecastWeather;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    /**
     * to run the program
     *
     * @param args input arguments
     * @throws Exception check errors
     */
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        menu(scan);

    }

    /**
     * shows menu
     *
     * @param scan gives user's inputs
     * @throws Exception check errors
     */
    private static void menu(Scanner scan) throws Exception {
        ForecastWeather forecastWeather;
        CurrentWeather currentWeather;
        System.out.println("choose one:\n1. forecast\n2. current");
        switch (scan.nextInt()) {
            case 1:
                System.out.println("how many days do you wanna forecast? :");
                forecastWeather = new ForecastWeather(scan.nextInt());
                forecast(scan, forecastWeather);
                continuing(scan);
                break;
            case 2:
                currentWeather = new CurrentWeather();
                current(scan, currentWeather);
                continuing(scan);
            default:
                System.out.println("wrong choice!");
                menu(scan);
        }
    }

    /**
     * to run current
     *
     * @param scan           gives user's inputs
     * @param currentWeather current weather class
     * @throws Exception check errors
     */
    private static void current(Scanner scan, CurrentWeather currentWeather) throws Exception {
        System.out.println("how do you want to forecast?\n1. city name\n2. city id\n3. city coordination");
        switch (scan.nextInt()) {
            case 1:
                System.out.println("enter city name:");
                currentWeather.currentByName(scan.next());
                continuing(scan);
                break;
            case 2:
                System.out.println("enter city id:");
                currentWeather.currentById(scan.nextInt());
                continuing(scan);
                break;
            case 3:
                System.out.println("enter city lon:");
                double lon = scan.nextDouble();
                System.out.println("enter city lat:");
                double lat = scan.nextDouble();
                currentWeather.currentByCoordinate(lon, lat);
                continuing(scan);
                break;
            default:
                System.out.println("wrong choice!");
                current(scan, currentWeather);
        }
    }

    /**
     * to run forecast
     *
     * @param scan            gives user's inputs
     * @param forecastWeather forecast weather class
     * @throws Exception check errors
     */
    private static void forecast(Scanner scan, ForecastWeather forecastWeather) throws Exception {
        int temp;
        System.out.println("how do you want to forecast?\n1. city name\n2. city id\n3. city coordination");
        switch (scan.nextInt()) {
            case 1:
                System.out.println("enter city name:");
                String cityName = scan.next();
                temp = forecastWeather.getDays();
                for (int i = 0; i < temp; i++) {
                    forecastWeather.forecastByName(cityName);
                    forecastWeather.setDays(forecastWeather.getDays() - 1);
                }
                forecastWeather.saveToFile();
                continuing(scan);
                break;
            case 2:
                System.out.println("enter city id:");
                int cityId = scan.nextInt();
                temp = forecastWeather.getDays();
                for (int i = 0; i < temp; i++) {
                    forecastWeather.forecastById(cityId);
                    forecastWeather.setDays(forecastWeather.getDays() - 1);
                }
                forecastWeather.saveToFile();
                continuing(scan);
                break;
            case 3:
                System.out.println("enter city lon:");
                double lon = scan.nextDouble();
                System.out.println("enter city lat:");
                double lat = scan.nextDouble();
                temp = forecastWeather.getDays();
                for (int i = 0; i < temp; i++) {
                    forecastWeather.forecastByCoordinate(lon, lat);
                    forecastWeather.setDays(forecastWeather.getDays() - 1);
                }
                forecastWeather.saveToFile();
                continuing(scan);
                break;
            default:
                System.out.println("wrong choice!");
                forecast(scan, forecastWeather);
        }
    }

    /**
     * checks if user wants to continue or not
     *
     * @param scan gives user's inputs
     * @throws Exception check errors
     */
    private static void continuing(Scanner scan) throws Exception {
        System.out.println("do you want to continue?\n1. yes\n2. no");
        switch (scan.nextInt()) {
            case 1:
                menu(scan);
                break;
            case 2:
                exit(1);
                break;
            default:
                System.out.println("wrong choice!");
                continuing(scan);
        }
    }
}
