/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex05;

import java.util.stream.Stream;

/**
 * Using Stream.iterate, make an infinite stream of random numbers—not by
 * calling Math.random but by directly implementing a linear congruential generator.
 * In such a generator, you start with x0 = seed and then produce xn + 1 =
 * (a xn + c) % m, for appropriate values of a, c, and m. You should implement a
 * method with parameters a, c, m, and seed that yields a Stream<Long>. Try out a =
 * 25214903917, c = 11, and m = 248.
 * 
 * Stream.iterate を使用して乱数の無限ストリームを生成しなさい。このとき、
 * Math.random を呼び出すのではなく、線形合同生成機（linear congruential generator）
 * を直接実装すること。そのような生成器では、x0 = seed で始めて、a、c、m の適切
 * な値に対して、xn+1 = (axn +c) % m を生成します。パラメータa、c、m、seed を受け
 * 取り、Stream<Long>を生成するメソッドを実装しなさい。a = 25214903917、c = 11、
 * m = 248 を試してみなさい。
 */
public class InfiniteStreamOfRandomNumbers {
    public static Stream<Long> random(long a, long c, long m, long seed) {
        LinearCongruntialGenerator lcg = new LinearCongruntialGenerator(a, c, m, seed);
        
        Stream<Long> randomStream = Stream.generate(() -> lcg.next());
        return randomStream;
    }
    
    private static class LinearCongruntialGenerator {
        final private long a;
        final private long c;
        final private long m;
        
        private long x;
        
        LinearCongruntialGenerator(long a, long c, long m, long seed) {
            this.a = a;
            this.c = c;
            this.m = m;
            x = seed;
        }
        
        long next() {
            x = (a * x + c ) % m;
            return x;
        }
    }
}
