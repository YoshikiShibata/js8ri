/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Using Files.lines and Pattern.asPredicate, write a program that acts like the
 * grep utility, printing all lines that contain a match for a regular
 * expression.
 *
 * Files.lines とPattern.asPredicate を使用して、正規表現に一致するすべての 行を表示し、grep
 * ユーティリティのように動作するプログラムを書きなさい
 */
public class Grep {

    public static void main(String[] args) {
        if (args.length <= 1) {
            showUsage();
            System.exit(1);
        }

        Pattern pattern = Pattern.compile(args[0]);
        for (int i = 1; i < args.length; i++) {
            executeMatching(args[i], pattern);
        }

    }

    private static void showUsage() {
        System.out.println("Usage: Grep pattern files ...");
    }

    private static void executeMatching(String file, Pattern pattern) {
        try {
            Stream<String> lines = Files.lines(Paths.get(file));
            lines.filter(pattern.asPredicate()).forEach(
                    line -> {
                        System.out.printf("%s: %s%n", file, line);
                    });
        } catch (IOException ex) {
            Logger.getLogger(Grep.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
