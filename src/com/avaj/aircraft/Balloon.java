package com.avaj.aircraft;

public class Balloon extends Aircraft {
    private static final String SUN_MSG = "Perfect weather for floating! ☀\uFE0F\uD83C\uDF88";
    private static final String RAIN_MSG = "Rain is ruining my balloon trip! \uD83C\uDF27\uFE0F\uD83C\uDF88";
    private static final String FOG_MSG = "I can't see where I'm floating! \uD83C\uDF2B\uFE0F\uD83D\uDC40";
    private static final String SNOW_MSG = "My basket is turning into a freezer! ❄\uFE0F\uD83E\uDD76";

    public Balloon(long id, String name, Coordinates coordinate) {
        super(id, name, coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = getCurrentWeather();

        switch (weather) {
            case "SUN" -> react(SUN_MSG, 2, 0, 4);
            case "RAIN" -> react(RAIN_MSG, 0, 0, -5);
            case "FOG" -> react(FOG_MSG, 0, 0, -3);
            case "SNOW" -> react(SNOW_MSG, 0, 0, -15);
            default -> throw new IllegalStateException(
                    "Unknown weather: " + weather
            );
        }
    }
}
