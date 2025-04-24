
package com.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The ImageConversion class implements the ImageConversionStrategy interface.
 * It provides functionality to convert image files from one format to another.
 * The conversion is performed by reading the input image, then writing it to the 
 * output file in the specified format.
 */

public class ImageConversion implements ImageConversionStrategy {
    @Override
    public File convert(File inputFile, String format, File outputFile) {
        try {
            BufferedImage inputImage = ImageIO.read(inputFile);
            ImageIO.write(inputImage, format, outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}