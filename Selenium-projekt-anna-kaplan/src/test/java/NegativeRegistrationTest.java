import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class NegativeRegistrationTest extends SeleniumBaseTest {
    Faker faker = new Faker();
    String emailFaker = faker.internet().emailAddress();

    @DataProvider
    public static Object[][] wrongPasswords() {
        return new Object[][]{
                {"", "The Password field is required."},
                {"test", "The Password must be at least 6 and at max 100 characters long."},
                {"Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!Test1!", "The Password must be at least 6 and at max 100 characters long."},
                {"test1!", "Passwords must have at least one uppercase ('A'-'Z')."},
                {"TEST1!", "Passwords must have at least one lowercase ('a'-'z')."},
                {"Test!!", "Passwords must have at least one digit ('0'-'9')."},
                {"Test12", "Passwords must have at least one non alphanumeric character."}
        };
    }

    @Test
    public void shouldNotRegisterWhenPasswordsDontMatch() {
        String password = "Test1!";
        String confirmationPassword = "Test2!";

        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(emailFaker)
                .typePassword(password)
                .typeConfirmationPassword(confirmationPassword)
                .registerWithFailure()
                .registerWithFailure() // To invoke form validation
                .assertRegisterErrorIsShown("The password and confirmation password do not match.");
    }

    @Test(dataProvider = "wrongPasswords")
    public void shouldNotRegisterWhenPasswordIsIncorrect(String password1, String expectedErrorMessage) {
        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(emailFaker)
                .typePassword(password1)
                .typeConfirmationPassword(password1)
                .registerWithFailure()
                .assertRegisterErrorIsShown(expectedErrorMessage);
    }
}
