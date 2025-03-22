package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;
import java.util.stream.Stream;
public class UserRegistrationTests extends TestBase{
    @Test
    void canRegisterUser(){
        //создать пользователя (адрес) на почтовом сервере (JamesHelper)
        var username=CommonFunctions.randomString(10);
        var email=String.format("%s@localhost", username);
        //app.jamesCli().addUser(email, "password");
        app.jamesApi().addUser(email,"password");
        //заполняем форму создания и отправляем (браузер)
        app.registration().registerNewUser(email,username);
        //получаем почту (MailHelper)
        var messages=app.mail().receive(email,"password", Duration.ofSeconds(10));
        var text=messages.get(0).content();
        //извлекаем ссылку из письма
        var pattern= Pattern.compile("http://\\S*");
        var matcher=pattern.matcher(text);
        String url = null;
        if (matcher.find()){
            url = text.substring(matcher.start(),matcher.end());
            System.out.println(url);
        }
        //проходим по ссылке и завершаем регистрацию (браузер)
        app.registration().confirmRegistration(url);
        app.registration().updatePassword();
        //проверяем что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username,"new_password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
