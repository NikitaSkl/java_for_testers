package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase{
    DeveloperMailUser user;
    @Test
    void canCreateUser(){
        var password="password";
        user=app.developerMailHelper().addUser();
        var email=String.format("%s@developermail", user.name());

//        app.registration().registerNewUser(email,username);
//        var messages=app.mail().receive(email,"password", Duration.ofSeconds(10));
//        var text=messages.get(0).content();
//        var pattern= Pattern.compile("http://\\S*");
//        var matcher=pattern.matcher(text);
//        String url = null;
//        if (matcher.find()){
//            url = text.substring(matcher.start(),matcher.end());
//            System.out.println(url);
//        }
//        app.registration().confirmRegistration(url);
//        app.registration().updatePassword();
//        app.http().login(username,"new_password");
//        Assertions.assertTrue(app.http().isLoggedIn());
        }
    @AfterEach
    void deletaMailUser(){
        app.developerMailHelper().deleteUser(user);
    }
}
