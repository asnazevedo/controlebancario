package seleniumtraining.test;

import static seleniumtraining.core.DriverFactory.getDriver;
import static seleniumtraining.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import seleniumtraining.core.BaseTest;
import seleniumtraining.core.DSL;
import seleniumtraining.page.TrainingFieldPage;



@RunWith(Parameterized.class)
public class TestRegisterRules extends BaseTest{


	private DSL dsl;
	private TrainingFieldPage page;
	
	@Parameter
	public String name;
	@Parameter(value=1)
	public String surname;
	@Parameter(value=2)
	public String sex;
	@Parameter(value=3)
	public List<String> foods;
	@Parameter(value=4)
	public String[] sports;
	@Parameter(value=5)
	public String msg;

	@Before
	public void starting(){

		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new TrainingFieldPage();
	}
	
	@Parameters
	public static Collection<Object[]> getColletion(){
		return Arrays.asList(new Object[][] {
			{"","","",Arrays.asList(""),new String[]{},"Nome eh obrigatorio"},
			{"Wagner", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Wagner", "Costa", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Wagner", "Costa", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Wagner", "Costa", "Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void testShouldValidateRules(){
		page.setName(name);
		page.setSurname(surname);
		if(sex=="Masculino") {page.setMaleSex();}
		if(sex=="Feminino") {page.setFemaleSex();}
		if(foods.contains("Carne")) { page.setFavoriteFoodBeef(); }
		if(foods.contains("Pizza")) { page.setFavoriteFoodPizza(); }
		if(foods.contains("Vegetariano")) { page.setFavoriteFoodVegetarian(); }
		page.selectFavoriteSport(sports);
		page.register();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.getAlertTextAndAccept());
	}
}
