package com.pradeep.threadrediskafka.threadrediskafka;
import java.io.InputStream;
import java.util.Properties;

import com.pradeep.threadrediskafka.threadrediskafka.Redis_Factory.*;
import com.pradeep.threadrediskafka.threadrediskafka.ReadPropertiesFile.*;
import redis.clients.jedis.Jedis;

public class Demo_Thread implements Runnable{
	Redis_Factory redis_Factory=new Redis_Factory();
	Jedis redis=null;
	ReadPropertiesFile rpf=new ReadPropertiesFile();
	int loopcount=Integer.parseInt(rpf.getAllProperty("loop.count"));
	
	public void run() {
		
		redis =redis_Factory.getInstance().getResource();
		for(int i =0;i<loopcount;i++)
		{
		String redis_reply=redis.lpop("redis_demo");
		System.out.println("Reply from Redis is "+redis_reply);
		}
	}
	
	
}
