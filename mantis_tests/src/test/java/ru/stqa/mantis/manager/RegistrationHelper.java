package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{
    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }
    public void registerNewUser(String email, String username){
        click(By.linkText("Signup for a new account"));
        type(By.name("username"),username);
        type(By.name("email"),email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void confirmRegistration(String url) {
        manager.driver().get(url);
    }
    public void updatePassword(){
        type(By.name("realname"),"real_name");
        type(By.name("password"),"new_password");
        type(By.name("password_confirm"),"new_password");
        click(By.cssSelector("button[type='submit']"));
    }
}
