/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex14;

import javafx.scene.image.PixelReader;
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
     * @param reader a PixelReader
     * @return transformed color
     */
    Color apply(int x, int y, PixelReader reader);
}
