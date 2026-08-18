package com.avaj.logger;

import com.avaj.exception.SimulationLoggerException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class SimulationLogger implements AutoCloseable {
    private static SimulationLogger instance;
    private final BufferedWriter writer;

    private SimulationLogger(Path outputPath) throws IOException {
        writer = Files.newBufferedWriter(
                outputPath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        );
    }

    public static SimulationLogger open(Path outputPath) throws IOException {
        if (instance != null) {
            throw new SimulationLoggerException("Simulation logger is already open");
        }

        instance = new SimulationLogger(outputPath);
        return instance;
    }

    public static SimulationLogger getInstance() {
        if (instance == null) {
            throw new SimulationLoggerException("Simulation logger is not open");
        }

        return instance;
    }

    public void log(String msg) {
        try {
            writer.write(msg);
            writer.newLine();
        } catch (IOException e) {
            throw new SimulationLoggerException("Cannot write simulation log", e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            writer.close();
        } finally {
            instance = null;
        }
    }
}
