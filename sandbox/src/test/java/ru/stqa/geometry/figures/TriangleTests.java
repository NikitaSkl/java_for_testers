package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter(){
        var t=new Triangle(8.0,6.0,10.0);
        Assertions.assertEquals(24,t.perimeter());
    }
    @Test
    void canCalculateArea(){
        Assertions.assertEquals(10.825317547305483,new Triangle(5.0,5.0,5.0).area());
    }
    @Test
    void cannotCreateTriangleWithNegativeSide(){
        try {
            new Triangle(-3.0, 3.0, 2.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }
    @Test
    void cannotCreateTriangleViolatingTriangleInequalityTheorem(){
        try {
            new Triangle(1.0,2.0,4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            System.out.println(exception);
        }
    }
    @Test
    void testEquality(){
        var triangle1=new Triangle(3.0,4.0, 5.0);
        var triangle2=new Triangle(3.0,4.0, 5.0);
        Assertions.assertEquals(triangle1,triangle2);
    }
    @Test
    void testEquality2(){
        var triangle1=new Triangle(3.0,4.0, 5.0);
        var triangle2=new Triangle(5.0,4.0, 3.0);
        Assertions.assertEquals(triangle1,triangle2);
    }
    @Test
    void testEquality3(){
        var triangle1=new Triangle(3.0,4.0, 5.0);
        var triangle2=new Triangle(5.0,3.0, 4.0);
        Assertions.assertEquals(triangle1,triangle2);
    }
    @Test
    void testEquality4(){
        var triangle1=new Triangle(3.0,4.0, 5.0);
        var triangle2=new Triangle(3.0,5.0, 4.0);
        Assertions.assertEquals(triangle1,triangle2);
    }
    @Test
    void testEqualit5(){
        var triangle1=new Triangle(3.0,4.0, 5.0);
        var triangle2=new Triangle(4.0,3.0, 5.0);
        Assertions.assertEquals(triangle1,triangle2);
    }
    @Test
    void testEqualit6(){
        var triangle1=new Triangle(3.0,4.0, 5.0);
        var triangle2=new Triangle(5.0,4.0, 3.0);
        Assertions.assertEquals(triangle1,triangle2);
    }
}
