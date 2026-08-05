package com.avaj.aircraft;

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
        if (type == null) throw new IllegalArgumentException("Aircraft type is null");
        Flyable flyable;
        long id = nextId;

        flyable = switch (type) {
            case "JetPlane" -> new JetPlane(id, name, coordinates);
            case "Balloon" -> new Balloon(id, name, coordinates);
            case "Helicopter" -> new Helicopter(id, name, coordinates);
            default -> throw new IllegalArgumentException("Unknown aircraft type: " + type);
        };
        this.nextId += 1;
        return flyable;
    }
}
