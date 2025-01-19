package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side){
        String text = String.format("Площадь квадрата со стороной %f = %f", side, getSquareArea(side));
        System.out.println(text);
    }

    private static double getSquareArea(double side) {
        return side * side;
    }
}
