package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

//С помощью базового класса избавились от общих элементов в тестах: инициализация переменной, вспомогательные методы
public class TestBase {

    protected static ApplicationManager app;



    @BeforeEach //фикстура, подготовительный код
    public void setUp() {
        if (app==null){
            app=new ApplicationManager();
        }
        app.init(System.getProperty("browser","chrome"));
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
