/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Enhance the LatentImage class in Section 3.6, “Laziness,” on page 56, so that
 * it supports both UnaryOperator<Color> and ColorTransformer. Hint: Adapt the
 * former to the latter.
 *
 * 69 ページの3.6 節「遅延」のLatentImage を機能拡張して、UnaryOperator<Color>と ColorTransformer
 * の両方をサポートするようにしなさい。 ヒント：UnaryOperator<Color>をColorTransformer へ適応させなさい。
 */
class LatentImage {

    private Image in;
    private List<UnaryOperator<Color>> pendingOperations;

    public static LatentImage from(Image in) {
        LatentImage result = new LatentImage();
        result.in = in;
        result.pendingOperations = new ArrayList<>();
        return result;
    }

    LatentImage transform(UnaryOperator<Color> f) {
        pendingOperations.add(f);
        return this;
    }
    
    LatentImage transform(ColorTransformer ct) {
        pendingOperations.add(c -> ct.apply(0, 0, c));
        return this;
    }

    public Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color c = in.getPixelReader().getColor(x, y);
                for (UnaryOperator<Color> f : pendingOperations) {
                    c = f.apply(c);
                }
                out.getPixelWriter().setColor(x, y, c);
            }
        }
        return out;
    }
}
