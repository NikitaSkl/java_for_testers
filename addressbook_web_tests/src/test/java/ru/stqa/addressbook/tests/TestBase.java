package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.AfterEach;
import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//С помощью базового класса избавились от общих элементов в тестах: инициализация переменной, вспомогательные методы
public class TestBase {

    protected static ApplicationManager app;


    @AfterEach
    void checkBaseConsistency(){
        app.jdbc().checkConsistency();
    }

    @BeforeEach //фикстура, подготовительный код
    public void setUp() throws IOException {
        var properties=new Properties(); //создали новый объект
        properties.load(new FileReader(System.getProperty("target","local.properties"))); //прочитали файл, используя системное свойство, как значение целевого файла (конфига) - задали его в build.gradle
        if (app==null){
            app=new ApplicationManager();
        }
        app.init(System.getProperty("browser","chrome"), properties);
    }

}
