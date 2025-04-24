package com.example;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Handles the logic for uploading a single image file using a file chooser.
 */
public class ImageUploader {

    public void uploadImage() {
        Stage stage = new Stage();
        // Set up the file chooser with image file extensions
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            ImageDisplay imageDisplay = new ImageDisplay();
            imageDisplay.displayImage(file); 
        }
    } 

} 
