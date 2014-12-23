/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Center the top and bottom buttons in Figure 4–7.
 *
 * 図4-7 のTop とBottom のボタンを真ん中にそろえなさい。
 */
public class BorderPaneDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        pane.setTop(createCenteredButtonBox("Top"));
        pane.setLeft(new Button("Left"));
        pane.setCenter(new Button("Center"));
        pane.setRight(new Button("Right"));
        pane.setBottom(createCenteredButtonBox("Bottom"));

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    private HBox createCenteredButtonBox(String label) {
        HBox box = new HBox();
        box.getChildren().add(new Button(label));
        box.setAlignment(Pos.CENTER);
        return box;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
