package com.avaj.aircraft;

public class Balloon extends Aircraft {
    public Balloon(long id, String name, Coordinates coordinate) {
        super(id, name, coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = getCurrentWeather();

        switch (weather) {
            case "SUN" -> move(2, 0, 4);
            case "RAIN" -> move(0, 0, -5);
            case "FOG" -> move(0, 0, -3);
            case "SNOW" -> move(0, 0, -15);
            default -> throw new IllegalStateException(
                    "Unknown weather: " + weather
            );
        }
    }
}
