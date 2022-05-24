import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.UUID;

public class PositiveProcessCreatingTest extends SeleniumBaseTest {

    String processName = "process" + UUID.randomUUID().toString().substring(0, 10);

    @Test
    public void shouldCreateNewProcess() {
        new LoginPage(driver)
                .login(config.getApplicationUser(), config.getApplicationPassword())
                .goToProcesses()
                .clickAddNewProcessBtn()
                .typeProcessName(processName)
                .typePrecessDescription("Testing description")
                .createProcess()
                .assertProcess(processName, "Testing description", "");
    }
}
