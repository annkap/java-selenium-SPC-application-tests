package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CharacteristicsPage extends HomePage {

    public CharacteristicsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".page-title h3")
    private WebElement pageHeader;

    public CharacteristicsPage assertURLisCorrect(String expectedURL) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        return this;
    }

    public CharacteristicsPage assertProcessesHeader() {
        Assert.assertTrue(pageHeader.isDisplayed());
        Assert.assertEquals(pageHeader.getText(), "Characteristics");

        return this;
    }
}
