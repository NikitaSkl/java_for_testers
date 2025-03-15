package ru.stqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReverseChecks {
    @Test
    public void testSqrt() {
        var input = 5.0;
        var result = Math.sqrt(input);
        Assertions.assertEquals(result * result, input, 0.00001);
    }

    @Test
    public void testSort() {
        var list = new ArrayList<>(List.of(4, 9, 1, 7, 0));
        list.sort(Integer::compareTo);
        Assertions.assertEquals(list, new ArrayList<>(List.of(0, 1, 4, 7, 9)));
    }

    @Test
    public void testSort2() {
        var list = new ArrayList<>(List.of(4, 9, 1, 7, 0));
        list.sort(Integer::compareTo);
        for (int i = 0; i < list.size() - 1; i++) {
            Assertions.assertTrue(list.get(i) <= list.get(i + 1));
        }
    }

    @Test
    public void testMap() {
        var map = new HashMap<Character, String>();
        map.put('1', "one");
        map.put('2', "two");
        map.put('3', "three");
        Assertions.assertEquals("one", map.get('1'));
        map.put('1', "один");
        Assertions.assertEquals("один", map.get('1'));
    }
}
