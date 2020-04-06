package seleniumtraining.test;
import static seleniumtraining.core.DriverFactory.getDriver;
import static seleniumtraining.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import seleniumtraining.core.BaseTest;
import seleniumtraining.page.TrainingFieldPage;



public class TestRegistration extends BaseTest{
	
	private TrainingFieldPage page;

	@Before
	public void starting(){

		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new TrainingFieldPage();
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
