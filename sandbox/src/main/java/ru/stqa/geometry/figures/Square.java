package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side){
        System.out.println("Площадь квадрата со стороной "+side+" = "+ getSquareArea(side));
    }

    private static double getSquareArea(double side) {
        return side * side;
    }
}
