/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex11;

import java.util.function.UnaryOperator;
import javafx.scene.paint.Color;

/**
 * Implement static methods that can compose two ColorTransformer objects, and a
 * static method that turns a UnaryOperator<Color> into a ColorTransformer that
 * ignores the x- and y-coordinates. Then use these methods to add a gray frame
 * to a brightened image. (See Exercise 5 for the gray frame.)
 *
 * 2 つのColorTransformer オブジェクトを合成できるstatic メソッドを実装しなさい。そして、x 座標とy
 * 座標を無視するColorTransformer へ UnaryOperator<Color>を変えるstatic メソッドを実装しなさい。それから、
 * 変換によって明るくなった画像に灰色の枠を追加するために、実装したメソッドを使用し なさい（灰色の枠に関しては練習問題5 を参照しなさい）。
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
     * Compose two ColorTransformer into one ColorTransformer.
     *
     * @param first the first ColorTransformer to wchich a Color will be applied
     * @param second the second ColorTranformer to which the result of first
     * will be applied.
     * @return a composed ColorTransformer
     */
    static ColorTransformer compose(ColorTransformer first, ColorTransformer second) {
        return (x, y, color) -> second.apply(x, y, first.apply(x, y, color));
    }
    
    /**
     * Convert an ColorTransformer into an UnaryOperator, ignoring x and y coordinates.
     * @param ct an ColorTransformer to be converted.
     * @return UnaryOperator
     */
    static UnaryOperator<Color> toUnary(ColorTransformer ct) {
        return c -> ct.apply(0, 0, c);
    }
}
