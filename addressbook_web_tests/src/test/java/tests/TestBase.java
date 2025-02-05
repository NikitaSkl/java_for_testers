package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

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

}
