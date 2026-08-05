package com.avaj.tower;

import com.avaj.aircraft.Coordinates;
import com.avaj.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        conditionChanged();

    }
}
