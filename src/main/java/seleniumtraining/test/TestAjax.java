package seleniumtraining.test;

import static seleniumtraining.core.DriverFactory.getDriver;
import static seleniumtraining.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumtraining.core.DSL;




public class TestAjax {
	

	private DSL dsl;

	@Before
	public void starting(){
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void terminate(){
		killDriver();
	}

	@Test
	public void testAjax(){
		dsl.write("j_idt720:name", "Teste");
		dsl.clickButton("j_idt720:j_idt723");
		WebDriverWait wait = new WebDriverWait(getDriver(), 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt720")));
		Assert.assertEquals("Teste", dsl.getText("j_idt720:display"));
	}
}
