package com.avaj.weather;

import com.avaj.aircraft.Coordinates;

public class WeatherProvider {
    private String[] weather;
    private static WeatherProvider instance;

    private WeatherProvider(String[] weather) {
        this.weather = weather;
    }

    public static WeatherProvider getInstance() {
        if (WeatherProvider.instance == null) {
            WeatherProvider.instance = new WeatherProvider();
        }
        return WeatherProvider.instance;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return "";
    }
}
