package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private FluentWait<WebDriver> wait;

    @FindBy(id = "ap_email")
    @CacheLookup
    private WebElement usernameField;

    @FindBy(id = "ap_password")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(id = "signInSubmit")
    @CacheLookup
    private WebElement loginButton;

    @FindBy(id = "nav-link-accountList-nav-line-1")
    @CacheLookup
    private WebElement profileName;
    

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        PageFactory.initElements(driver, this);
    }

    public void goToLoginPage(String url) {
        driver.get(url);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getProfileName() {
        wait.until(ExpectedConditions.visibilityOf(profileName));
        return profileName.getText();
    }
    
    public String getTitle() {
    	wait.until(ExpectedConditions.visibilityOf(profileName)); //dummy
    	return driver.getTitle(); 	
    }
}
