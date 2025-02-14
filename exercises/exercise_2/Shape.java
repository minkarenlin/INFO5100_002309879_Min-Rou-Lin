abstract class Shape {
    protected static String color = "Blue"; 
    protected String name; 
    
    public Shape(String name) {
        this.name = name;
    }
    
    public abstract double calculateArea(); 
    public abstract double calculatePerimeter(); 
    
    public static String getColor() {
        return color;
    }
    
    public String getName() {
        return name;
    }
    
    public void display() {
        System.out.println("Shape: " + name + ", Color: " + color);
    }
}