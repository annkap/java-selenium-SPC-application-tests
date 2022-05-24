package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateProcessPage extends HomePage{

    public CreateProcessPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#Name")
    private WebElement processNameTxt;

    @FindBy(css = "#Description")
    private WebElement processDescriptionTxt;

    @FindBy(css = ".btn-success")
    private WebElement createBtn;

    public CreateProcessPage typeProcessName(String processName) {
        processNameTxt.clear();
        processNameTxt.sendKeys(processName);

        return this;
    }

    public CreateProcessPage typePrecessDescription(String processDescription){
        processDescriptionTxt.clear();
        processDescriptionTxt.sendKeys(processDescription);

        return this;
    }

    public ProcessesPage createProcess(){
        createBtn.click();

        return new ProcessesPage(driver);
    }
}
