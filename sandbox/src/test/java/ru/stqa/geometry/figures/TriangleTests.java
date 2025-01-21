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
}
