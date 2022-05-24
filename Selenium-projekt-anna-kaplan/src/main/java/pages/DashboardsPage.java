package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DashboardsPage extends HomePage {

    public DashboardsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".x_title h2")
    private WebElement demoHeader;

    public DashboardsPage assertURLisCorrect(String expectedURL) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        return this;
    }

    public DashboardsPage assertDemoProjectHeaderIsShown() {
        Assert.assertEquals(demoHeader.getText(), "DEMO PROJECT");

        return this;
    }
}
