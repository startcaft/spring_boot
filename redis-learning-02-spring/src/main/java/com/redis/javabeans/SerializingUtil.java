package com.redis.javabeans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 希望Redis能够对JavaBean进行缓存，需要借助于JDK提供的序列化技术：
 * 1，要求被缓存的JavaBean实现序列化接口java.io.Serializable;
 * 2，自定义序列化工具类，Jedis提供对序列化的支持，它提供了字节数组byte[]作为参数的方法；
 */
public class SerializingUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SerializingUtil.class);
	
	/**
	 * 对JavaBean进行序列化操作
	 */
	public static byte[] serialize(Object source){
		{
			ByteArrayOutputStream byteOut = null;
			ObjectOutputStream objOut = null;
			try {
				byteOut = new ByteArrayOutputStream();
				objOut = new ObjectOutputStream(byteOut);
				objOut.writeObject(source);
				objOut.flush();
			} catch (IOException e) {
				logger.error(source.getClass().getName()  
		                + " serialized error !", e);  
			}
			finally {  
	            try {  
	                if (null != objOut) {  
	                	objOut.close();  
	                }  
	            }  
	            catch (IOException e) {  
	            	objOut = null;  
	            }  
	        }  
			
			return byteOut.toByteArray();  
		}
	}
	
	/**
	 * 将字节数组反序列化为JavaBean
	 */
	public static Object deserialize(byte[] source){
		{
			ObjectInputStream ObjIn = null;  
	        Object retVal = null;  
	        try {  
	            ByteArrayInputStream byteIn = new ByteArrayInputStream(source);  
	            ObjIn = new ObjectInputStream(byteIn);  
	            retVal = ObjIn.readObject();  
	        }  
	        catch (Exception e) {  
	            logger.debug("deserialized error  !", e);  
	        }  
	        finally {  
	            try {  
	                if(null != ObjIn) {  
	                    ObjIn.close();  
	                }  
	            }  
	            catch (IOException e) {  
	                ObjIn = null;  
	            }  
	        }  
	        return retVal;  
		}
	}
}
