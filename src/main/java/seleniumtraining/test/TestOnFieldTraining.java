package seleniumtraining.test;




import static seleniumtraining.core.DriverFactory.getDriver;
import static seleniumtraining.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import seleniumtraining.core.DSL;
import seleniumtraining.page.TrainingFieldPage;



public class TestOnFieldTraining {
	

	private DSL dsl;
	private TrainingFieldPage page;

	@Before
	public void starting(){
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new TrainingFieldPage();
	}
	
	@After
	public void terminate(){
		killDriver();
	}
	
	@Test
	public void testTextField(){
		page.setName("Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:nome"));
	}
	
	@Test
	public void testTextFieldDouble(){
		page.setName("Wagner");
		Assert.assertEquals("Wagner", dsl.getFieldValue("elementosForm:nome"));
		page.setName("Aquino");
		Assert.assertEquals("Aquino", dsl.getFieldValue("elementosForm:nome"));
	}
	
	@Test
	public void testShouldIntearcWithTextArea(){
		dsl.write("elementosForm:sugestoes", "teste\n\naasldjdlks\nUltima linha");
		Assert.assertEquals("teste\n\naasldjdlks\nUltima linha", dsl.getFieldValue("elementosForm:sugestoes"));
	}
	
	@Test
	public void testShouldInteractWithRadioButton(){
		page.setMaleSex();
		Assert.assertTrue(dsl.isRadioChecked("elementosForm:sexo:0"));
	}
	
	@Test
	public void testshouldInteractWithCheckbox(){
		page.setFavoriteFoodPizza();
		Assert.assertTrue(dsl.isChecked("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void testShouldInteractWithCombo(){
		page.setSchooling("2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.getCombovalue("elementosForm:escolaridade"));
	}
	
	@Test
	public void testShouldVerifyCombovalues(){
		Assert.assertEquals(8, dsl.getComboQuantityOptions("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verifyComboOption("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void testShouldVerifyComboMultiplesValues(){
		page.selectFavoriteSport("Natacao", "Corrida" , "O que eh esporte?");
		List<String> opcoesMarcadas = dsl.getComboValues("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.unselectCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.getComboValues("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void testShouldInteractWithButtons(){
		dsl.clickButton("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	public void ShouldInteractWithLinks(){
		dsl.clickLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.getText("resultado"));
	}
	
	@Test
	public void testShouldSearchtextOnpage(){
		Assert.assertEquals("Campo de Treinamento", dsl.getText(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				dsl.getText(By.className("facilAchar")));
	}
	
	@Test
	public void testJavaScript() {
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		dsl.executeJS("arguments[0].style.border = arguments[1]", element ,"solid 4px red");
	}
	
	@Test
	public void testShouldClickTableButton() {
		dsl.clickTableButton("Nome", "Maria", "Botao","elementosForm:tableUsuarios");
		
	}
	
}