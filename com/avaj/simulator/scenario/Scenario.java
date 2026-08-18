package com.avaj.simulator.scenario;

import java.util.List;

public record Scenario(int simulationCount, List<AircraftSpec> aircraft) {
    public Scenario {
        aircraft = List.copyOf(aircraft);
    }
}
