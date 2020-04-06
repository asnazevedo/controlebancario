package seleniumtraining.test;
import static seleniumtraining.core.DriverFactory.getDriver;
import static seleniumtraining.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import seleniumtraining.core.DSL;


public class TestAlert {
	
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
	public void testShouldInteractWihtSimpleAlert(){
		dsl.clickButton("alert");
		String texto = dsl.getAlertTextAndAccept(); 
		Assert.assertEquals("Alert Simples", texto);
		
		dsl.write("elementosForm:nome", texto);
	}
	
	@Test
	public void testShouldInteractWithConfirmAlert(){
		dsl.clickButton("confirm");
		Assert.assertEquals("Confirm Simples", dsl.getAlertTextAndAccept());
		Assert.assertEquals("Confirmado", dsl.getAlertTextAndAccept());
		
		dsl.clickButton("confirm");
		Assert.assertEquals("Confirm Simples", dsl.getAlertTextAndDimiss());
		Assert.assertEquals("Negado", dsl.getAlertTextAndDimiss());
	}
	
	@Test
	public void testShouldInteractWithPromptAlert(){
		dsl.clickButton("prompt");
		Assert.assertEquals("Digite um numero", dsl.getAlertText());
		dsl.writeOnAlert("12");
		Assert.assertEquals("Era 12?", dsl.getAlertTextAndAccept());
		Assert.assertEquals(":D", dsl.getAlertTextAndAccept());
	}
}
