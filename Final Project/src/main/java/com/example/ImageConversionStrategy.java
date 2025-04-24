
package com.example;

import java.io.File;

/**
 * The interface defines the contract for converting image files
 * from one format to another. Implementing classes must provide logic to convert
 * an input image file to the specified format and save the result to the given output file.
 */

public interface ImageConversionStrategy {
    File convert(File inputFile, String format, File outputFile);
}
