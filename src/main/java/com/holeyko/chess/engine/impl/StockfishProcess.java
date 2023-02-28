package com.holeyko.chess.engine.impl;

import com.holeyko.chess.engine.ChessEngineSkillLevelChanger;
import com.holeyko.util.FileUtils;
import com.holeyko.config.PathConfig;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class StockfishProcess extends ProcessChessEngineUCI implements ChessEngineSkillLevelChanger {
    private static final String STOCKFISH = "stockfish";
    private static final int MIN_SKILL_LEVEL = 0;
    private static final int MAX_SKILL_LEVEL = 20;
    private static Path executableFile;

    static {
        try {
            executableFile = FileUtils.findExecutableFile(
                    Path.of(PathConfig.PATH_TO_ENGINE_DIR),
                    STOCKFISH
            );
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create the stockfish engine. %s".formatted(e.getMessage()));
        }
    }

    public StockfishProcess() {
            super(
                    executableFile.toString(),
                    (line) -> line.startsWith("Unknown command") || line.startsWith("Unexpected token")
            );
            executeOperation("");
    }

    @Override
    public List<String> changeSkillLevel(int level) {
        if (MIN_SKILL_LEVEL > level || level > MAX_SKILL_LEVEL) {
            throw new IllegalArgumentException(
                    "Level must be between %d and %d [level=%d]".formatted(MIN_SKILL_LEVEL, MAX_SKILL_LEVEL, level));
        }

        return setOption("Skill Level", Integer.toString(level));
    }
}
