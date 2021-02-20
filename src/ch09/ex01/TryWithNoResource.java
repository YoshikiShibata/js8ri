/*
 * Copyright (C) 2015, 2021 Yoshiki Shibata. All rights reserved.
 */
package ch09.ex01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 212 ページの9.1.1 節「try-with-resources 文」の最後にあるScanner とPrinter を生 成しているコードを、try-with-resources
 * 文を使用しないで実装しなさい。両方のオブジェ クトが適切に生成された場合には、両方のオブジェクトをきちんとクローズしなさい。次 の事柄を考慮すること。
 *
 * <p>Scanner のコンストラクタは、例外をスローする。
 *
 * <p>PrintWriter のコンストラクタは、例外をスローする。
 *
 * <p>hasNext、next、println のメソッドは、例外をスローする。
 *
 * <p>in.close() は、例外をスローする。
 *
 * <p>out.close() は、例外をスローする。
 */
public class TryWithNoResource {

  /*
  public static void main(String[] args) {
      try (Scanner in
              = new Scanner(Paths.get("alice.txt"));
              PrintWriter out = new PrintWriter("/tmp/out.txt")) {
          while (in.hasNext()) {
              out.println(in.next().toLowerCase());
          }
      } catch (IOException ex) {
          Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, ex);
      }
  }*/

  public static void main(String[] args) {
    Scanner in;
    try {
      in = new Scanner(Paths.get("alice.txt"));
    } catch (IOException ex) {
      Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, ex);
      return;
    }

    PrintWriter out;
    try {
      out = new PrintWriter("/tmp/out.txt");
    } catch (FileNotFoundException ex) {
      Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, ex);
      in.close();
      return;
    }

    RuntimeException runtimeEx = null;
    try {
      while (in.hasNext()) {
        out.println(in.next().toLowerCase());
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      runtimeEx = e;
    }

    in.close();
    out.close();

    if (runtimeEx != null) {
      throw runtimeEx;
    }
  }
}
