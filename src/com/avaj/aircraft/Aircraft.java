package com.avaj.aircraft;

import com.avaj.logger.SimulationLogger;

public class Aircraft extends Flyable{
    private static final int MAX_HEIGHT = 100;
    private static final int GROUND_HEIGHT = 0;
    protected final long id;
    protected final String name;
    protected Coordinates coordinates;

    protected Aircraft(long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    private void move(int longitudeDelta, int latitudeDelta, int heightDelta) {
        int newLongitude = coordinates.getLongitude() + longitudeDelta;
        int newLatitude = coordinates.getLatitude() + latitudeDelta;
        int newHeight = coordinates.getHeight() + heightDelta;

        newHeight = Math.min(MAX_HEIGHT, newHeight);
        newHeight = Math.max(GROUND_HEIGHT, newHeight);

        coordinates = new Coordinates(newLongitude, newLatitude, newHeight);

        if (newHeight == 0) {
            SimulationLogger.getInstance().log(
                    this + " landing."
            );
            weatherTower.unregister(this);
        }
    }

    protected String getCurrentWeather() {
        if (weatherTower == null) {
            throw new IllegalStateException(
                    "Aircraft is not registered to a weather tower"
            );
        }

        return weatherTower.getWeather(coordinates);
    }

    protected void react(String msg, int longitudeDelta, int latitudeDelta, int heightDelta) {
        SimulationLogger.getInstance().log(
                this + ": " + msg
        );

        move(longitudeDelta, latitudeDelta, heightDelta);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "#" + name + "(" + id + ")";
    }
}
