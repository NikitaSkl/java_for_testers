package ru.stqa.geometry.figures;

import java.util.Objects;

public class Rectangle {

    private double a;
    private double b;

    public Rectangle(double a, double b) {
        if (a <0 | b <0) throw new IllegalArgumentException("Rectangle side should be non-negative");
        else {
            this.a = a;
            this.b = b;
        }
    }

    public static void printRectangleArea(double side1, double side2) {
        var text=String.format("Площадь прямоугольника со сторонами %f и %f = %f",side1,side2, rectangleArea(side1, side2));
        System.out.println(text);
    }

    private static double rectangleArea(double side1, double side2) {
        return side1 * side2;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Rectangle rectangle = (Rectangle) object;
        return (Double.compare(this.a, rectangle.a) == 0 && Double.compare(this.b, rectangle.b) == 0)
                || (Double.compare(this.b, rectangle.a) == 0 && Double.compare(this.a, rectangle.b) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
