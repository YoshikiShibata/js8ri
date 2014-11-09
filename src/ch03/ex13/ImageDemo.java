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

        Image blurredImage = LatentImage.from(image).transform(blur())
                .toImage();
        Image edgeDetectedImage = LatentImage.from(image).transform(edgeDetect())
                .toImage();
        stage.setScene(new Scene(new HBox(
                new ImageView(image),
                new ImageView(blurredImage),
                new ImageView(edgeDetectedImage))));
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
                if (c == null) {
                    continue;
                }
                pixels++;
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }

            return Color.color(red / pixels, green / pixels, blue / pixels);
        };
    }

    private static ColorTransformer edgeDetect() {
        return (x, y, image) -> {
            int width = image.getWidth();
            int height = image.getHeight();
            Color[] colors = new Color[4];

            colors[0] = getColor(image, x, y + 1, width, height); // north
            colors[1] = getColor(image, x + 1, y, width, height); // east
            colors[2] = getColor(image, x, y - 1, width, height); // south
            colors[3] = getColor(image, x - 1, y, width, height); // west

            Color myColor = image.getColor(x, y);

            double red = 0.0;
            double green = 0.0;
            double blue = 0.0;
            int count = 0;
            for (Color c : colors) {
                if (c == null) {
                    continue;
                }
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
                count++;
            }
            
            if (count == 0) {
                throw new AssertionError("count == 0");
            }

            return Color.color(
                    fixColorValue(myColor.getRed() * count - red),
                    fixColorValue(myColor.getGreen() * count - green),
                    fixColorValue(myColor.getBlue() * count - blue));
        };
    }
    
    private static double fixColorValue(double value) {
        if (value < 0.0)
            return 0.0;
        if (value > 1.0)
            return 1.0;
        return value;
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
