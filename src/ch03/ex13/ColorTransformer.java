/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex13;

import javafx.scene.paint.Color;

/**
 * Transform a color into another color.
 */
@FunctionalInterface
public interface ColorTransformer {
    /**
     * transform a color into another color
     * @param x x location
     * @param y y location
     * @param image original Image
     * @return transformed color
     */
    Color apply(int x, int y, LatentImage image);
}
