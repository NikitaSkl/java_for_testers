public class Geometry {
    public static void main(String[] args) {
        var squareSide=8.;
        printSquareArea(squareSide);
        printRectangleArea(3.0,5.0);
    }

    private static void printRectangleArea(double side1, double side2) {
        System.out.println("Площадь прямоугольнка со сторонами "+side1+" и "+side2+" = "+ getRectangleSquare(side1, side2));
    }

    private static double getRectangleSquare(double side1, double side2) {
        return side1 * side2;
    }

    static void printSquareArea(double side){
        System.out.println("Площадь квадрата со стороной "+side+" = "+ getSquareArea(side));
    }

    private static double getSquareArea(double side) {
        return side * side;
    }
}
