/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Combine the lazy evaluation of Section 3.6, “Laziness,” on page 56, with the
 * parallel evaluation of Section 3.7, “Parallelizing Operations,” on page 57.
 *
 * 69 ページの3.6 節「遅延」の遅延評価と70 ページの3.7 節「操作の並列化」の並列評価 を組み合わせなさい。
 */
public class ParallelLatentImage {

    private final Image image;
    private final List<UnaryOperator<Color>> pendingOperations;
    private final int width;
    private final int height;

    private ParallelLatentImage(Image image) {
        this.image = image;
        this.pendingOperations = new ArrayList<>();
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
    }

    public static ParallelLatentImage from(Image in) {
        return new ParallelLatentImage(in);
    }

    ParallelLatentImage transform(UnaryOperator<Color> f) {
        pendingOperations.add(f);
        return this;
    }

    public Image toImage() {
        Color[][] in = convertImageToArray();
        Color[][] pixels = new Color[height][width];
        int n = Runtime.getRuntime().availableProcessors();

        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            for (int i = 0; i < n; i++) {
                int fromY = i * height / n;
                int toY = (i + 1) * height / n;
                pool.submit(() -> {
                    System.out.printf("%s %d...%d\n", Thread.currentThread(), fromY, toY - 1);
                    for (int x = 0; x < width; x++) {
                        for (int y = fromY; y < toY; y++) {
                            Color c = in[y][x];
                            for (UnaryOperator<Color> f : pendingOperations) {
                                c = f.apply(c);
                            }
                            pixels[y][x] = c;
                        }
                    }
                });
            }
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return covertArrayToImage(pixels);
    }

    private Image covertArrayToImage(Color[][] pixels) {
        WritableImage result = new WritableImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result.getPixelWriter().setColor(x, y, pixels[y][x]);
            }
        }
        return result;
    }

    private Color[][] convertImageToArray() {
        Color[][] in = new Color[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                in[y][x] = image.getPixelReader().getColor(x, y);
            }
        }
        return in;
    }
}
