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
 * To deal with lazy evaluation on a per-pixel basis, change the transformers so
 * that they are passed a PixelReader object from which they can read other
 * pixels in the image. For example, (x, y, reader) -> reader.get(width - x, y)
 * is a mirroring operation. The convolution filters from the preceding
 * exercises can be easily implemented in terms of such a reader. The
 * straightforward operations would simply have the form (x, y, reader) ->
 * reader.get(x, y).grayscale(), and you can provide an adapter from
 * UnaryOperation<Color>. A PixelReader is at a particular level in the pipeline
 * of operations. Keep a cache of recently read pixels at each level in the
 * pipeline. If a reader is asked for a pixel, it looks in the cache (or in the
 * original image at level 0); if that fails, it constructs a reader that asks
 * the previous transform.
 *
 * ピクセル単位の遅延評価を扱うために、今までのtransformer を変更して、画像内の 他のピクセルを読み込むことができるPixelReader
 * を渡すようにしなさい。たとえば、 (x, y, reader) -> reader.get(width - x, y) は、鏡像操作です。前の練
 * 習問題からの畳み込みフィルターであれば、リーダーの観点からは容易に実装できます。 素直な操作は、単に(x, y, reader) ->
 * reader.get(x, y).grayscale() の
 * 形式であり、UnaryOperation<Color>からのアダプターを提供することができます。 PixelReader
 * は、操作のパイプライン中の特定のレベルにあります。パイプライン中 の個々のレベルで最近読み込まれたピクセルのキャッシュを保持するようにしてくださ
 * い。ピクセルを求められたら、リーダーはキャッシュ（あるいは、レベル0 なら元画像）
 * を調べます。ピクセルがなければリーダーを構築し、そのリーダーはピクセルを前段階で 求めます。
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
