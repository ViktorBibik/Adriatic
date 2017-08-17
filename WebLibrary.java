package helpers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

public class WebLibrary {

	//common methods
	public static void goToUrl (WebDriver driver, String url){
		driver.get(url);
	}
	
	public static void maximazeWindow (WebDriver driver){
		driver.manage().window().maximize();
	}
	
	public static void setImplicitlyWait (WebDriver driver, long seconds ){
		
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	
	public static void setUpDriver(WebDriver driver, long seconds){
		maximazeWindow(driver);
		setImplicitlyWait(driver, seconds);
	}
	
	
	public static void waitElementPresent(WebDriver driver,long seconds,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element)); 
	}
	//action mouse
	public static void moveMouse (Actions actions,WebDriver driver,WebElement element){
		actions = new Actions(driver);
		actions.moveToElement(element);
		actions.build().perform();
	}
	
	public static void moveAndClickMouse (Actions actions, WebDriver driver,WebElement element){
		actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click().build().perform();
	}
	
	
	//good method
	
	public static boolean isElementPresent (WebDriver driver, String xpath) throws Exception{
		
		List <WebElement> elements = driver.findElements(By.xpath(xpath));
		int numberOfElements = elements.size();
		
		if (numberOfElements==0){
			return false;
		}else if (numberOfElements==1){
			return true;
			
		}else {
			throw new Exception("There are more than 1 elemnt with current locator on the page");
		}
	
	}
	
	
	public static void clickWebElement(WebElement element){
		element.click();
	}
	
	public static void switchToFrame(WebDriver driver, WebElement elem){
		driver.switchTo().frame(elem);
	}
	
	public static void switchToDefaultFrame (WebDriver driver){
		driver.switchTo().defaultContent();
	}
	

	
	public static boolean isElementEnable (WebElement element){
		
		boolean Enable =  element.isEnabled();
		return Enable;
	}
	
	public static boolean isElementDisplayed (WebElement element){
		
		boolean Displayed = element.isDisplayed();
		return Displayed;
		
	}
	
	public static boolean isElementSelected(WebElement element){
	
		boolean selected = element.isSelected();
		return selected;
		
	}
	
	//text Field
	public static String getTextFromWebElement (WebElement element) {
		
		String text = element.getText();
		return text;
	}
	
	
	//text edit field
	
	public static void setTextIntoWebElement(WebElement element, String text ){
		element.click();
		clearTextField(element);
		element.sendKeys(text);
	}
	
	public static void clearTextField(WebElement element){
		element.clear();
	}
	//link
	public static void clickLink(WebElement link){
		link.click();
	}
	
	//button
	public static void clickButton(WebElement button){
		button.click();
	}
	
	//radio button
	
	public static boolean getRadioButtonState (WebElement radioButton){
		
		  if (radioButton.isSelected()) {
			   
			  // System.out.println("Element state is selected");
			   return true;
			  } else {
			   
			  // System.out.println("Element state isn't selected");
			   return false;
			  }
			 }
	
	public static void setRadioButtonState (WebElement radioButton, String expectedState){
		
		boolean actualState = getRadioButtonState(radioButton);
		
		 if (actualState == true && expectedState=="selected" ) {
			  System.out.println("RadioButton is selected");  
			  
		  } else if (actualState == false && expectedState=="selected"){
			  clickWebElement(radioButton);
			  System.out.println("radioButton is selected after click");
		  }
		
		
	}
	
	//tab

	//checkbox
	
	public static boolean getCheckBoxState (WebElement checkbox){
		
		if (checkbox.isSelected()){
			return true;
		}
		
		else {
			return false;
		}
	}
	
	
	public static void setCheckBoxState (WebElement checkbox, String expectedState) {
		
		boolean actualState = getCheckBoxState(checkbox);
		
		  if (actualState == true && expectedState=="checked" ) {
			  System.out.println("checkbox is checked");
			  
		  } else if (actualState == true && expectedState == "unchecked"){
			  //checkbox.click();
			  clickWebElement(checkbox);
			  System.out.println("checkbox is checked");
			  
		  } else if (actualState == false && expectedState=="checked"){
			  clickWebElement(checkbox);
			  System.out.println("checkbox is checked");
		  }
		  else if (actualState == false && expectedState=="unchecked"){
			  System.out.println("checkbox is unchecked");
		 
		 }
	}


	public static void verifyCheckBoxState (WebElement checkbox, String expectedState) throws Exception{
		
		boolean actualState = getCheckBoxState(checkbox);
		boolean expectedStateBoolean;
		
		if (expectedState=="checked"){
			expectedStateBoolean = true;
			
		}else if (expectedState=="unchecked"){
			expectedStateBoolean = false;
		}
		else {
			throw new Exception ("Expected state has to be checked or uchecked only.");
		}
		
		if (actualState == expectedStateBoolean){
			 System.out.println("checkbox is" + expectedState +"as expected.");
			 
		} else {
			throw new Exception ("checkbox is not" + expectedState +
					"as expected. Actual value is"+actualState);
		}
		
	
	}


	//tab
	
	
	//dropdown
	
	
	
	//combobox
	public static void cklickComboBox(WebElement comboBox){
		comboBox.click();
	}
	
/*	public void selectComboValue(String elementId, String value) {
	    Select selectBox = new Select(findElement(By.id(elementId)));
	    select.SelectByText("Option3");
	    selectBox.selectByValue(value);
}*/

}