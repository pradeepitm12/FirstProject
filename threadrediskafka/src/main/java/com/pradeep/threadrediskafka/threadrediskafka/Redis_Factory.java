package com.pradeep.threadrediskafka.threadrediskafka;

import java.io.InputStream;
import java.util.Properties;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis_Factory {
	
	private static Redis_Factory instance =null;
	Jedis jedis1=null;
	static JedisPool jedisPool=null;
	Properties prop;
	
	public Redis_Factory(){
		try{
		 prop =new Properties();
		InputStream is = RedisData.class.getResourceAsStream("/TRK.properties");
		prop.load(is);
		JedisPoolConfig	poolConfig =new JedisPoolConfig();
		poolConfig.setMaxActive(Integer.parseInt(prop.getProperty("redis.pool.count")));
		jedisPool = new JedisPool(poolConfig, prop.getProperty("redis.host"),Integer.parseInt(prop.getProperty("redis.port")),Integer.parseInt(prop.getProperty("redis.timeout")), prop.getProperty("redis.auth"));
		}
		catch(Exception ex)
		{
			System.out.println("The exception is** " + ex);
		}
	} 
	public static JedisPool getInstance(){
		JedisPool result = jedisPool;
		return result;
	}
	
}
