package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CreateAccountPage {
    protected WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    private WebElement emailTxt;

    @FindBy(css = "#Password")
    private WebElement passwordTxt;

    @FindBy(css = "#ConfirmPassword")
    private WebElement confirmationPasswordTxt;

    @FindBy(css = "button[type=submit]")
    private WebElement registerBtn;

    @FindBy(css = ".validation-summary-errors>ul>li")
    public List<WebElement> loginErrors;

    public CreateAccountPage typeEmail(String email) {
        emailTxt.clear();
        emailTxt.sendKeys(email);
        return this;
    }

    public CreateAccountPage typePassword(String password) {
        passwordTxt.clear();
        passwordTxt.sendKeys(password);
        return this;
    }

    public CreateAccountPage typeConfirmationPassword(String password) {
        confirmationPasswordTxt.clear();
        confirmationPasswordTxt.sendKeys(password);
        return this;
    }

    public HomePage registerNewUser() {
        registerBtn.click();
        return new HomePage(driver);
    }

    public CreateAccountPage registerWithFailure() {
        registerBtn.click();
        return this;
    }

    public CreateAccountPage assertRegisterErrorIsShown(String expectedError) {
        boolean doesErrorMessageMatch = loginErrors
                .stream()
                .anyMatch(loginError -> loginError.getText().equals(expectedError));
        Assert.assertTrue(doesErrorMessageMatch);
        return this;
    }
}


