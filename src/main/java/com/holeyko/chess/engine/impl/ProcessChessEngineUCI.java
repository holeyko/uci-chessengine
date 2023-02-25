package com.holeyko.chess.engine.impl;

import com.holeyko.chess.engine.AbstractChessEngineUCI;
import com.holeyko.chess.engine.exception.ExecuteOperationException;
import com.holeyko.chess.engine.exception.UnknownOperationExceptoin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ProcessChessEngineUCI extends AbstractChessEngineUCI {
    private final Process engine;
    private final BufferedReader engineReader;
    private final BufferedWriter engineWriter;

    private static final String LINE_SEPARATOR = "\n";

    public ProcessChessEngineUCI(String cmd, Predicate<String> failedOperationCheck) {
        super(failedOperationCheck);

        try {
            engine = new ProcessBuilder().command(cmd).start();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to create a engine process [cmd=%s]. %s".formatted(cmd, e.getMessage()));
        }

        engineReader = engine.inputReader();
        engineWriter = engine.outputWriter();
    }

    @Override
    protected List<String> executeOperation(String cmd) {
        List<String> response = new ArrayList<>();
        Optional<RuntimeException> exception = Optional.empty();

        try {
            engineWriter.write(cmd);
            engineWriter.write(LINE_SEPARATOR);
            engineWriter.write(CHECK_OPERATION_END);
            engineWriter.write(LINE_SEPARATOR);
            engineWriter.flush();

            String line;
            while ((line = engineReader.readLine()) != null) {
                if (failedOperationCheck.test(line)) {
                    exception = Optional.of(new UnknownOperationExceptoin("Unknown operation [cmd=%s]".formatted(cmd)));
                }

                response.add(line);
                if (line.startsWith(OPERATION_END)) {
                    break;
                }
            }

            if (exception.isPresent()) {
                throw exception.get();
            }
            return response;
        } catch (IOException e) {
           throw new ExecuteOperationException("Failed to execute a operation [cmd=%s]. %s".formatted(cmd, e.getMessage()));
        }
    }

    @Override
    public void quit() {
        executeOperation("quit");
        try {
            if (engine.isAlive()) {
                engine.destroy();
                engineReader.close();
                engineWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close the engine process. %s".formatted(e.getMessage()));
        }
    }
}
