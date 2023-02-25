package com.holeyko.chess.engine;

import com.holeyko.chess.model.GoOperationParameters;
import com.holeyko.chess.model.Move;

import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractChessEngineUCI implements ChessEngineUCI {
    protected final Predicate<String> failedOperationCheck;

    protected static final String CHECK_OPERATION_END = "isready";
    protected static final String OPERATION_END = "readyok";

    public AbstractChessEngineUCI(Predicate<String> failedOperationCheck) {
        this.failedOperationCheck = failedOperationCheck;
    }

    protected abstract List<String> executeOperation(String cmd);

    public List<String> uci() {
        return executeOperation("uci");
    }

    public List<String> debug(boolean flag) {
        return executeOperation("debug %s".formatted(flag ? "on" : "off"));
    }

    public List<String> setOption(String name) {
        return executeOperation("setoption name %s".formatted(name));
    }

    public List<String> setOption(String name, String value) {
        return executeOperation("setoption name %s value %s".formatted(name, value));
    }

    public List<String> newGame() {
        return executeOperation("ucinewgame");
    }

    public List<String> position(String fen) {
        return executeOperation("position fen %s".formatted(fen));
    }

    public List<String> position(Move... moves) {
        return executeOperation("position moves %s".formatted(movesToString(moves)));
    }

    public List<String> position(String fen, Move... moves) {
        return executeOperation("position fen %s moves %s".formatted(fen, movesToString(moves)));
    }

    private String movesToString(Move... moves) {
        StringBuilder result = new StringBuilder(4 * moves.length);
        for (Move move : moves) {
            result.append(move);
        }

        return result.toString();
    }

    public List<String> go(GoOperationParameters parameters) {
        return executeOperation("go " + parameters);
    }

    public List<String> stop() {
        return executeOperation("stop");
    }

    public abstract void quit();
}
