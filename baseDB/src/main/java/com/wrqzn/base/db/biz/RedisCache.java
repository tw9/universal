//package com.wrqzn.base.db.biz;
//
//
//import com.wrqzn.base.db.bean.DataSource;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * Created by WANG, RUIQING on 12/13/16
// * Twitter : @taylorwang789
// * E-mail : i@wrqzn.com
// */
//public class RedisCache {
//
//	private static Map<String,JedisPool> pools = new HashMap<>();
//
//	private static Jedis getConnect(DataSource dataSource){
//		String key = dataSource.getRedisHost()+":"+dataSource.getRedisPort() ;
//		JedisPool connectPool = pools.get(key);
//		if(null == connectPool ) {
//			JedisPool pool = new JedisPool(dataSource.getRedisHost(),dataSource.getRedisPort());
//			pools.put(key,pool);
//			return pool.getResource();
//		} else {
//			return connectPool.getResource();
//		}
//	}
//
//	public static String get(DataSource dataSource, String key){
////		Jedis redis = new Jedis(dataSource.getRedisHost(),dataSource.getRedisPort());
//		Jedis redis = getConnect(dataSource);
//		return redis.get(key);
//	}
//
//	public static void set(DataSource dataSource,String key, Object data){
//		Jedis redis = getConnect(dataSource);
//		redis.set(key, ValueUtil.toString(data));
//		redis.expire(key,dataSource.getExpireSecond());
//	}
//
//	public static void set(DataSource dataSource,String sql, String data,Integer cacheSeconds ){
//		Jedis redis = getConnect(dataSource);
//		redis.set(sql,data);
//		redis.expire(sql,cacheSeconds);
//	}
//
//	public static String get( String key){
////		Jedis redis = new Jedis(dataSource.getRedisHost(),dataSource.getRedisPort());
//		Jedis redis = getConnect(DefaultData.getDataSource());
//		return redis.get(key);
//	}
//
//	public static void set(String key, Object data){
//		Jedis redis = getConnect(DefaultData.getDataSource());
//		redis.set(key, ValueUtil.toString(data));
//		redis.expire(key,DefaultData.getDataSource().getExpireSecond());
//	}
//
//	public static void set(String sql, String data,Integer cacheSeconds ){
//		Jedis redis = getConnect(DefaultData.getDataSource());
//		redis.set(sql,data);
//		redis.expire(sql,cacheSeconds);
//	}
//
//	//刷新时间
//	public static void expire(DataSource dataSource, String sql, Integer cacheSeconds){
//		Jedis redis = getConnect(dataSource);
//		redis.expire(sql, cacheSeconds);
//	}
//
//	public static void expire(String sql, Integer cacheSeconds){
//		Jedis redis = getConnect(DefaultData.getDataSource());
//		redis.expire(sql, cacheSeconds);
//	}
//
//
//}
