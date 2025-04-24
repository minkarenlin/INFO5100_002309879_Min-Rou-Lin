
package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * It is responsible for providing a UI that allows users
 * to upload images, preview them, select a desired image format, and convert
 * and download the converted images using a specified conversion strategy.
 */

public class ImageManager {
    private VBox root = new VBox(10);
    private HBox imageBox = new HBox(10);
    private HBox convertedBox = new HBox(10);
    private ComboBox<String> formatBox = new ComboBox<>();
    private List<File> uploadedFiles = new ArrayList<>();
    private ImageConversionStrategy conversionStrategy = new ImageConversion();

    public VBox createContent() {
        root.setPadding(new Insets(15));

        Button uploadBtn = new Button("Upload Images");
        uploadBtn.setOnAction(e -> handleUpload());

        formatBox.getItems().addAll("png", "jpg", "bmp", "gif");
        formatBox.setValue("png");

        Button convertBtn = new Button("Convert Selected Format");
        convertBtn.setOnAction(e -> displayConvertedPreview());

        ScrollPane imageScroll = new ScrollPane(imageBox);
        imageScroll.setPrefHeight(250);
        imageBox.setPadding(new Insets(10));

        ScrollPane convertedScroll = new ScrollPane(convertedBox);
        convertedScroll.setPrefHeight(250);
        convertedBox.setPadding(new Insets(10));
        // Add components to the root container
        root.getChildren().addAll(
                uploadBtn,
                imageScroll,
                new Label("Select Format:"),
                formatBox,
                convertBtn,
                new Label("Converted Images"),
                convertedScroll
        );

        return root;
    }

     /**
     * Handles the image upload process by opening a FileChooser dialog,
     * allowing users to select multiple image files. It then displays the
     * selected images in the imageBox.
     */
    private void handleUpload() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
        );
          // Allow the user to select multiple files
        List<File> files = chooser.showOpenMultipleDialog(null);

        if (files != null) {
            uploadedFiles.clear();
            imageBox.getChildren().clear();
            // For each selected file, add it to the list and display it
            for (File file : files) {
                uploadedFiles.add(file);
                ImageDisplay display = new ImageDisplay();
                display.displayImage(file);
                imageBox.getChildren().add(display);
            }
        }
    }

      /**
     * Displays a preview of each uploaded image with a download button.
     * When clicked, the download button allows the user to save the image
     * in the selected format using the defined conversion strategy.
     */

    private void displayConvertedPreview() {
        String format = formatBox.getValue();
        convertedBox.getChildren().clear();

        for (File file : uploadedFiles) {
            ImageDisplay display = new ImageDisplay();
            display.displayImage(file); 

        
            Button download = new Button("Download");
            download.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName("converted_" + file.getName());
                fileChooser.getExtensionFilters().add(
                    new ExtensionFilter("Image Files", "*." + format)
                );
                // Show save dialog to allow the user to select where to save the file
                File outputFile = fileChooser.showSaveDialog(null);

                if (outputFile != null) {
                    File convertedFile = conversionStrategy.convert(file, format, outputFile);
                    if (convertedFile != null) {
                        try {
                            java.awt.Desktop.getDesktop().open(convertedFile);
                            System.out.println("File saved and opened: " + convertedFile.getAbsolutePath());
                        } catch (Exception ex) {
                            System.err.println("Error opening file: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    } else {
                        System.err.println("Conversion failed for file: " + file.getName());
                    }
                }
            });
            display.getChildren().add(download);

            convertedBox.getChildren().add(display);
        }
    }
}