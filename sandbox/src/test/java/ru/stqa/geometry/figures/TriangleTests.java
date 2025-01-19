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
        Assertions.assertEquals(4.15,new Triangle(3.0,3.0,5.0).area());
    }
}
