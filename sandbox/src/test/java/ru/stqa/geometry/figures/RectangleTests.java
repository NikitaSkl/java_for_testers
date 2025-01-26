package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {
    @Test
    void cannotCreateRectangleWithNegativeSide(){
        try {
            new Rectangle(-5.0,4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            System.out.println("Negative ");
        }
    }
    @Test
    void testEquality(){
        var rectangle1=new Rectangle(5.0,3.0);
        var rectangle2=new Rectangle(5.0,3.0);
        Assertions.assertEquals(rectangle1,rectangle2);

    }
    @Test
    void testEquality2(){
        var rectangle1=new Rectangle(5.0,3.0);
        var rectangle2=new Rectangle(3.0,5.0);
        Assertions.assertEquals(rectangle1,rectangle2);

    }
    @Test
    void testNonEquality(){
        var r1=new Rectangle(4.0,3.0);
        var r2=new Rectangle(5.0, 2.0);
        Assertions.assertNotEquals(r1,r2);
    }
}
