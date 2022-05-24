import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.LoginPage;

public class PositiveRegistrationTest extends SeleniumBaseTest {

    Faker faker = new Faker();
    String emailFaker = faker.internet().emailAddress();
    String password = "Test1!";

    @Test
    public void shouldRegister() {

        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(emailFaker)
                .typePassword(password)
                .typeConfirmationPassword(password)
                .registerNewUser()
                .assertWelcomeElementIsShown();
    }
}
