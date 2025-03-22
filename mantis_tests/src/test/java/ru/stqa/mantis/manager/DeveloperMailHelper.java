package ru.stqa.mantis.manager;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.net.CookieManager;

public class DeveloperMailHelper extends HelperBase{
    OkHttpClient client;
    public DeveloperMailHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public DeveloperMailUser addUser() {
        return null;
    }

    public void deleteUser(DeveloperMailUser user) {

    }
}
