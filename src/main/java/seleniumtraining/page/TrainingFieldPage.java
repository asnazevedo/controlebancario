package seleniumtraining.page;

import org.openqa.selenium.By;

import seleniumtraining.core.BasePage;

public class TrainingFieldPage extends BasePage {



	public void setName(String name) {
		dsl.write("elementosForm:nome", name);
	}
	
	public void setSurname(String surname) {
		dsl.write("elementosForm:sobrenome",surname);
	}
	
	public void setMaleSex() {
		dsl.clickRadio("elementosForm:sexo:0");
	}
	
	public void setFemaleSex() {
		dsl.clickRadio("elementosForm:sexo:1");
	}
	
	public void setFavoriteFoodBeef() {
		dsl.clickRadio("elementosForm:comidaFavorita:0");
	}
	
	public void setFavoriteFoodPizza() {
		dsl.clickRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setFavoriteFoodVegetarian() {
		dsl.clickRadio("elementosForm:comidaFavorita:3");
	}
	
	public void setSchooling(String value) {
		dsl.selectCombo("elementosForm:escolaridade", value);
	}
	
	public void selectFavoriteSport(String ...values) {
		for(String value:values) {
			dsl.selectCombo("elementosForm:esportes", value);
		}
	}
	
	public void register() {
		dsl.clickButton("elementosForm:cadastrar");
	}
	
	public String getResult() {
		return dsl.getText(By.xpath("//*[@id='resultado']/span"));
	}
	
	public String getName() {
		return dsl.getText(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String getSurname() {
		return dsl.getText(By.xpath("//*[@id='descSobrenome']/span"));
		
	}
	
	public String getMaleSex() {
		return dsl.getText(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String getFavoriteFood() {
		return dsl.getText(By.xpath("//*[@id='descComida']/span"));
	}
	
	public String getSchooling() {
		return dsl.getText(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String getFavoriteSport() {
		return dsl.getText(By.xpath("//*[@id='descEsportes']/span"));
	}
	
}
