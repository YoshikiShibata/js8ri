/*
 * Copyright (C) 2014, 2017 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex02;

import java.io.File;

/**
 * Using the listFiles(FileFilter) and isDirectory methods of the java.io.File class, write a method
 * that returns all subdirectories of a given directory. Use a lambda expression instead of a
 * FileFilter object. Repeat with a method expression.
 *
 * <p>java.io.File クラスのlistFiles(FileFilter) メソッドとisDirectory メ
 * ソッドを使用して、指定されたディレクトの下のすべてのサブディレクトリを返すメソッ ドを書きなさい。FileFilter オブジェクトではなく、ラムダ式を使用しなさい。同じ
 * ことを、メソッド参照を用いて行いなさい。
 */
public class ListAllSubdirectories {

  public static File[] listAllSubDirectories(File directory) {
    return directory.listFiles(
        file -> {
          return file.isDirectory();
        });
  }

  public static File[] listAllSubDirectoriesWithMethodReference(File directory) {
    return directory.listFiles(File::isDirectory);
  }
}
