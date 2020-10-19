/*
 * Copyright (C) 2014, 2020 Yoshiki Shibata. All rights reserved.
 */
package js8ri.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Contains utility methods for all exercises.
 */
public class FileUtil {

    private FileUtil() {
        // Non-instantiable
    }

    /**
     * Read the contents of the specified file and split it into words.
     *
     * @param filepath a file path
     * @return List of words
     * @throws NullPointerException if filepath is null.
     * @throws IllegalArgumentException if the filepath cannot be read.
     */
    public static List<String> readAsWords(String filepath) {
		Objects.requireNonNull(filepath, "filepath is null");

        String contents = null;
        try {
            contents = new String(java.nio.file.Files.readAllBytes(
                    Paths.get(filepath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot Open " + filepath);
        }

        return Arrays.asList(contents.split("[\\P{L}]+"));
    }

}
