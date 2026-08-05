package com.avaj.weather;

import com.avaj.aircraft.Coordinates;

public class WeatherProvider {
    private final String[] weather;
    private static WeatherProvider instance;

    private WeatherProvider() {
        this.weather = new String[]{
                "RAIN",
                "FOG",
                "SNOW",
                "SUN",
        };
    }

    public static WeatherProvider getInstance() {
        if (WeatherProvider.instance == null) {
            WeatherProvider.instance = new WeatherProvider();
        }
        return WeatherProvider.instance;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return this.weather[0];
    }
}
