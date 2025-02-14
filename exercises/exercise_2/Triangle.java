class Triangle extends Shape {
    private double base, height, sideA, sideB, sideC;
    
    public Triangle(String name, double base, double height, double sideA, double sideB, double sideC) {
        super(name);
        this.base = base;
        this.height = height;
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
    
    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }
}