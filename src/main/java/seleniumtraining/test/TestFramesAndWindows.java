package seleniumtraining.test;
import static seleniumtraining.core.DriverFactory.getDriver;
import static seleniumtraining.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import seleniumtraining.core.DSL;



public class TestFramesAndWindows {
	
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
	public void testShouldInteractWithFrames(){
		dsl.enterFrame("frame1");
		dsl.clickButton("frameButton");
		String msg = dsl.getAlertTextAndAccept();
		Assert.assertEquals("Frame OK!", msg);

		dsl.leaveFrame();
		dsl.write("elementosForm:nome", msg);
	}
	
	@Test
	public void testShouldInteractWithOcultedFrame() {
		dsl.enterFrame("frame2");
		dsl.clickButton("frameButton");
		String msg = dsl.getAlertTextAndAccept();
		Assert.assertEquals("Frame OK!", msg);
	}
	
	@Test
	public void testShouldInteractWithwindows(){
		dsl.clickButton("buttonPopUpEasy");
		dsl.changeWindow("Popup");
		dsl.write(By.tagName("textarea"), "Deu certo?");
		getDriver().close();
		dsl.changeWindow("");
		dsl.write(By.tagName("textarea"), "e agora?");
	}
	
	@Test
	public void testShouldInteractwithNoTileWindows(){
		dsl.clickButton("buttonPopUpHard");
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		dsl.changeWindow((String) getDriver().getWindowHandles().toArray()[1]);
		dsl.write(By.tagName("textarea"), "Deu certo?");
		dsl.changeWindow((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.write(By.tagName("textarea"), "e agora?");
	}
	

}
