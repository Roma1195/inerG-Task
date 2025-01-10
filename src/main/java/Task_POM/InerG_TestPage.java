package Task_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Task_Page.BaseClass;

import java.time.Duration;
import java.util.List;

public class InerG_TestPage extends BaseClass {

	private WebElement stateDropdown;
	private List<WebElement> chartPoints;
	private List<WebElement> piePoints;
	Actions action;

	By tc = By.xpath("/html/body/div/div/div[1]/div/div/p[1]");
	By ac = By.xpath("/html/body/div/div/div[1]/div/div/p[2]");
	By rc = By.xpath("/html/body/div/div/div[1]/div/div/p[3]");
	By dt = By.xpath("/html/body/div/div/div[1]/div/div/p[4]");

	public InerG_TestPage(WebDriver driver) {
		super(driver);
		try {
			action = new Actions(driver);
			stateDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".data-filter-input")));
			System.out.println("State dropdown found");
		} catch (TimeoutException e) {
			System.out.println("State dropdown not found");
			throw e;
		}

	}

	public void selectStateAndWaitForChart(String state) {

		try {

			action.moveToElement(stateDropdown).perform();
			selectState(stateDropdown, state);
			System.out.println("State '" + state + "' selected");
		} catch (TimeoutException e) {
			System.out.println("Dropdown not found");
			throw e;
		}

	}

	public void getLineChartPoints() {
		try {
			chartPoints = wait.until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".graph-representation .points")));
			System.out.println("Line Chart points found");
		} catch (TimeoutException e) {
			System.out.println("Line Chart points not found");
			throw e;
		}
	}

	public void getPieChartSlices() {
		try {
			piePoints = wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(By.cssSelector(".graph-representation .slice .surface")));
			System.out.println("Pie Chart points found");
			System.out.println(piePoints);
		} catch (TimeoutException y) {
			System.out.println("Pie Chart points not found");
			throw y;
		}
	}

	public void printTextValuesLine() {
		try {
			//Actions action = new Actions(driver);
			for (WebElement point : chartPoints) {
				action.moveToElement(point).perform();
				Thread.sleep(1000); // Wait for the text to appear
				List<WebElement> tooltipElements = driver.findElements(By.cssSelector("g.hoverlayer g.hovertext text"));
				for (WebElement tooltipElement : tooltipElements) {
					String textContent = tooltipElement.getText();
					if (!textContent.isEmpty()) {
						System.out.println(textContent);
					} else {
						System.out.println("No text found");
					}
				}

			}

		}

		catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("Text elements not found");
			throw e;
		}
	}

	public void printTextValuesPie() {
		try {
			//Actions action1 = new Actions(driver);
			for (WebElement ppoint : piePoints) {
				action.moveToElement(ppoint, 0, 10).perform();
				Thread.sleep(1000); // Wait for the text to appear
				List<WebElement> tooltipElements1 = driver
						.findElements(By.cssSelector("g.hoverlayer g.hovertext text"));
				for (WebElement tooltipElement1 : tooltipElements1) {
					String textContent1 = tooltipElement1.getText();
					if (!textContent1.isEmpty()) {
						System.out.println(textContent1);
					} else {
						System.out.println("No text found");
					}
				}

			}
		}

		catch (InterruptedException ey) {
			ey.printStackTrace();
		} catch (TimeoutException ey) {
			System.out.println("Text elements not found");
			throw ey;
		}
	}

	public void printFromTile() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String totalCases = driver.findElement(tc).getText();
		System.out.println(totalCases);
		String activeCases = driver.findElement(ac).getText();
		System.out.println(activeCases);
		String recovered = driver.findElement(rc).getText();
		System.out.println(recovered);
		String death = driver.findElement(dt).getText();
		System.out.println(death);

	}
}
