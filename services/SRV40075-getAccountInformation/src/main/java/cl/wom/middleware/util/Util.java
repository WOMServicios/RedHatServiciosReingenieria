package cl.wom.middleware.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Util {

	public static Properties getProperties (String fileName) {
		String propertiesFile="";
		Map<String, String> env = System.getenv();
		for (Entry<String, String> envName : env.entrySet()) {
			if(envName.getKey().equals(fileName)) {
				propertiesFile = envName.getValue() + ".properties";
			}
		}
		
		Properties prop = new Properties();
      	ClassLoader loader = Thread.currentThread().getContextClassLoader();           
      	InputStream stream = loader.getResourceAsStream(propertiesFile);
      	try {
      		prop.load(stream);
      	} catch (IOException e) {
      		e.printStackTrace();
      	}
      	return prop;
	}
	
}