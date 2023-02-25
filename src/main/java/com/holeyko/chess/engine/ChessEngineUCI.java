package com.holeyko.chess.engine;

import com.holeyko.chess.model.GoOperationParameters;
import com.holeyko.chess.model.Move;

import java.util.List;

public interface ChessEngineUCI {
    List<String> uci();

    List<String> debug(boolean flag);

    List<String> setOption(String name);
    List<String> setOption(String name, String value);

    List<String> newGame();

    List<String> position(String fen);
    List<String> position(Move... moves);
    List<String> position(String fen, Move... moves);

    List<String> go(GoOperationParameters parameters);

    List<String> stop();

    void quit();
}
