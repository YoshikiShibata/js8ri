/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex09;

import java.util.Arrays;
import java.util.Objects;

/**
 * You can use the parallelPrefix method to parallelize the computation of
 * Fibonacci numbers. We use the fact that the nth Fibonacci number is the top
 * left coefficient of Fn, where F = ( 1 1 1 0 ) . Make an array filled with 2 ×
 * 2 matrices. Define a Matrix class with a multiplication method, use
 * parallelSetAll to make an array of matrices, and use parallelPrefix to
 * multiply them.
 *
 * フィボナッチ（Fibonacci）数の計算を並列化するためにparallelPrefix メソッドを使 用することができます。n
 * 番目のフィボナッチ数は、F = 
 * <pre>
 * 1 1
 * 1 0
 * </pre>
 *
 * とした場合の、Fn を計算した結果の行列の左上の値です。2 × 2 の行列で配列を埋めなさい。乗算のメソッ ドを持つMatrix
 * クラスを定義し、行列の配列を作成するためにparallelSetAll を 使用し、行列の乗算をするためにparallelPrefix を使用しなさい。
 */
public final class Matrix {

    private final static int SIZE = 2;

    private final int[][] matrix = new int[SIZE][SIZE];

    private Matrix(int[][] value) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = value[i][j];
            }
        }
    }

    public static Matrix of(int[][] value) {
        Objects.requireNonNull(value, "value is null");
        if (value.length != SIZE) {
            throw new IllegalArgumentException(
                    String.format("Illegal size(%d,?) of value", value.length));
        }
        for (int[] y : value) {
            if (y.length != SIZE) {
                throw new IllegalArgumentException(
                        String.format("Illeagl size(2,%d) of value", y.length));
            }
        }
        return new Matrix(value);
    }

    public final Matrix multiply(Matrix other) {
        int[][] result = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result[i][j] = matrix[i][0] * other.matrix[j][0]
                        + matrix[i][1] * other.matrix[j][1];
            }
        }
        return new Matrix(result);
    }

    @Override
    public final String toString() {
        return String.format("[%10d %10d%n %10d %10d]%n",
                matrix[0][0], matrix[0][1], matrix[1][0], matrix[1][1]);
    }
    
    public final int get(int x, int y) {
        return matrix[x][y];
    }

    public static void main(String[] args) {
        int[][] fm = new int[][]{{1, 1}, {1, 0}};

        Matrix[] ma = new Matrix[45]; // 46 causes an overflow
        Arrays.parallelSetAll(ma, i -> Matrix.of(fm));
        Arrays.parallelPrefix(ma, (x, y) -> x.multiply(y));
        
        for (int i = 0; i < ma.length; i++) {
            if (i != 0 && (i % 5) == 0)
                System.out.println();
            System.out.printf("%10d ", ma[i].get(0, 0));
        }
        System.out.println();
        System.out.println(ma[ma.length - 1]);
    }

}
