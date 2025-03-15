package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionTests {
    @Test
    public void arrayTest(){
        var array= new String[]{"a", "b", "c"}; //если нужна коллекция с фиксированнмы размером, то подойдет и массив
        Assertions.assertEquals("a",array[0]);
        array[0]="d";
        Assertions.assertEquals("d",array[0]);
    }
    @Test
    public void listTest() {
        //var list = new ArrayList<String>();
        //var list= List.of("a", "b", "c"); // более короткий вариант инициализации List, но такой список немодифицируемый
        var list = new ArrayList<String>(List.of("a", "b", "c")); //комбинируя две записи получаем короткую инициализацию модифицируемого списка
        Assertions.assertEquals(3,list.size());
        /*list.add("a");
        list.add("b");
        list.add("c");*/
        Assertions.assertEquals("a",list.get(0));
    }
    @Test
    public void setTests(){
        var set = new HashSet<>(List.of("a","b","c","a"));
        Assertions.assertEquals(3,set.size());
        var element=set.stream().findAny().get();
        set.add("a");
        Assertions.assertEquals(3,set.size());
    }
}
