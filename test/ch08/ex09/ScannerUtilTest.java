/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex09;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import js8ri.util.FileUtil;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class ScannerUtilTest {

    @Test
    public void testWords() throws Exception {
        // prepare
        Scanner scanner = new Scanner(new File("alice.txt"));
        scanner.useDelimiter("[\\P{L}]+");

        // action
        List<String> words = ScannerUtil.words(scanner).collect(Collectors.toList());

        // check
        List<String> words2 = new ArrayList<>(FileUtil.readAsWords("alice.txt"));
        words2.remove(0);
        assertEquals(words, words2);
    }

    @Test
    public void testLines() throws Exception {
        // prepare & action
        List<String> lines2 = ScannerUtil.lines(new Scanner(new File("alice.txt"))).
                collect(Collectors.toList());

        // check
        List<String> lines = Files.readAllLines(Paths.get("alice.txt"));
        assertEquals(lines, lines2);
    }

    @Test
    public void testIntegers() {
        // Prepare
        String data = "1 2 3 4 5 6 7 8 9 10 -1 -2 -3 -4 -5 -6 -7 -8 -9 -10";

        // action
        List<Integer> integers = ScannerUtil.integers(new Scanner(data)).
                collect(Collectors.toList());

        // check
        Scanner scanner = new Scanner(data);
        List<Integer> integers2 = new ArrayList<>();
        while (scanner.hasNextInt()) {
            integers2.add(scanner.nextInt());
        }
        assertEquals(integers, integers2);

    }

    @Test
    public void testDoubles() {
        // Prepare
        String data = "1.5 2.25 3.75 4.0 5.5 6.25 7.75 8 9 10 -1 -2 -3 -4 -5 -6 -7 -8 -9 -10";

        // action
        List<Double> doubles = ScannerUtil.doubles(new Scanner(data)).
                collect(Collectors.toList());

        // check
        Scanner scanner = new Scanner(data);
        List<Double> doubles2 = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            doubles2.add(scanner.nextDouble());
        }

        assertEquals(doubles, doubles2);
    }
}
