package com.avaj.simulator.scenario;

import com.avaj.exception.ScenarioException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class ScenarioParser {
    private ScenarioParser() {}

    public static Scenario parse(Path path) throws IOException {
        List<String> lines = Files.readAllLines(
                path,
                StandardCharsets.UTF_8
        );

        if (lines.isEmpty()) {
            throw new ScenarioException("Scenario file is empty");
        }

        int simulationCount;

        try {
            simulationCount = Integer.parseInt(
                    lines.getFirst().trim()
            );
        } catch (NumberFormatException exception) {
            throw new ScenarioException(
                    "Line 1: simulation count must be an integer",
                    exception
            );
        }

        if (simulationCount <= 0) {
            throw new ScenarioException("Line 1: simulation count must be positive");
        }

        List<AircraftSpec> aircraft = new ArrayList<>();

        for (int index = 1; index < lines.size(); index++) {
            int lineNumber = index + 1;
            String line = lines.get(index).trim();

            if (line.isEmpty()) {
                throw new ScenarioException("Line " + lineNumber + ": line is empty");
            }

            String[] parts = line.split("\\s+");

            if (parts.length != 5) {
                throw new ScenarioException("Line " + lineNumber + ": expected 5 values, found " + parts.length);
            }

            String type = parts[0];
            String name = parts[1];

            boolean supportedType = switch (type) {
                case "Balloon", "Helicopter", "JetPlane" -> true;
                default -> false;
            };

            if (!supportedType) {
                throw new ScenarioException("Line " + lineNumber + ": unknown aircraft type: " + type);
            }

            int longitude;
            int latitude;
            int height;

            try {
                longitude = Integer.parseInt(parts[2]);
                latitude = Integer.parseInt(parts[3]);
                height = Integer.parseInt(parts[4]);
            } catch (NumberFormatException exception) {
                throw new ScenarioException(
                        "Line " + lineNumber + ": coordinates must be integers",
                        exception
                );
            }

            if (longitude <= 0) {
                throw new ScenarioException("Line " + lineNumber + ": longitude must be positive");
            }

            if (latitude <= 0) {
                throw new ScenarioException("Line " + lineNumber + ": latitude must be positive");
            }

            if (height <= 0 || height > 100) {
                throw new ScenarioException("Line " + lineNumber + ": height must be between 1 and 100");
            }

            AircraftSpec spec = new AircraftSpec(type, name, longitude, latitude, height);
            aircraft.add(spec);
        }

        return new Scenario(simulationCount, aircraft);
    }
}
