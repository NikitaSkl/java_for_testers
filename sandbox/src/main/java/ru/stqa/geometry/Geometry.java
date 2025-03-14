package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare= () -> new Square(new Random().nextDouble(100));
        var squares= Stream.generate(randomSquare).limit(5);
        /*Consumer<Square> print=(square)->{
            Square.printSquareArea(square);
            Square.printPerimeter(square);
        };*/

        squares.peek(Square::printPerimeter).forEach(Square::printSquareArea);

    }
}
