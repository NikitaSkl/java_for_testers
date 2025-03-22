package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private Properties properties; // в нем хранятся настройки окружения
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private RegistrationHelper registration;
    private JamesApiHelper jamesApi;
    private DeveloperMailHelper developerMailHelper;
    private RestApiHelper restApiHelper;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties=properties;

    }
    public WebDriver driver(){
        if (driver==null){
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(2064, 1128));
        }
        return driver;
    }
    public SessionHelper session(){
        if (sessionHelper==null){
            sessionHelper=new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper==null){
            httpSessionHelper=new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }
    public JamesCliHelper jamesCli() {
        if (jamesCliHelper ==null){
            jamesCliHelper =new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }
    public MailHelper mail() {
        if (mailHelper ==null){
            mailHelper =new MailHelper(this);
        }
        return mailHelper;
    }
    public RegistrationHelper registration() {
        if (registration ==null){
            registration =new RegistrationHelper(this);
        }
        return registration;
    }
    public JamesApiHelper jamesApi() {
        if (jamesApi ==null){
            jamesApi =new JamesApiHelper(this);
        }
        return jamesApi;
    }
    public DeveloperMailHelper developerMailHelper() {
        if (developerMailHelper ==null){
            developerMailHelper =new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }
    public RestApiHelper rest() {
        if (restApiHelper ==null){
            restApiHelper =new RestApiHelper(this);
        }
        return restApiHelper;
    }
    public String property(String name){
        return properties.getProperty(name);
    }
}
