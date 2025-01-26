package ru.stqa.geometry.figures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        if (a<0 || b<0 || c<0) throw new IllegalArgumentException("Triangle side should be non-negative");
        else if(a+b<=c || a+c<=b || b+c<=a) throw new IllegalArgumentException("Sum of two sides of a triangle should be always greater than the third");
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Triangle triangle = (Triangle) object;
        /*List<Double> sides=new ArrayList<>();
        sides.add(triangle.a);
        sides.add(triangle.b);
        sides.add(triangle.c);
        for (Double s : sides) {
            if (s.equals(this.a)) {
                sides.remove(s);
                break;
            }
        }
        for (Double s : sides) {
            if (s.equals(this.b)) {
                sides.remove(s);
                break;
            }
        }
        for (Double s : sides) {
            if (s.equals(this.c)) {
                sides.remove(s);
                break;
            }
        }
        return sides.isEmpty();*/
        return (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.c) == 0)
                || (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.a) == 0)
                || (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.b) == 0)
                || (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.b) == 0)
                || (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.c) == 0)
                || (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.a) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
