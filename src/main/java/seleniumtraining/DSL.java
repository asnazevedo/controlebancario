package seleniumtraining;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	/********* TextField e TextArea ************/
	
	public void write(By by, String text){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(text);
	}

	public void write(String id, String text){
		write(By.id(id), text);
	}
	
	public String getFieldValue(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	/********* Radio e Check ************/
	
	public void clickRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioChecked(String id){
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void clickCheck(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isChecked(String id){
		return driver.findElement(By.id(id)).isSelected();
	}
	
	/********* Combo ************/
	
	public void selectCombo(String id, String target) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(target);
	}
	
	public void unselectCombo(String id, String value) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(value);
	}

	public String getCombovalue(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> getComboValues(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int getComboQuantityOptions(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verifyComboOption(String id, String opcao){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	/********* Button ************/
	
	public void clickButton(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	/********* Link ************/
	
	public void clickLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	/********* Texts ************/
	
	public String getText(By by) {
		return driver.findElement(by).getText();
	}
	
	public String getText(String id) {
		return getText(By.id(id));
	}
	
	/********* Alerts ************/
	
	public String getAlertText(){
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public String getAlertTextAndAccept(){
		Alert alert = driver.switchTo().alert();
		String value = alert.getText();
		alert.accept();
		return value;
		
	}
	
	public String getAlertTextAndDimiss(){
		Alert alert = driver.switchTo().alert();
		String value = alert.getText();
		alert.dismiss();
		return value;
		
	}
	
	public void writeOnAlert(String value) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}
	
	/********* Frames e Windows ************/
	
	public void enterFrame(String id) {
		driver.switchTo().frame(id);
	}
	
	public void leaveFrame(){
		driver.switchTo().defaultContent();
	}
	
	public void changeWindow(String id) {
		driver.switchTo().window(id);
	}
	/********** JS ***********************/
	public Object executeJS(String command , Object...param) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(command, param);
	}
	
	/**********Table*********************/
	public void clickTableButton(String searchedColumn,String value , String buttonColumn , String idTable) {
		// find column of the record
		WebElement table  = driver.findElement(By.xpath("//*[@id='"+idTable+"']"));
		int idColumn = getColumnIndex(searchedColumn, table);
		
		//find row of the record
		int idRow  = getRowIndex(value, table, idColumn);
		//find column of the button
		int idColumnButton = getColumnIndex(buttonColumn, table);
		
		//click on cell button
		WebElement cell = table.findElement(By.xpath(".//tr["+idRow+"]//td["+idColumnButton+"]"	));
		cell.findElement(By.xpath(".//input")).click();
		
	}

	private int getRowIndex(String value, WebElement table, int idColumn) {
		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr/td["+idColumn+"]"));
		int idRow = -1;
		for( int index = 0; index < rows.size();index ++) {
			if(rows.get(index).getText().equals(value)) {
				idRow = index + 1;
				break;
			}
		}
		return idRow;
	}

	private int getColumnIndex(String columnTitle, WebElement table) {
		List<WebElement> columns  =  table.findElements(By.xpath(".//th"));
		int idColumn = -1;
		for( int index = 0; index < columns.size();index ++) {
			if(columns.get(index).getText().equals(columnTitle)) {
				idColumn = index + 1;
				break;
			}
		}
		return idColumn;
	}

}