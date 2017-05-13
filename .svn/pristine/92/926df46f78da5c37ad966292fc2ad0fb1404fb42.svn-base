package com.Section.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * 加载资源文件工具类
* @ClassName: PropUtil 
* @Description: 加载资源文件工具类 
* @author shijin
* @date 2016年12月16日 下午3:49:28 
*
 */
public class PropUtil {
	private static Logger logger = Logger.getLogger(PropUtil.class);
	public static Properties getTargetProps()
	{
		Properties props = new Properties();
		try
		{
			props.load(BigDataInterFacesClient.class.getClassLoader().getResourceAsStream("test-target.properties"));
		} catch (IOException e)
		{
			logger.error("", e);
			e.printStackTrace();
		}
		return props;
	}
	public static Properties getJDBCProps()
	{
		Properties props = new Properties();
		try
		{
			props.load(BigDataInterFacesClient.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e)
		{
			logger.error("", e);
			e.printStackTrace();
		}
		return props;
	}
}
