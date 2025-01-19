package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double side1, double side2) {
        System.out.println("Площадь прямоугольнка со сторонами "+side1+" и "+side2+" = "+ getRectangleSquare(side1, side2));
    }

    private static double getRectangleSquare(double side1, double side2) {
        return side1 * side2;
    }
}
