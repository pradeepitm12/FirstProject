package com.pradeep.threadrediskafka.threadrediskafka;

import java.io.InputStream;
import java.util.Properties;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisData {
	public static RedisData obj =null;

	public static void main (String args[])
	{
		Jedis jedis1=null;
		JedisPool jedisPool=null;
		try{
		Properties prop =new Properties();
		// *********taking properties file as inputstream**********
		InputStream is = RedisData.class.getResourceAsStream("/TRK.properties");
		//********loading properties file as input for property********** 
		prop.load(is);
		//********Configuring jedis pool*********
		/*manages a number of connections in a pool, using them as needed and
		 *  keeping all aspects of releasing active connections internal to 
		 *  the object, so the user does not need to worry about forgotten 
		 *  connections leaking resources.*/
		JedisPoolConfig	poolConfig =new JedisPoolConfig();
		//************ Passing value to configure redis pool ***********
		poolConfig.setMaxActive(Integer.parseInt(prop.getProperty("redis.pool.count")));
		//poolConfig.setTestOnBorrow(true);
		//poolConfig.setTestOnReturn(true);
		///*********** making one instance in jedis pool********
		jedisPool = new JedisPool(poolConfig, prop.getProperty("redis.host"),Integer.parseInt(prop.getProperty("redis.port")),Integer.parseInt(prop.getProperty("redis.timeout")), prop.getProperty("redis.auth"));
		//*********getting redis/jedis as a resource"say one redis thread from redis pool to perform out tasks"********
		 jedis1 = jedisPool.getResource();
		 
		 jedis1.hset("Hash:Pradeep", "One", "1");
		 jedis1.hset("Hash:Pradeep", "Two", "2");
		 jedis1.hset("Hash:Pradeep", "Three", "3");
		 jedis1.hget("H:pradeep:1", "1");
		 System.out.println("Redis Hash Data------"+ jedis1.hget("Hash:Pradeep", "Two"));
		 System.out.println("Redis Hash Data------"+ jedis1.hget("Hash:Pradeep", "One"));
		 
		
	
		}
		catch(Exception ex)
		{
			System.out.println("Exception is ==***==="+ ex);
		}
		finally{   
			if(jedis1!=null)
				jedisPool.returnResource(jedis1);
			System.out.println("Jedis Resource is returned");
			}
	}
}
