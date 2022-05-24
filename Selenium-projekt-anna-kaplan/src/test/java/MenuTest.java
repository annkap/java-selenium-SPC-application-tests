import org.testng.annotations.Test;
import pages.LoginPage;

public class MenuTest extends SeleniumBaseTest {

    @Test
    public void menuTest() {
        new LoginPage(driver)
                .login(config.getApplicationUser(), config.getApplicationPassword())
                .goToProcesses()
                .assertURLisCorrect(config.getApplicationUrl() + "Projects")
                .assertProcessesHeader()
                .goToCharacteristics()
                .assertURLisCorrect(config.getApplicationUrl() + "Characteristics")
                .assertProcessesHeader()
                .goToDashboards()
                .assertURLisCorrect(config.getApplicationUrl())
                .assertDemoProjectHeaderIsShown();
    }
}