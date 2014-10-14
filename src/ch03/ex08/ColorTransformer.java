/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex08;

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Transform a color into another color.
 *
 * Generalize Exercise 5 by writing a static method that yields a
 * ColorTransformer that adds a frame of arbitrary thickness and color to an
 * image.
 *
 * 画像に任意の幅と色の枠を追加するColorTransformer を生成するように、static メソッドを書いて、練習問題5 を汎用化しなさい。
 */
@FunctionalInterface
public interface ColorTransformer {

    /**
     * transform a color into another color
     *
     * @param x x location
     * @param y y location
     * @param colorAtXY original color
     * @return transformed color
     */
    Color apply(int x, int y, Color colorAtXY);

    /**
     * Constructs a ColorTransformer which puts a frame with the specified
     * thickness and color, replace the pixels on the borader of an image.
     *
     * @param thickness the thickness of the frame
     * @param color the color of the frame
     * @param widthOfImage the width of the image to which this transformer will
     * be applied.
     * @param heightOfImage the height of the image to which this transformer
     * will be applied.
     * @return a ColorTransformer instance
     * @throws IllegalArgumentException if either thickness, widthOfImage, or
     * heightOfImage is equal to or less than zero.
     * @throws IllegalArgumentException if thickness is too big
     * @throws NullPointerException if color is null.
     */
    static ColorTransformer frameTransformer(int thickness, Color color,
            double widthOfImage, double heightOfImage) {
        Objects.requireNonNull(color, "color is null");

        if (thickness <= 0) {
            throw new IllegalArgumentException("thickness is " + thickness);
        }
        if (widthOfImage <= 0.0) {
            throw new IllegalArgumentException("widthOfImage is " + widthOfImage);
        }
        if (heightOfImage <= 0.0) {
            throw new IllegalArgumentException("heightOfImage is " + heightOfImage);
        }

        if ((widthOfImage - thickness * 2) <= 0.0
                || (heightOfImage - thickness * 2) <= 0.0) {
            throw new IllegalArgumentException("thickness is too tick");
        }

        return (x, y, c)
                -> x < thickness
                || x > widthOfImage - thickness
                || y < thickness
                || y > heightOfImage - thickness ? color : c;
    }

}
