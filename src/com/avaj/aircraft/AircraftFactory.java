package com.avaj.aircraft;

import com.avaj.exception.AircraftCreationException;

public class AircraftFactory {

    private static AircraftFactory instance;
    private long nextId = 1;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (AircraftFactory.instance == null) {
            AircraftFactory.instance = new AircraftFactory();
        }
        return AircraftFactory.instance;
    }

    public Flyable newAircraft(String type, String name, Coordinates coordinates) {
        if (type == null) {
            throw new AircraftCreationException("Aircraft type is null");
        }
        Flyable flyable;
        long id = nextId;

        flyable = switch (type) {
            case "JetPlane" -> new JetPlane(id, name, coordinates);
            case "Balloon" -> new Balloon(id, name, coordinates);
            case "Helicopter" -> new Helicopter(id, name, coordinates);
            default -> throw new AircraftCreationException("Unknown aircraft type: " + type);
        };
        this.nextId += 1;
        return flyable;
    }

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        return newAircraft(type, name, coordinates);
    }
}
