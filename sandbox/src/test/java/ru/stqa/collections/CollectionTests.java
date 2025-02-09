package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    }
