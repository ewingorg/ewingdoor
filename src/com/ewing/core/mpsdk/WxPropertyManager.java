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
  /** 微信服务器验证的token **/
  private final String TOKEN = "token";
  /** 获取微信号 **/
  private final String MPID = "mpId";
  
  
  private static ConfigReaderUtils reader;

  static {
    try {
      reader = new ConfigReaderUtils(ResourceUtils.getURL("classpath:config/properties/mpconf.properties").getFile());
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
    return reader.get(APPID);
  }
  
  /**
   * 获取微信 secret
   * @author Joeson
   */
  public String getSecret(){
    return reader.get(SECRET);
  }
  
  /**
   * 获取token
   * @author Joeson
   */
  public String getToken(){
    return reader.get(TOKEN);
  }
  
  /**
   * 获取微信号
   * @author Joeson
   */
  public String getMpId(){
    return reader.get(MPID);
  }
  
  
  
  
  
  

}
