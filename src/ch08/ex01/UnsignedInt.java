/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex01;

/**
 * Write a program that adds, subtracts, divides, and compares numbers between 0
 * and 232 – 1, using int values and unsigned operations. Show why
 * divideUnsigned and remainderUnsigned are necessary.
 *
 * int 値と符号なし操作を使用して、0 から232 − 1 までの間の数値の加算、減算、除算、
 * 比較を行うプログラムを書きなさい。divideUnsigned とremainderUnsigned が 必要な理由を示しなさい。
 *
 * Answer: For addition and Subtraction, there is no need for unsigned
 * operations. But for divide and remainder, special operations are needed.
 *
 * Note that in two's complement arithmetic, the three other basic arithmetic
 * operations of add, subtract, and multiply are bit-wise identical if the two
 * operands are regarded as both being signed or both being unsigned.
 */
public class UnsignedInt {

    private UnsignedInt() {
    }

    public static int add(int x, int y) {
        return (int) (Integer.toUnsignedLong(x) + Integer.toUnsignedLong(y));
    }

    public static int subtract(int x, int y) {
        return (int) (Integer.toUnsignedLong(x) - Integer.toUnsignedLong(y));
    }

    public static int divide(int x, int y) {
        return (int) (Integer.toUnsignedLong(x) / Integer.toUnsignedLong(y));
    }

    public static int compare(int x, int y) {
        long unsignedX = Integer.toUnsignedLong(x);
        long unsignedY = Integer.toUnsignedLong(y);

        if (unsignedX < unsignedY) {
            return -1;
        }

        return unsignedX == unsignedY ? 0 : 1;
    }
}
