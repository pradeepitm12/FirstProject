package com.pradeep.threadrediskafka.threadrediskafka;
import java.io.InputStream;
import java.util.Properties;

import com.pradeep.threadrediskafka.threadrediskafka.Redis_Factory.*;

import redis.clients.jedis.Jedis;
public class mainController {
public static void main (String args[])throws Exception 
{

	ReadPropertiesFile rpf=new ReadPropertiesFile();
	System.out.println(rpf.getAllProperty("redis.host"));
	Jedis  redis=null;
	Redis_Factory rf= new Redis_Factory();
	redis=rf.getInstance().getResource();
	System.out.println("This is main. For inserting data into redis");
	for(int i =0;i<Integer.parseInt(rpf.getAllProperty("loop.count"));i++)
	{
		redis.lpush("redis_demo", "*#*"+i);
	}
	//startThread();
	//SpawnThread(Integer.parseInt(rpf.getAllProperty("loop.count")));
	SpawnThread(3);
}



public static void startThread(){
	
	Demo_Thread job=new Demo_Thread();
	Thread thread =new Thread(job);
	thread.start();
	
}
public static void SpawnThread(int count)
{
	ReadPropertiesFile rpf=new ReadPropertiesFile();

Demo_Thread [] threadarray=new Demo_Thread[count];
for(Demo_Thread job:threadarray)
{
	job=new Demo_Thread();
	Thread thread=new Thread(job);
	thread.start();
	}

}

}
