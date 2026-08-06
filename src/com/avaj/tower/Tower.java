package com.avaj.tower;

import com.avaj.aircraft.Flyable;

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
        }
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionChanged() {
        List<Flyable> snapshot = new ArrayList<>(observers);

        for (Flyable flyable : snapshot) {
            flyable.updateConditions();
        }
    }
}
