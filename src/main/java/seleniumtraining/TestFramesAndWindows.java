package seleniumtraining;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestFramesAndWindows {
	
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void starting(){
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void terminate(){
		driver.quit();
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
		driver.close();
		dsl.changeWindow("");
		dsl.write(By.tagName("textarea"), "e agora?");
	}
	
	@Test
	public void testShouldInteractwithNoTileWindows(){
		dsl.clickButton("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		dsl.changeWindow((String) driver.getWindowHandles().toArray()[1]);
		dsl.write(By.tagName("textarea"), "Deu certo?");
		dsl.changeWindow((String) driver.getWindowHandles().toArray()[0]);
		dsl.write(By.tagName("textarea"), "e agora?");
	}
	

}
