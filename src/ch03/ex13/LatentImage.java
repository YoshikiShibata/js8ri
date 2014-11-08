/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex13;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author yoshiki
 */
public class LatentImage {

    private final Image image;
    final int width;
    final int height;

    public LatentImage(Image image) {
        if (image == null) {
            throw new NullPointerException("image is null");
        }

        this.image = image;
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
    }
    
    LatentImage(int width, int height) {
        this.image = null;
        this.width = width;
        this.height = height;
    }

    public static LatentImage from(Image image) {
        return new LatentImage(image);
    }

    public Color getColor(int x, int y) {
        if (x < 0 || width <= x) {
            throw new IllegalArgumentException("x=" + x + " is illegal");
        }
        if (y < 0 || height <= x) {
            throw new IllegalArgumentException("x=" + x + " is illegal");
        }

        return image.getPixelReader().getColor(x, y);
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }
    
    public final LatentImage transform(ColorTransformer transformer) {
        return new IntermediateLatentImage(this, transformer);
    }


    public final Image toImage() {
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color c = getColor(x, y);
                out.getPixelWriter().setColor(x, y, c);
            }
        }
        return out;
    }
}
