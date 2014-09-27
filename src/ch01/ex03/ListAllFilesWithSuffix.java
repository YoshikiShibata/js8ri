/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex03;

import java.io.File;

/**
 * Using the list(FilenameFilter) method of the java.io.File class, write a
 * method that returns all files in a given directory with a given extension.
 * Use a lambda expression, not a FilenameFilter. Which variables from the
 * enclosing scope does it capture?
 *
 * java.io.File クラスのlist(FilenameFilter) を使用して、指定されたディレ
 * クトリの下の指定された拡張子を持つ、すべてのファイルを返すメソッドを書きなさい。 FilenameFilter
 * ではなく、ラムダ式を使用しなさい。エンクロージングスコープからキャプチャされる変数はどれですか。
 */
public class ListAllFilesWithSuffix {

    public static String[] listAllFilesWithSuffix(File directory, String suffix) {
        return directory.list((dir, file) -> {
            return file.endsWith("." + suffix);
        });
    }
}
