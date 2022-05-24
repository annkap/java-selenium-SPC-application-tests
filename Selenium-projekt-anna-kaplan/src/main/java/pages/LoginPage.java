package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;


public class LoginPage {
    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    private WebElement emailTxt;

    @FindBy(css = "#Password")
    private WebElement passwordTxt;

    @FindBy(css = "button[type=submit]")
    private WebElement loginBtn;

    @FindBy(css = ".validation-summary-errors>ul>li")
    public List<WebElement> loginErrors;

    @FindBy(css = "a[href*='Register']")
    private WebElement registerLink;

    public pages.LoginPage typeEmail(String email) {
        emailTxt.clear();
        emailTxt.sendKeys(email);
        return this;
    }

    public pages.LoginPage typePassword(String password) {
        passwordTxt.clear();
        passwordTxt.sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        loginBtn.click();
        return new HomePage(driver);
    }

    public HomePage login(String email, String password) {
        typeEmail(email);
        typePassword(password);
        return submitLogin();
    }

    public pages.LoginPage submitLoginWithFailure() {
        loginBtn.click();
        return this;
    }

    public pages.LoginPage assertLoginErrorIsShown(String expectedError) {
        Assert.assertTrue(
                loginErrors
                        .stream()
                        .anyMatch(error -> error.getText().equals(expectedError))
        );
        return this;
    }

    public CreateAccountPage goToRegisterPage() {
        registerLink.click();
        return new CreateAccountPage(driver);
    }
}



