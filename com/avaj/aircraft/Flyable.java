package com.avaj.aircraft;

import com.avaj.tower.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public void registerTower(WeatherTower tower) {
        if (tower == null) {
            throw new IllegalArgumentException("WeatherTower cannot be null");
        }

        weatherTower = tower;
        tower.register(this);
    }
}
