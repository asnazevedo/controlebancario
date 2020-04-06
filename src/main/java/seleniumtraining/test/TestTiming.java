package seleniumtraining.test;

import static seleniumtraining.core.DriverFactory.getDriver;
import static seleniumtraining.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumtraining.core.DSL;


public class TestTiming {

	private DSL dsl;

	@Before
	public void starting(){
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void terminate(){
		killDriver();
	}

	@Test
	
	public void testShouldInteractWithLongResponseFixWait() throws InterruptedException{
		dsl.clickButton("buttonDelay");
		Thread.sleep(5000);
		dsl.write("novoCampo", "Achei");
	}
	
	public void testShouldInteractWithLongResponseImplicitWait() throws InterruptedException{
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.clickButton("buttonDelay");
		dsl.write("novoCampo", "Achei");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	public void testShouldInteractWithLongResponseExplicitWait() throws InterruptedException{
		dsl.clickButton("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(),30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.write("novoCampo", "Achei");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
}
