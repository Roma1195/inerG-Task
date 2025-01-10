package Task_Page;



import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	
	protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(40));
    }

    public void selectState(WebElement selectElement, String state) {
        Select select = new Select(selectElement);
        wait.until(ExpectedConditions.elementToBeClickable(selectElement));
        select.selectByVisibleText(state);
        System.out.println("State '" + state + "' selected in dropdown");
    }
}


