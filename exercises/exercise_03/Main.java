import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args)  {

        Shape triangle = new Triangle(3, 4, 3, 4, 5);
        Shape rectangle = new Rectangle(7, 6);
        Shape circle = new Circle(7);
        Shape square = new Square(4);

    
        Shape.setColor("Red");

        // **Try-with-resources for Serialization**
        try (FileOutputStream fileOut = new FileOutputStream("shapes.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(triangle);
            out.writeObject(rectangle);
            out.writeObject(circle);
            out.writeObject(square);
            System.out.println("Objects serialized successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // **Try-with-resources for Deserialization**
        try (FileInputStream fileIn = new FileInputStream("shapes.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            Shape deserializedTriangle = (Shape) in.readObject();
            Shape deserializedRectangle = (Shape) in.readObject();
            Shape deserializedCircle = (Shape) in.readObject();
            Shape deserializedSquare = (Shape) in.readObject();

            System.out.println("\nDeserialized Objects:");
            System.out.println("Triangle Area: " + deserializedTriangle.calculateArea());
            System.out.println("Rectangle Area: " + deserializedRectangle.calculateArea());
            System.out.println("Circle Area: " + deserializedCircle.calculateArea());
            System.out.println("Square Area: " + deserializedSquare.calculateArea());

        
            System.out.println("Static color of shapes: " + Shape.getColor());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        
        }
    }
}
