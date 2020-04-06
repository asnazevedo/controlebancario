package seleniumtraining.test;
import static seleniumtraining.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import seleniumtraining.core.DSL;

public class TestPrine {
	
	private DSL dsl;

	@Before
	public void inicializa(){
		dsl = new DSL();
	}
	
	@After
	public void finaliza(){
//		getDriver().quit();
	}

	@Test
	public void testShouldInteractWihRadioPrime(){
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clickRadio(By.xpath("//input[@id='j_idt721:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioChecked("j_idt721:console:0"));
		dsl.clickRadio(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioChecked("j_idt721:console:1"));
	}
	
	@Test
	public void testShouldInteractWithSelectPrime(){
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.selectComboPrime("j_idt721:console", "Xbox One");
		Assert.assertEquals("Xbox One", dsl.getText("j_idt721:console_label"));
	}
}
