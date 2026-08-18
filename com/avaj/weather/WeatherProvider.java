package com.avaj.weather;

import com.avaj.aircraft.Coordinates;

public class WeatherProvider {
    private final String[] weather;
    private static WeatherProvider instance;

    private WeatherProvider() {
        this.weather = new String[]{
                "RAIN",
                "FOG",
                "SUN",
                "SNOW",
        };
    }

    public static WeatherProvider getInstance() {
        if (WeatherProvider.instance == null) {
            WeatherProvider.instance = new WeatherProvider();
        }
        return WeatherProvider.instance;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }

        long weatherCode = coordinates.getLongitude()
                         + 2L * coordinates.getLatitude()
                         + 3L * coordinates.getHeight();

        int index = Math.floorMod(weatherCode, weather.length);

        return weather[index];
    }
}
