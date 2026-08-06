package com.avaj.simulator;

import com.avaj.simulator.scenario.Scenario;
import com.avaj.simulator.scenario.ScenarioParser;

import java.io.IOException;
import java.nio.file.Path;

public class Simulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: expected path to scenario file");
            return;
        }

        try {
            Scenario scenario = ScenarioParser.parse(Path.of(args[0]));
            System.out.println(scenario);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() +  " type: " + e.getClass());
        }
    }
}
