package com.pradeep.threadrediskafka.threadrediskafka;

import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {
	Properties prop=null;
	
	 public ReadPropertiesFile() {
	try{
	 prop= new Properties();
	InputStream is = RedisData.class.getResourceAsStream("/TRK.properties");
	prop.load(is);
	}
	catch(Exception ex)
	{
		System.out.println("Exception"+ex);
	}
}
	
	public String getAllProperty(String data) {
		String reply=prop.getProperty(data);
		System.out.println(reply);
		return reply;
	}
	 
}
