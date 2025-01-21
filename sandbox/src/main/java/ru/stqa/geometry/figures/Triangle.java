package ru.stqa.geometry.figures;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public static void printTriangleArea(Triangle triangle){
        var text=String.format("Площадь треугольника со сторонами %f, %f и %f = %f", triangle.a, triangle.b, triangle.c, triangle.area());
        System.out.println(text);
    }

    public double perimeter() {
        return this.a+this.b+this.c;
    }

    public double area() {
        var halfPerimeter=(this.a+this.b+this.c)/2;
        return Math.sqrt(halfPerimeter*(halfPerimeter-a)*(halfPerimeter-b)*(halfPerimeter-c));
    }
}
