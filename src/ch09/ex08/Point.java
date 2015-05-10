/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch09.ex08;

/**
 * 224 ページの9.3.3 節「数値型を比較する」のPoint クラスのcompareTo メソッドを、 Integer.compareTo
 * を使用せずに実装しなさい。
 */
public class Point implements Comparable<Point> {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        if (x < other.x) {
            return -1;
        }

        if (x > other.x) {
            return 1;
        }

        if (y < other.y) {
            return -1;
        }
        if (y > other.y) {
            return 1;
        }

        return 0;
    }

}
