package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point of the JavaFX application.
 * This class sets up the primary stage and displays the main UI created by ImageManager.
 */

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Management Tool");
        ImageManager manager = new ImageManager();
        Scene scene = new Scene(manager.createContent(), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

