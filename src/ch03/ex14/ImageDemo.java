/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex14;

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
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        
        Image blurredImage = LatentImage.from(image).transform(
                (x, y, reader) -> reader.getColor(width - (x + 1), y))
                .transform(Color::grayscale).toImage();
        Image edgeDetectedImage = LatentImage.from(image).transform(Color::grayscale)
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
}
