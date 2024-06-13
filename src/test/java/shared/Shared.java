package shared;

import org.openqa.selenium.WebDriver;
import hooks.Hook;
import pages.LoginPage;
import runnerLogin.ConfigReader;

public class Shared {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static ConfigReader configReader;

    public Shared() {
        driver = Hook.driver;
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
    }
}
