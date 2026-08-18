package com.avaj.aircraft;

import com.avaj.exception.WeatherException;

public class JetPlane extends Aircraft {
    private static final String SUN_MSG = "Blue skies ahead! Full speed! ☀\uFE0F✈\uFE0F";
    private static final String RAIN_MSG = "Turbulence and rain. Hold your drinks! \uD83C\uDF27\uFE0F\uD83E\uDD64";
    private static final String FOG_MSG = "Visibility is zero, trusting the instruments! \uD83C\uDF2B\uFE0F\uD83E\uDDED";
    private static final String SNOW_MSG = "Winter is coming at cruising altitude! ❄\uFE0F✈\uFE0F";

    public JetPlane(long id, String name, Coordinates coordinate) {
        super(id, name, coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = getCurrentWeather();

        switch (weather) {
            case "SUN" -> react(SUN_MSG, 0, 10, 2);
            case "RAIN" -> react(RAIN_MSG, 0, 5, 0);
            case "FOG" -> react(FOG_MSG, 0, 1, 0);
            case "SNOW" -> react(SNOW_MSG, 0, 0, -7);
            default -> throw new WeatherException(
                    "Unknown weather: " + weather
            );
        }
    }
}
