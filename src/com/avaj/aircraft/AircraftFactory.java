package com.avaj.aircraft;

public class AircraftFactory {

    private static AircraftFactory instance;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (AircraftFactory.instance == null) {
            AircraftFactory.instance = new AircraftFactory();
        }
        return AircraftFactory.instance;
    }

    public Flyable newAircraft(String type, String name, Coordinates coordinates) {
        throw new UnsupportedOperationException(
                "Aircraft creation is not implemented yet"
        );
    }
}
