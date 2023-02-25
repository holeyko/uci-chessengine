package com.holeyko.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;

public class FileUtils {
    public static Path findExecutableFile(Path start, String fileName) throws IOException {
        BiPredicate<Path, BasicFileAttributes> match = (path, attributes) ->
            path.getFileName().toString().startsWith(fileName) && Files.isExecutable(path);

        return Files.find(start, Integer.MAX_VALUE, match)
                .findFirst()
                .orElseThrow(FileNotFoundException::new);
    }
}
