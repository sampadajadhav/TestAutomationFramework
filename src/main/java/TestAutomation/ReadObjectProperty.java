package TestAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObjectProperty {

	Properties Prop = new Properties();

	public Properties getObjectRepository() throws IOException {
		
		File file =  new File((System.getProperty("user.dir"))+"\\src\\main\\java\\TestAutomation\\ObjectRepo.properties");
		InputStream FIS = new FileInputStream(file);
		
		//to load all the objects in properties file 
		
		Prop.load(FIS);
		return Prop;
	}
	
	
}
