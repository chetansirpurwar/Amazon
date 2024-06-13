package glueCode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import runnerLogin.ConfigReader;
import shared.Shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginStepDef {

	    @SuppressWarnings("unused")
		private WebDriver driver;
	    private LoginPage loginPage;
	    private ConfigReader configReader;
	    private Logger logger;

	    public LoginStepDef(Shared shared) {
	        this.driver = Shared.driver;
	        this.loginPage = Shared.loginPage;
	        this.configReader = Shared.configReader; // Initialize configReader from Shared object
	        this.logger = LogManager.getLogger(this.getClass());
	    }

    @Given("User is on Amazon login page")
    public void i_am_on_the_amazon_login_page() {
        String url = configReader.getProperty("url");
        logger.info("Navigating to Amazon login page: " + url);
        loginPage.goToLoginPage(url);
    }

    @When("User provide username")
    public void i_enter_the_email() {
        String username = configReader.getProperty("username");
        logger.info("Entering username: " + username);
        loginPage.enterUsername(username);
    }

    @When("provide password")
    public void i_enter_the_password() {
        String password = configReader.getProperty("password");
        logger.info("Entering password.");
        loginPage.enterPassword(password);
    }

    @When("click the sign in button")
    public void i_click_the_sign_in_button() {
        logger.info("Clicking the sign in button.");
        loginPage.clickSignInButton();
    }

    @Then("User should login successfully")
    public void i_should_be_logged_in() {
        String expectedTitle = "Online Return Center";
        logger.info("Checking if user is logged in by verifying the page title contains: " + expectedTitle);
        try {
            String actualTitle = loginPage.getTitle();
            Assert.assertTrue(actualTitle.contains(expectedTitle), "Login failed. Page title does not contain expected text: " + expectedTitle);
            logger.info("Login successful. Page title contains: " + expectedTitle);
        } catch (Exception e) {
            logger.error("An error occurred while checking the login status.", e);
            throw e;
        }
    }

    @Then("I should see my profile name {string}")
    public void i_should_see_my_profile_name(String expectedProfileName) {
        String actualProfileName = loginPage.getProfileName();
        try {
            Assert.assertEquals(actualProfileName, expectedProfileName, "Profile name does not match.");
            logger.info("Profile name matches expected: " + expectedProfileName);
        } catch (AssertionError e) {
            logger.error("Profile name does not match. Expected: " + expectedProfileName + ", but was: " + actualProfileName);
            throw e;
        }
    }
}
