package com.example;

import java.io.File;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class extends BaseView and is responsible for displaying an image 
 * along with related details.
 */
public class ImageDisplay extends BaseView {
    private ImageView imageView;
    private Label details;

    @Override
    protected void initView() {
       // Initialize display components
        imageView = new ImageView();
        details = new Label();
        // Add components to BaseView (VBox)
        getChildren().addAll(imageView, details);
    }

   
    public void displayImage(File file) {
        // Load image
        Image img = new Image(file.toURI().toString());
        imageView.setImage(img);
        imageView.setFitWidth(100); // Set image width
        imageView.setFitHeight(100); // Set image height

        // Create a StringBuilder to show image details
        StringBuilder sb = new StringBuilder();
        sb.append("File: ").append(file.getName()).append("\n")
          .append("W: ").append((int) img.getWidth()).append(" px\n")
          .append("H: ").append((int) img.getHeight()).append(" px\n");
        
        try {
             // Read image metadata (EXIF)
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            
             // Get camera model (if available)
            ExifIFD0Directory exif0 = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (exif0 != null) sb.append("Camera: ").append(exif0.getString(ExifIFD0Directory.TAG_MODEL)).append("\n");
            
            // Get original capture date/time (if available)
            ExifSubIFDDirectory exifSub = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (exifSub != null) sb.append("Date: ").append(exifSub.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)).append("\n");
            
            // Get GPS location info (if available)
            GpsDirectory gps = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if (gps != null && gps.getGeoLocation() != null) sb.append("Location: ").append(gps.getGeoLocation()).append("\n");
        } catch (Exception e) {
            sb.append("Metadata Error: ").append(e.getMessage());
        }

        // Update the displayed detailed information
        details.setText(sb.toString());
    }
}

