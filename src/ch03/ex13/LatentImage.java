/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex13;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Convolution filters such as blur or edge detection compute a pixel from
 * neighboring pixels. To blur an image, replace each color value by the average
 * of itself and its eight neighbors. For edge detection, replace each color
 * value c with 4c – n – e – s – w, where the other colors are those of the
 * pixel to the north, east, south, and west. Note that these cannot be
 * implemented lazily, using the approach of Section 3.6, “Laziness,” on page
 * 56, since they require the image from the previous stage (or at least the
 * neighboring pixels) to have been computed. Enhance the lazy image processing
 * to deal with these operations. Force computation of the previous stage when
 * one of these operators is evaluated.
 *
 * ぼやけ検出、あるいは、エッジ検出といった畳み込みフィルターは、隣接するピクセル から1
 * つのピクセルを計算します。画像をぼやかすためには、ピクセルとその隣接する 8 個のピクセルの平均で、個々の色値を置き換えます。エッジ検出には、個々の色値c
 * を 4c − n − e − s − w で置き換えます。ここで、他の色は、北（north）、東（east）、南
 * （south）、西（west）のピクセルの色値です。これは、69 ページの3.6 節「遅延」で説明
 * された方法を用いた遅延では実装できないことに注意してください。なぜなら、計算する
 * ために、前段の画像（あるいは、少なくとも隣接するピクセル）が必要だからです。これ らの操作を扱うために遅延画像処理の機能を強化しなさい。これらの演算の1
 * つが評価さ れる際に、前段の計算が強制されるようにしなさい。
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
