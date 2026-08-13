package com.avaj.tower;

import com.avaj.aircraft.Flyable;
import com.avaj.logger.SimulationLogger;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (flyable == null) {
            throw new IllegalArgumentException("Flyable cannot be null");
        }

        if (!observers.contains(flyable)) {
            observers.add(flyable);
            SimulationLogger.getInstance().log(
                    "Tower says: " + flyable + " registered to weather tower."
            );
        }
    }

    public void unregister(Flyable flyable) {
       if (observers.remove(flyable)) {
           SimulationLogger.getInstance().log(
                   "Tower says: " + flyable + " unregistered from weather tower."
           );
       }
    }

    protected void conditionChanged() {
        List<Flyable> snapshot = new ArrayList<>(observers);

        for (Flyable flyable : snapshot) {
            flyable.updateConditions();
        }
    }
}
