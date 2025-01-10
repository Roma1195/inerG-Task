package Task_TestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Task_POM.InerG_TestPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestScript_Class {

	private WebDriver driver;
	InerG_TestPage poc;

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);

		driver.get("https://inerg-test.web.app/");
		driver.manage().window().maximize();

		poc = new InerG_TestPage(driver);
	}

	// To print hover value from line chart for a state
	@Test
	public void testSelectStateAndPrintLineChartValues() {

		poc.selectStateAndWaitForChart("Goa");
		poc.getLineChartPoints(); // to get line chart points
		poc.printTextValuesLine(); // Print the text values in Line Chart

	}

	// // To print hover value from pie chart for a different state
	@Test
	public void testSelectStateAndPrintPieChartValues() {

		poc.selectStateAndWaitForChart("Maharashtra");
		poc.getPieChartSlices(); // To get pie chart slices
		poc.printTextValuesPie(); // Print the text values in Line Chart

	}
	@Test
	public void testSelectStateAndPrintTileValues() {
		poc.selectStateAndWaitForChart("West Bengal");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		poc.printFromTile();

	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
