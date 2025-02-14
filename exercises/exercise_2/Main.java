public class Main {
    public static void main(String[] args) {
        Shape triangle = new Triangle("Triangle", 3, 4, 3, 4, 5);
        Shape rectangle = new Rectangle("Rectangle", 7, 5);
        Shape circle = new Circle("Circle", 8);
        Shape square = new Square("Square", 5);
        
        Shape[] shapes = {triangle, rectangle, circle, square};
        
        for (Shape shape : shapes) {
            shape.display();
            System.out.println("Area: " + shape.calculateArea());
            System.out.println("Perimeter: " + shape.calculatePerimeter());
            System.out.println("------------");
        }
    }
}
