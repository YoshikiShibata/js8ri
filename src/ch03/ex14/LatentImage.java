/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

/**
 *
 * @author yoshiki
 */
public class LatentImage implements PixelReader {

    private final PixelReader reader;
    private final int width;
    private final int height;
    private final ColorTransformer transformer;
    private final Color[][] caches;

    private LatentImage(Image image) {
        this.reader = image.getPixelReader();
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        this.transformer = (x, y, reader) -> reader.getColor(x, y);
        this.caches = new Color[width][height];
    }

    private LatentImage(PixelReader reader, int width, int height,
            ColorTransformer transformer) {
        this.reader = reader;
        this.width = width;
        this.height = height;
        this.transformer = transformer;
        this.caches = new Color[width][height];

    }

    public static LatentImage from(Image image) {
        return new LatentImage(image);
    }

    public final LatentImage transform(ColorTransformer transformer) {
        return new LatentImage(this, width, height, transformer);
    }

    public final LatentImage transform(UnaryOperator<Color> op) {
        return new LatentImage(this, width, height,
                (x, y, reader) -> op.apply(reader.getColor(x, y)));
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

    @Override
    public PixelFormat getPixelFormat() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getArgb(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Color getColor(int x, int y) {
        if (x < 0 || width <= x) {
            throw new IllegalArgumentException("x=" + x + " is illegal");
        }
        if (y < 0 || height <= x) {
            throw new IllegalArgumentException("x=" + x + " is illegal");
        }

        if (caches[x][y] == null) {
            caches[x][y] = transformer.apply(x, y, reader);
        }
        return caches[x][y];
    }

    @Override
    public <T extends Buffer> void getPixels(
            int x, int y, int w, int h,
            WritablePixelFormat<T> pixelformat,
            T buffer,
            int scanlineStride) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void getPixels(int x, int y, int w, int h,
            WritablePixelFormat<ByteBuffer> pixelformat,
            byte[] buffer, int offset,
            int scanlineStride) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void getPixels(int x, int y, int w, int h,
            WritablePixelFormat<IntBuffer> pixelformat,
            int[] buffer, int offset, int scanlineStride) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
