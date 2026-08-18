package com.avaj.aircraft;

import com.avaj.exception.WeatherException;

public class Helicopter extends Aircraft {
    private static final String SUN_MSG = "Clear skies and happy rotors! ☀\uFE0F\uD83D\uDE81";
    private static final String RAIN_MSG = "The rain is hammering my rotor! \uD83C\uDF27\uFE0F\uD83D\uDE81";
    private static final String FOG_MSG = "Flying blind was not in today's plan! \uD83C\uDF2B\uFE0F\uD83D\uDE35";
    private static final String SNOW_MSG = "My rotor is turning into an icicle! ❄\uFE0F\uD83E\uDDCA";

    public Helicopter(long id, String name, Coordinates coordinate) {
        super(id, name, coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = getCurrentWeather();

        switch (weather) {
            case "SUN" -> react(SUN_MSG, 10, 0, 2);
            case "RAIN" -> react(RAIN_MSG, 5, 0, 0);
            case "FOG" -> react(FOG_MSG, 1, 0, 0);
            case "SNOW" -> react(SNOW_MSG, 0, 0, -12);
            default -> throw new WeatherException(
                    "Unknown weather: " + weather
            );
        }
    }
}
