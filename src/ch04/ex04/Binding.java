/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Enhance the program in Section 4.5, “Bindings,” on page 75 so that the circle
 * stays centered and always touches at least two of the sides of the scene.
 *
 * 91 ページの4.5 節「バインディング」のプログラムについて、円が真ん中に配置され、 シーンの4 つの辺の少なくとも2
 * つの辺に常に接するように機能拡張しなさい。
 */
public class Binding extends Application {

    @Override
    public void start(Stage stage) {
        Circle circle = new Circle(100, 100, 100);
        circle.setFill(Color.RED);
        Pane pane = new Pane();
        pane.getChildren().add(circle);
        Scene scene = new Scene(pane);
        circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
        circle.radiusProperty().bind(
                Bindings.min(scene.widthProperty(), scene.heightProperty()).divide(2));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
