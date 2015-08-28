/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex13;

import javafx.scene.paint.Color;

/**
 *
 */
class IntermediateLatentImage extends LatentImage {

    final LatentImage image;
    final ColorTransformer transformer;

    IntermediateLatentImage(LatentImage image, ColorTransformer transformer) {
        super(image.getWidth(), image.getHeight());
        this.image = image;
        this.transformer = transformer;
    }

    @Override
    public Color getColor(int x, int y) {
        if (x < 0 || width <= x) {
            throw new IllegalArgumentException("x=" + x + " is illegal");
        }
        if (y < 0 || height <= y) {
            throw new IllegalArgumentException("x=" + x + " is illegal");
        }

        return transformer.apply(x, y, image);
    }
}
