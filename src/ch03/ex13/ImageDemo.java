/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex13;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageDemo extends Application {

    @Override
    public void start(Stage stage) {
        Image image = new Image("eiffel-tower.jpg");

        Image finalImage = LatentImage.from(image).transform(blur())
                .toImage();
        stage.setScene(new Scene(new HBox(
                new ImageView(image),
                new ImageView(finalImage))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static ColorTransformer blur() {
        return (x, y, image) -> {
            int width = image.getWidth();
            int height = image.getHeight();
            Color[] colors = new Color[9];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    colors[i * 3 + j] = getColor(image,
                            x + i - 1,
                            y + i - 1,
                            width,
                            height);
                }
            }
            double red = 0;
            double green = 0;
            double blue = 0;
            int pixels = 0;

            for (Color c : colors) {
                if (c == null)
                    continue;
                pixels ++;
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }

            return Color.color(red / pixels, green / pixels, blue / pixels);
        };
    }

    private static Color getColor(LatentImage image,
            int x, int y,
            int width, int height) {

        if (x < 0 || width <= x) {
            return null;
        }
        if (y < 0 || height <= y) {
            return null;
        }

        return image.getColor(x, y);
    }
}
