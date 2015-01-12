/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex06;

import java.util.Comparator;

/**
 * A Point class for 2D.
 */
public final class Point2D {

    private final double x;
    private final double y;

    /**
     * Comparator for this class
     */
    public static final Comparator<Point2D> COMPARATOR
            = Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY);

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}
