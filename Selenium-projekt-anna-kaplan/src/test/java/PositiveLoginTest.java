import org.testng.annotations.Test;
import pages.LoginPage;

public class PositiveLoginTest extends SeleniumBaseTest {

    @Test
    public void shouldLogin() {
        new LoginPage(driver)
                .login(config.getApplicationUser(), config.getApplicationPassword())
                .assertWelcomeElementIsShown();
    }
}
