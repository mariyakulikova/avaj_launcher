package com.avaj.aircraft;

import com.avaj.exception.AircraftStateException;
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
        long newLongitude = (long)coordinates.getLongitude() + longitudeDelta;
        long newLatitude = (long)coordinates.getLatitude() + latitudeDelta;
        int newHeight = coordinates.getHeight() + heightDelta;

        if (newLongitude <= 0 || newLongitude > Integer.MAX_VALUE || newLatitude <= 0 || newLatitude > Integer.MAX_VALUE) {
            throw new AircraftStateException(
                    "Aircraft coordinates are out of range"
            );
        }

        newHeight = Math.min(MAX_HEIGHT, newHeight);
        newHeight = Math.max(GROUND_HEIGHT, newHeight);

        coordinates = new Coordinates((int)newLongitude, (int)newLatitude, newHeight);

        if (newHeight == 0) {
            SimulationLogger.getInstance().log(
                    this + " landing."
            );
            weatherTower.unregister(this);
        }
    }

    protected String getCurrentWeather() {
        if (weatherTower == null) {
            throw new AircraftStateException(
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
    public void updateConditions() {}

    @Override
    public String toString() {
        return getClass().getSimpleName() + "#" + name + "(" + id + ")";
    }
}
