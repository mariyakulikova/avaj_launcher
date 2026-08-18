package com.avaj.simulator;

import com.avaj.aircraft.AircraftFactory;
import com.avaj.aircraft.Flyable;
import com.avaj.exception.AvajException;
import com.avaj.logger.SimulationLogger;
import com.avaj.simulator.scenario.AircraftSpec;
import com.avaj.simulator.scenario.Scenario;
import com.avaj.simulator.scenario.ScenarioParser;
import com.avaj.tower.WeatherTower;

import java.io.IOException;
import java.nio.file.Path;

public class Simulator {
    @SuppressWarnings("try")
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: expected path to scenario file");
            return;
        }

        try {
            Scenario scenario = ScenarioParser.parse(Path.of(args[0]));
            try (SimulationLogger logger = SimulationLogger.open(Path.of("simulation.txt"))) {
                run(scenario);
            }

        } catch (IOException | AvajException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void run(Scenario scenario) {
        WeatherTower weatherTower = new WeatherTower();
        AircraftFactory factory = AircraftFactory.getInstance();

        for (AircraftSpec spec : scenario.aircraft()) {
            Flyable flyable = factory.newAircraft(spec.type(), spec.name(), spec.longitude(), spec.latitude(), spec.height());
            flyable.registerTower(weatherTower);
        }

        for (int i = 0; i < scenario.simulationCount(); i++) {
            weatherTower.changeWeather();
        }
    }
}
