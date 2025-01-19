package ru.stqa.geometry.figures;

public class Square {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public static void printSquareArea(Square square){
        String text = String.format("Площадь квадрата со стороной %f = %f", square.side, square.area());
        System.out.println(text);
    }
    public double area() {
        return this.side*this.side;
    }

    public double perimeter() {
        return this.side*4;
    }
}
