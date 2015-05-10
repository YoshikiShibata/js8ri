/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch09.ex02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 練習問題1 を改善して、in.close() やout.close() によりスローされる例外を抑
 * 制された例外として、元々の例外へ追加するようにしなさい。
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
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in;
        try {
            in = new Scanner(Paths.get("alice.txt"));
        } catch (IOException ex) {
            Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        PrintWriter out;
        try {
            out = new PrintWriter("/tmp/out.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TryWithNoResource.class.getName()).log(Level.SEVERE, null, ex);
            try {
                in.close();
            } catch (IllegalStateException e) {
                ex.addSuppressed(e);
                throw ex;
            }
            throw ex;
        }

        try {
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        } catch (IllegalStateException | NoSuchElementException ex) {
            try {
                in.close();
            } catch (IllegalStateException e) {
                try {
                    out.close();
                } catch (Throwable e2) {
                    e.addSuppressed(e2);
                    ex.addSuppressed(e);
                }
                ex.addSuppressed(e);
                throw ex;
            }

        }

        try {
            in.close();
        } catch (IllegalStateException ex) {
            try {
                out.close();
            } catch (Throwable e) {
                ex.addSuppressed(e);
                throw ex;
            }

        }

        out.close();
    }

}
