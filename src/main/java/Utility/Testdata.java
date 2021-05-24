package Utility;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Properties;

public class Testdata {
	
	public static String appurl;
	public static String adminuser;
	public static String adminpassword;
	public static String apibaseurl;
	
	public void loadEnvironmentData(String properyFilePath) throws Exception {
		try {			
			FileReader reader=new FileReader(properyFilePath);
			Properties prop = new Properties();
			prop.load(reader);
			Enumeration<Object> keys = prop.keys();
			while( keys.hasMoreElements()) {
				String key = String.valueOf(keys.nextElement());
				Field field = this.getClass().getField(key);
				field.set(this, prop.get(key));
			}
			System.out.println(prop.getProperty("appurl"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
