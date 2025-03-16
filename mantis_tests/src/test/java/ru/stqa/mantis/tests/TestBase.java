package ru.stqa.mantis.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.mantis.manager.ApplicationManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected static ApplicationManager app;
    @BeforeEach //фикстура, подготовительный код
    public void setUp() throws IOException {
        var properties=new Properties();
        properties.load(new FileReader(System.getProperty("target","local.properties"))); //прочитали файл, используя системное свойство, как значение целевого файла (конфига) - задали его в build.gradle
        if (app==null){
            app=new ApplicationManager();
        }
        app.init(System.getProperty("browser","chrome"), properties);
    }

}
