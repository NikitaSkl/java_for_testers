package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculateArea(){
        var s=new Square(5.0);
        Assertions.assertEquals(25.0, s.area());

    }
    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(20.0,new Square(5.0).perimeter());
    }
    @Test
    void cannotCreateSquareWithNegativeSide(){
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            System.out.println("Negative ");
        }
    }
    @Test
    void testEquality(){
        var square1=new Square(5.0);
        var square2=new Square(5.0);
        Assertions.assertEquals(square1,square2);

    }
    @Test
    void testNonEquality(){
        var square1=new Square(4.0);
        var square2=new Square(5.0);
        Assertions.assertNotEquals(square1,square2);
    }
    @Test
    void testPass(){
        var square1=new Square(5.0);
        var square2=new Square(5.0);
        Assertions.assertTrue(square1.equals(square2));
    }
}
