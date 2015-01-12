/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex02;

/**
 * For which integer n does Math.negateExact(n) throw an exception? (Hint: There
 * is only one.)
 *
 * Math.negateExact(n) が例外をスローすることになる整数n の値は何ですか（ヒン ト：1 つの値しか該当しません）。
 */
public class NegateExact {

    public static void main(String[] args) throws Exception {
        int x = Math.negateExact(Integer.MIN_VALUE);
    }
 }
