/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex12;

import javafx.application.Application;
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
        ColorTransformer ct = (x, y, c) -> c.grayscale();

        Image finalImage = LatentImage.from(image)
                .transform(Color::brighter).transform(ct)
                .toImage();
        stage.setScene(new Scene(new HBox(
                new ImageView(image),
                new ImageView(finalImage))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
