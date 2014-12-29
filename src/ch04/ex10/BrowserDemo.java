/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch04.ex10;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Using the web viewer, implement a browser with a URL bar and a back button.
 * Hint: WebEngine.getHistory().
 *
 * WebViewer を使用して、URL バーと戻るボタンを持つブラウザを実装しなさい。ヒント：WebEngine.getHistory()。
 */
public class BrowserDemo extends Application {

    @Override
    public void start(Stage stage) {
        WebView browser = new WebView();
        WebEngine engine = browser.getEngine();
        WebHistory history = engine.getHistory();
        
        Button backButton = new Button("←");
        backButton.setOnAction(e -> history.go(-1));
        Button forwardButton = new Button("→");
        forwardButton.setOnAction(e -> history.go(1));
        
        TextField urlField = new TextField();
        urlField.setOnAction(e -> engine.load(urlField.getText()));
        

        VBox box = new VBox();
        FlowPane firstLine = new FlowPane();
        firstLine.getChildren().addAll(backButton, forwardButton, urlField);
        
        box.getChildren().addAll(firstLine, browser);
        
        
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
