import java.io.*;

abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    // Abstract methods for area and perimeter
    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    // Static field to represent color of the shape
    protected static String color;

    public static void setColor(String color) {
        Shape.color = color;
    }

    public static String getColor() {
        return color;
    }
}