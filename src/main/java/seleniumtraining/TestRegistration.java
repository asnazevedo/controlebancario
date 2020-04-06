package seleniumtraining;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRegistration {
	
	private WebDriver driver;
	private TrainingFieldPage page;

	@Before
	public void starting(){
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new TrainingFieldPage(driver);
	}
	
	@After
	public void terminate(){
		driver.quit();
	}

	@Test
	public void testShouldRealizeSuccessfullyRegistration(){
		page.setName("Wagner");
		page.setSurname("Costa");
		page.setMaleSex();
		page.setFavoriteFoodPizza();
		page.setSchooling("Mestrado");
		page.selectFavoriteSport("Natacao");
		page.register();
		
		Assert.assertEquals("Cadastrado!",page.getResult());
		Assert.assertEquals("Wagner",page.getName());
		Assert.assertEquals("Costa", page.getSurname());
		Assert.assertEquals("Masculino",page.getMaleSex());
		Assert.assertEquals("Pizza", page.getFavoriteFood());
		Assert.assertEquals("mestrado",page.getSchooling());
		Assert.assertEquals("Natacao",page.getFavoriteSport());
	}
	
}
