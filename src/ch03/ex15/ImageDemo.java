package ch03.ex15;

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
        
        ParallelLatentImage pli = ParallelLatentImage.from(image);
        pli.transform(Color::brighter);
        pli.transform(Color::grayscale);
        Image result = pli.toImage();

        stage.setScene(new Scene(new HBox(
                new ImageView(image),
                new ImageView(result))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
