package controlebancario;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGoogle {

	@Test
	public  void test() {
	// Definir o local do driver	System.setProperty(arg0, arg1)
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		//driver.manage().window().maximize();
		driver.get("http://google.com");
		Assert.assertEquals("Google",driver.getTitle());
		driver.quit();
;	}	
}
