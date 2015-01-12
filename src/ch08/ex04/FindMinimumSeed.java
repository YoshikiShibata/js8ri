/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex04;

import java.util.Random;
import java.util.stream.LongStream;

/**
 *
 * The Math.nextDown(x) method returns the next smaller floating-point number
 * than x, just in case some random process hit x exactly, and we promised a
 * number < x. Can this really happen? Consider double r = 1 - generator.
 * nextDouble(), where generator is an instance of java.util.Random. Can it ever
 * yield 1? That is, can generator.nextDouble() ever yield 0? The documentation
 * says it can yield any value between 0 inclusive and 1 exclusive. But, given
 * that there are 253 such floating-point numbers, will you ever get a zero?
 * Indeed, you do. The random number generator computes the next seed as next(s)
 * = s · m + a % N, where m = 25214903917, a = 11, and N = 248. The inverse of m
 * modulo N is v = 246154705703781, and therefore you can compute the
 * predecessor of a seed as prev(s) = (s – a) · v % N. To make a double, two
 * random numbers are generated, and the top 26 and 27 bits are taken each time.
 * When s is 0, next(s) is 11, so that’s what we want to hit: two consecutive
 * numbers whose top bits are zero. Now, working backwards, let’s start with s =
 * prev(prev(prev(0))). Since the Random constructor sets s = (initialSeed ^ m)
 * % N, offer it s = prev(prev(prev(0))) ^ m = 164311266871034, and you’ll get a
 * zero after two calls to nextDouble. But that is still too obvious. Generate a
 * million predecessors, using a stream of course, and pick the minimum seed.
 * Hint: You will get a zero after 376050 calls to nextDouble.
 *
 * Math.nextDown(x) メソッドは、何らかのランダムな処理がx に正確に一致した場 合に、x
 * よりも次に小さな浮動小数点数を返します。これにより、返された数がx よ り小さいことが保証されます。これは、本当に保証できるのでしょうか。double r
 * = 1 - generator.nextDouble() を考えてみてください。このgenerator は、 java.util.Random
 * のインスタンスです。r が、1 になることはあるでしょうか。す なわち、generator.nextDouble() が0
 * を生成できるでしょうか。ドキュメントに よれば、それは0 を含み、1 未満の値を生成できるとなっています。しかし、253 個の浮動
 * 小数点数が存在することを考慮すると、0 を得ることはあるのでしょうか。実際、0 を得 ます。乱数生成器は、次のシードをnext(s) = s ・m+ a
 * % N として計算します。ここで、 m = 25214903917、a = 11、N = 248 です。m モジュロN の逆はv =
 * 246154705703781 です。そして、結果として、シードの前の値をprev(s) = (s - a) ・v % N として計算する
 * ことができます。double を生成するために2 つの乱数が生成されて、毎回、トップの 26 ビットと27 ビットが使用されます。s が0
 * の場合、next(s) は11 であり、それが私達 が得たいものです。すなわち、2 つの連続する数字の上位ビットが0 です。ここで、逆に やりなおして、s
 * = prev(prev(prev(0))) から始めましょう。Random のコンストラクタ はs = (initialSeed ˆm)
 * を設定するので、s = prev(prev(prev(0))) ˆ m = 164311266871034 を提供すると、nextDouble の2
 * 回の呼び出し後に0 となります。しかし、それはあま りにも明白です。100 万個の前の値を、もちろん、ストリームを使用して生成し、最小の
 * シードを見つけなさい。ヒント：nextDouble を376050 回呼び出した後に0 を得ます。
 */
public class FindMinimumSeed {

    private static final long M = 25_214_903_917L;
    private static final long N = (1L << 48);
    private static final long V = 246_154_705_703_781L;
    private static final long A = 11L;

    static long prev(long s) {
        return (s - A) * V % N;
    }

    public static void main(String[] args) {
        long minimumSeed = LongStream.
                iterate(prev(0), p -> prev(prev(p))).
                map(n -> n ^ M).  // produce seeds
                limit(1000_1000). // limit to one million
                reduce((x, y) -> Math.abs(x) < Math.abs(y) ? x : y). // find the minimum seed
                getAsLong();
        
        System.out.println("Minimum Seed:" + minimumSeed);

        Random random = new Random(minimumSeed);
        for (long i = 1;; i++) {
            double next = random.nextDouble();
            if (next == 0.0) {
                System.out.println("i = " + i);
                break;
            }
        }
    }

}
