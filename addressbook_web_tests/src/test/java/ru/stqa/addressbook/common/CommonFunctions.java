package ru.stqa.addressbook.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        var result="";
        for (int i = 0; i < n; i++) {
            result=result+(char)('a'+rnd.nextInt(26));
        }
        /*if (n<20){
            result=result+'\'';
        }*/
        return result;
    }

    public static String randomStringOfNumbers(int n) {
        var rnd = new Random();
        var result="";
        for (int i = 0; i < n; i++) {
            result=result+(char)('0'+rnd.nextInt(10));
        }
        return result;
    }

    public static String randomFile(String directory){
        var fileNames=new File(directory).list();
        var rnd = new Random();
        var index=rnd.nextInt(fileNames.length);
        return Paths.get(directory,fileNames[index]).toAbsolutePath().toString();
    }
}
