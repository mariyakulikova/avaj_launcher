package com.avaj.aircraft;

public class Helicopter extends Aircraft {
    public Helicopter(long id, String name, Coordinates coordinate) {
        super(id, name, coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = getCurrentWeather();

        switch (weather) {
            case "SUN" -> react("SUN", 10, 0, 2);
            case "RAIN" -> react("RAIN", 5, 0, 0);
            case "FOG" -> react("FOG", 1, 0, 0);
            case "SNOW" -> react("SNOW", 0, 0, -12);
            default -> throw new IllegalStateException(
                    "Unknown weather: " + weather
            );
        }
    }
}
