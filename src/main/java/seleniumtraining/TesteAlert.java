package seleniumtraining;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void starting(){
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void terminate(){
		driver.quit();
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
