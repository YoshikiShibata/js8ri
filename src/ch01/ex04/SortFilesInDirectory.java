/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex04;

import java.io.File;
import java.util.Arrays;

/**
 * Given an array of File objects, sort it so that the directories come before
 * the files, and within each group, elements are sorted by path name. Use a
 * lambda expression, not a Comparator.
 *
 * Fileオブジェクトの配列が与えられたとします。その配列をソートして、ファイルの前
 * にディレクトリが来るようにし、ファイルとディレクトリのそれぞれのグループではパス
 * 名でソートされるようにしなさい。Comparatorではなく、ラムダ式を使用しなさい。
 */
public class SortFilesInDirectory {

    public static void sortFilesByDirectoryThenFilename(File[] files) {
        Arrays.sort(files, (file1, file2) -> {
            if (file1.isDirectory() && !file2.isDirectory()) {
                return -1;
            }
            if (!file1.isDirectory() && file2.isDirectory()) {
                return 1;
            }

            return file1.getName().compareTo(file2.getName());
        });
    }
}
