/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex03;

/**
 * Euclid’s algorithm (which is over two thousand years old) computes the
 * greatest common divisor of two numbers as gcd(a, b) = a if b is zero, and
 * gcd(b, rem(a, b)) otherwise, where rem is the remainder. Clearly, the gcd
 * should not be negative, even if a or b are (since its negation would then be
 * a greater divisor). Implement the algorithm with %, floorMod, and a rem
 * function that produces the mathematical (non-negative) remainder. Which of
 * the three gives you the least hassle with negative values?
 *
 * ユークリッドのアルゴリズム（200 年以上前のものです）は、2 つの数値の最大公約数 （greatest common divisor）を計算します。b
 * が0 であれば、gcd(a, b) = a であり、そうでな ければ、gcd(b, rem(a, b)) です。rem は、余りを意味しています。a かb
 * が負であったと しても、明らかにgcd は、負になるべきではありません（なぜなら、その値の符号を正にし たものの方がより大きな約数となるからです）。gcd
 * のアルゴリズムを%、floorMode、 数学的な（負ではない）余りを生成するrem 関数で実装しなさい。これらの3 つの方法
 * のどれが負の値に対して最も簡単ですか。
 */
public class GCD {
    
    static int gcd1(int a, int b) {
        if (b == 0)
            return positive(a);
        return gcd1(b, a % b);
    }
    
    static int gcd2(int a, int b) {
        if (b == 0)
            return positive(a);
        return gcd2(b, Math.floorMod(a, b));
    }
    
    static int gcd3(int a, int b) {
        if (b == 0)
            return positive(a);
        return gcd3(b, rem(a, b));
    }
    
    static int positive(int a) {
        if (a < 0)
            a = -a;
        return a;
    }
    
    static int rem(int a, int b) {
        int remainder = a % b;
        if (remainder < 0)
            remainder = -remainder;
        return remainder;
    }
    
    public static void main(String[] args) {
        System.out.println(gcd3(314159, 271828));
        System.out.println(gcd3(-123, 3));
    }

}
