package controlebancario;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class TesteCampoTreinamento {
	@Test
	public  void testTextField() {
	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Antonio");
		assertEquals("Antonio",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.quit();
	
	}

	@Test
	public  void shoudlInteractTextArea() {
		String target = "Writing";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(target);
		assertEquals(target,driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		driver.quit();
	
	}
	
	@Test
	public  void shoudlInteractRadioButton() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		driver.quit();
	
	}
	
	@Test
	public  void shoudlInteractCheckButton() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		driver.quit();
	
	}
	
	@Test
	public  void shoudlInteractComboBox() {
		String target = "Mestrado";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(3);
		//combo.selectByValue("superior");
		combo.selectByVisibleText(target);//recomendado pois é a visão do usuário
		assertEquals(target,combo.getFirstSelectedOption().getText());
		driver.quit();
	
	}
	
	@Test
	public  void shouldVerifyComboValues() {
		String target = "Mestrado";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List <WebElement> options = combo.getOptions();
		combo.selectByVisibleText(target);//recomendado pois é a visão do usuário
		assertEquals(8,options.size());
		
		Boolean searched = false;
		
		for (WebElement option:options) {
			if(option.getText().equals(target)) {
				searched = true;
				break;
			}
		}
		assertTrue(searched);
		
		driver.quit();
	
	}
	
	@Test
	public  void shouldInteractComboMultiValues() {
		String[] target = {"Natacao","Futebol","Corrida"};
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		combo.selectByVisibleText(target[0]);
		combo.selectByVisibleText(target[1]);
		combo.selectByVisibleText(target[2]);
		combo.deselectByVisibleText(target[1]);
		List <WebElement> options = combo.getAllSelectedOptions();
		assertEquals(2,options.size());
		
		driver.quit();
	
	}
	
	@Test
	public  void shouldInteractButton() {
		String[] target = {"Obrigado!"};
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("buttonSimple"));
		element.click();
		assertEquals(target[0],element.getAttribute("value"));
		
		driver.quit();
	
	}
	
	@Test
	public  void shouldInteractLink() {
		String[] target = {"Obrigado!"};
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.linkText("Voltar"));
		element.click();
		assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	
	}
	
	@Test
	public  void shouldSearchTextOnThePage() {
		String[] target = {"Obrigado!"};
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,765));		
		driver.get("file://"+ System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		//WebElement element = driver.findElement(By.linkText("Voltar"));
		//element.click();
		;
		//assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento") );
		assertEquals("Campo de Treinamento",driver.findElement(By.tagName("h3")).getText());
		assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.className("facilAchar")).getText());
		driver.quit();
	
	}

}
;