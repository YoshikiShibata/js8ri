/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch04.ex05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author yoshiki
 */
public class BindingDemo3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button smaller = new Button("Smaller");
        Button larger = new Button("Larger");
        Rectangle gauge = new Rectangle(0, 5, 50, 15);
        Rectangle outline = new Rectangle(0, 5, 100, 15);
        outline.setFill(null);
        outline.setStroke(Color.BLACK);
        Pane pane = new Pane();
        pane.getChildren().addAll(gauge, outline);
        smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
        larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));
        smaller.disableProperty().bind(
                BindingUtil.observe(
                        t -> t.doubleValue() <= 0, gauge.widthProperty()));
        larger.disableProperty().bind(
                BindingUtil.observe(
                        t -> t.doubleValue() >= 100, gauge.widthProperty()));

        HBox box = new HBox(10);
        box.getChildren().addAll(smaller, pane, larger);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
