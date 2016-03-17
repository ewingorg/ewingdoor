package com.ewing.core.mpsdk;

import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

import com.ewing.utils.ConfigReaderUtils;

/**
 * 微信配置信息获取的相关utils
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年3月17日
 *
 */
public class WxPropertyManager {
  
  private static WxPropertyManager instance = new WxPropertyManager();
  /** 微信appId **/
  private final String APPID = "appId";
  /** 微信appId 对应的secret **/
  private final String SECRET = "appSecret";
  
  protected static ConfigReaderUtils _cr;

  static {
    try {
      _cr = new ConfigReaderUtils(ResourceUtils.getURL("classpath:config/properties/mpconf.properties").getFile());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  private WxPropertyManager(){
    
  }
  
  public static WxPropertyManager getInstance(){
    return instance;
  }
  
  /**
   * 获取微信 appId
   * @author Joeson
   */
  public String getAppId(){
    return _cr.get(APPID);
  }
  
  /**
   * 获取微信 secret
   * @author Joeson
   */
  public String getSecret(){
    return _cr.get(SECRET);
  }
  
  
  
  
  
  

}
