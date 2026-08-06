package com.avaj.aircraft;

public class Helicopter extends Aircraft {
    public Helicopter(long id, String name, Coordinates coordinate) {
        super(id, name, coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = getCurrentWeather();

        switch (weather) {
            case "SUN" -> move(10, 0, 2);
            case "RAIN" -> move(5, 0, 0);
            case "FOG" -> move(1, 0, 0);
            case "SNOW" -> move(0, 0, -12);
            default -> throw new IllegalStateException(
                    "Unknown weather: " + weather
            );
        }
    }
}
