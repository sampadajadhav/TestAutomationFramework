package TestAutomation;


import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.Assert;

public class BaseFunctionsUtility {
	
	WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

	private static Logger log = LogManager.getLogger(BaseFunctionsUtility.class.getName());

	//Calling Constructor to initialize the driver
	public BaseFunctionsUtility(WebDriver driver) {
		this.driver = driver;
	}

	public void perform(Properties prop,String keyword, String ObjectName, String ObjectType,String Value) throws Exception {
		
		switch(keyword.toUpperCase()) {
		//To perform click operation
		case "CLICK":
			try
			{
			driver.findElement(this.getObject(prop, ObjectName, ObjectType)).click();
			}
			catch (Exception e)
			{
				WebElement element = driver.findElement(this.getObject(prop, ObjectName, ObjectType));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				Thread.sleep(500);
			}
			break;
		//To perform Entertext operation
		case "ENTERTEXT":
			try {
			driver.findElement(this.getObject(prop, ObjectName, ObjectType)).sendKeys(Value);
			}
			catch (Exception e)
			{
				WebElement element = driver.findElement(this.getObject(prop, ObjectName, ObjectType));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				Thread.sleep(500);
			}		
			break;
		//To Launch URL
		case "LAUNCHURL":
			driver.get(Value);
			driver.manage().window().maximize();
			break;
		//To fetch the text
		case "GETTEXT":
			driver.findElement(this.getObject(prop, ObjectName, ObjectType)).getText();
			break;
		case "VERIFYTEXT":
			String ActValue = driver.findElement(this.getObject(prop, ObjectName, ObjectType)).getText();
			Assert.assertEquals(ActValue, Value);
			log.trace("Assert.assertEquals(ActValue, Value);");
		default : 
			break;
		}
	}
	
	public By getObject(Properties Prop,String ObjectName, String ObjectType) throws Exception {
		
	if(ObjectType.equalsIgnoreCase("XPATH")) {
		return By.xpath(Prop.getProperty(ObjectName));
	}
	else if(ObjectType.equalsIgnoreCase("CLASSNAME")) {
		return By.className(Prop.getProperty(ObjectName));
	}
	else if(ObjectType.equalsIgnoreCase("NAME")) {
		return By.name(Prop.getProperty(ObjectName));
	}
	else if(ObjectType.equalsIgnoreCase("TAGNAME")) {
		return By.tagName(Prop.getProperty(ObjectName));
	}
	else if(ObjectType.equalsIgnoreCase("LINKTEXT")) {
		return By.linkText(Prop.getProperty(ObjectName));
	}
	else if(ObjectType.equalsIgnoreCase("PARTIALLINKTEXT")) {
		return By.partialLinkText(Prop.getProperty(ObjectName));
	}
	else if(ObjectType.equalsIgnoreCase("CSSSELECTOR")) {
		return By.cssSelector(Prop.getProperty(ObjectName));
	}
	else if(ObjectType.equalsIgnoreCase("ID")) {
		return By.id(Prop.getProperty(ObjectName));
	}
	else {
		throw new Exception("Invalid Object Type");
	}
	
	
	
	
	
	
	
	}

	
}
