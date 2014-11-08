/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex10;

import java.util.function.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;

/**
 * Why can’t one call
 * <blockquote><pre>
 *    UnaryOperator op = Color::brighter;
 *    Image finalImage = transform(image, op.compose(Color::grayscale));
 * </pre></blockquote>
 * Look carefully at the return type of the compose method of UnaryOperator<T>.
 * Why is it not appropriate for the transform method? What does that say about
 * the utility of structural and nominal types when it comes to function
 * composition?
 *
 * なぜ、次の呼び出しができないのでしょうか。
 *
 * UnaryOperator<T>のcompose メソッドの戻り値型を注意深く調べなさい。なぜ、 transform
 * メソッドに対しては、適切ではないのでしょうか。関数合成に関しては、ス トラクチャル型（structural type）とノミナル型（nominal
 * type）*4のユーティリティに関 して、何が言えますか。
 *
 * The reurned type of op.compose(Color::grayscale) is Function<Color, Color>,
 * which cannot be assigned to UnaryOperator<Color>.
 *
 * If structural type was accepted, then the compose would be fine.
 */
public class ImageDemo extends Application {

    public static Image transform(Image in, UnaryOperator<Color> f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(
                width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y,
                        f.apply(in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

    public static <T> UnaryOperator<T> compose(UnaryOperator<T> op1,
            UnaryOperator<T> op2) {
        return t -> op2.apply(op1.apply(t));
    }

    @Override
    public void start(Stage stage) {
        Image image = new Image("eiffel-tower.jpg");

        UnaryOperator<Color> op = Color::brighter;
        // Image finalImage = transform(image, op.compose(Color::grayscale));
        Function<Color, Color> op2 = op.compose(Color::grayscale);
        UnaryOperator<Color> op3 = (UnaryOperator<Color>) op2; // ClassCastException

        Image image2 = transform(image, Color::brighter);
        Image finalImage = transform(image, op3);

        stage.setScene(new Scene(new HBox(
                new ImageView(image),
                new ImageView(image2),
                new ImageView(finalImage))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
