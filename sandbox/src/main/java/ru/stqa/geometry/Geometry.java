package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        var squareSide=8.;
        Square.printSquareArea(squareSide);
        Rectangle.printRectangleArea(3.0,5.0);
    }

}
