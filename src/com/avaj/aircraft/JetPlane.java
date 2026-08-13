package com.avaj.aircraft;

public class JetPlane extends Aircraft {
    public JetPlane(long id, String name, Coordinates coordinate) {
        super(id, name, coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = getCurrentWeather();

        switch (weather) {
            case "SUN" -> react("SUN",0, 10, 2);
            case "RAIN" -> react("RAIN",0, 5, 0);
            case "FOG" -> react("FOG",0, 1, 0);
            case "SNOW" -> react("SNOW",0, 0, -7);
            default -> throw new IllegalStateException(
                    "Unknown weather: " + weather
            );
        }
    }
}
