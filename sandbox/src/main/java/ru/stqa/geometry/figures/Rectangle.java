package ru.stqa.geometry.figures;

public class Rectangle {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public static void printRectangleArea(double side1, double side2) {
        var text=String.format("Площадь прямоугольнка со сторонами %f и %f = %f",side1,side2, rectangleArea(side1, side2));
        System.out.println(text);
    }

    private static double rectangleArea(double side1, double side2) {
        return side1 * side2;
    }
}
