/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex06;

import java.util.function.BiFunction;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Complete the method
 * <blockquote><pre>
 * public static <T> Image transform(Image in,  BiFunction<Color, T> f, T arg)
 * </blockquote></pre>
 *
 * from Section 3.4, “Returning Functions,” on page 53.
 *
 * 65 ページの3.4 節「関数を返す」で見た次のメソッドを完成させなさい。
 */
public class ImageTransformation extends Application {

    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(
                width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y,
                        f.apply(in.getPixelReader().getColor(x, y), arg));
            }
        }
        return out;
    }

    @Override
    public void start(Stage stage) {
        Image image = new Image("queen-mary.png");
        Image brightenedImage = transform(image,
                (c, factor) -> c.deriveColor(0, 1, factor, 1),
                1.2);
        stage.setScene(new Scene(new HBox(new ImageView(image),
                new ImageView(brightenedImage))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
