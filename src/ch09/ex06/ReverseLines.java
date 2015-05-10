/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

/**
 * ファイルのすべての行を読み込み、逆順に書き出すプログラムを作成しなさい。 Files.readAllLines とFiles.write を使用しなさい。
 */
public class ReverseLines {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("alice.txt"));
        Collections.reverse(lines);
        Files.write(Paths.get("/tmp/alice.txt"), lines, StandardOpenOption.CREATE);
    }
}
