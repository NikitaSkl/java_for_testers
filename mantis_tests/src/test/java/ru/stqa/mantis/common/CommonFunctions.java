package ru.stqa.mantis.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = ()->rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i->'a'+i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
        /*var result="";
        for (int i = 0; i < n; i++) {
            result=result+(char)('a'+rnd.nextInt(26));
        }
        return result;*/
    }

    public static String randomStringOfNumbers(int n) {
        var rnd = new Random();
        /*Supplier<Integer> randomNumbers = ()->rnd.nextInt(10);
         var result = Stream.generate(randomNumbers)
                 .limit(n)
                 .map(i->'0'+i)
                 .map(Character::toString)
                 .collect(Collectors.joining());
        return result;*/
        var result="";
        for (int i = 0; i < n; i++) {
            result=result+(char)('0'+rnd.nextInt(10));

        }
        return result;
    }
/*
    public static String randomFile(String directory){
        var fileNames=new File(directory).list();
        var rnd = new Random();
        var index=rnd.nextInt(fileNames.length);
        return Paths.get(directory,fileNames[index]).toAbsolutePath().toString();
    }*/
}
