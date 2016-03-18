package com.ewing.utils;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

/**
 * 
 * 属性工具类
 * 
 * @author tansonlam
 * @createDate 2016年2月2日
 * 
 */
public class PropertyUtils {
    private static Logger logger = Logger.getLogger(PropertyUtils.class);
    
    private static ConfigReaderUtils reader;

    static {
      try {
        reader = new ConfigReaderUtils(ResourceUtils.getURL("classpath:config/properties/system.properties").getFile());
      } catch (FileNotFoundException e) {
        logger.error(e.getMessage(), e);
      }
    }
    

    public static String getProperty(String key) {
        try {
          return reader.get(key);
        } catch (Exception e) {
            logger.error("fail to get property for " + key, e);
            return null;
        }
    }

    public static void main(String[] args) {
        String hosts = PropertyUtils.getProperty("redis.host");
        System.out.println(hosts);
    }
}