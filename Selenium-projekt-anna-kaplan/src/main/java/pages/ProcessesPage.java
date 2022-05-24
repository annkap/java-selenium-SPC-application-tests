package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProcessesPage extends HomePage {

    private String GENERIC_PROCESS_ROW_XPATH = "//td[text()='%s']/..";

    public ProcessesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".page-title h3")
    private WebElement pageHeader;

    @FindBy(css = ".btn-success")
    private WebElement addNewProcessBtn;

    @FindBy(css = ".table-responsive")
    private WebElement processesTable;

    public CreateProcessPage clickAddNewProcessBtn() {
        addNewProcessBtn.click();

        return new CreateProcessPage(driver);
    }

    public pages.ProcessesPage assertURLisCorrect(String expectedURL) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        return this;
    }

    public pages.ProcessesPage assertProcessesHeader() {
        Assert.assertTrue(pageHeader.isDisplayed());
        Assert.assertEquals(pageHeader.getText(), "Processes");

        return this;
    }


    public pages.ProcessesPage assertProcess(String expName, String expDescription, String expNotes) {
        String processXpath = String.format(GENERIC_PROCESS_ROW_XPATH, expName);

        WebElement processRow = driver.findElement(By.xpath(processXpath));

        String actDescription = processRow.findElement(By.xpath("./td[2]")).getText();
        String actNotes = processRow.findElement(By.xpath("./td[3]")).getText();

        Assert.assertEquals(actDescription, expDescription);
        Assert.assertEquals(actNotes, expNotes);

        return this;
    }
}
