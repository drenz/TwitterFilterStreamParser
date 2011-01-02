package net.davidrenz.twitter.parser.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import org.apache.log4j.Logger;

public class PropertyUtil {

	private static Properties properties = null;
	static final Logger log = Logger.getLogger(PropertyUtil.class);
	public static Properties getProperyFile(){
		
		if ( properties == null){
			properties = new Properties();
			try {
				FileInputStream fis = new FileInputStream("properties/runtime.properties");
				properties.load(fis);
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
		
		return properties;
		
	}
	
}
