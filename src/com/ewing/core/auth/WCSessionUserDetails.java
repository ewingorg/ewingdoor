package com.ewing.core.auth;

public class WCSessionUserDetails extends SessionUserDetails {
  
  /**
   * wechat openId，微信平台标识用户的唯一id
   */
  private String wcOpenId;
  
  /**
   * wechat nickname
   */
  private String wcNickName;
  
  /**
   * wechat 头像地址
   */
  private String wcIcon;
  
  /**
   * wechat access_token 
   */
  private String wcST;
  
  /**
   * wechar access_token最后更新时间，也就是请求access_token 请求或者刷新时间
   */
  private Long wcSTLastUpdate;

  public String getWcOpenId() {
    return wcOpenId;
  }

  public void setWcOpenId(String wcOpenId) {
    this.wcOpenId = wcOpenId;
  }

  public String getWcNickName() {
    return wcNickName;
  }

  public void setWcNickName(String wcNickName) {
    this.wcNickName = wcNickName;
  }

  public String getWcIcon() {
    return wcIcon;
  }

  public void setWcIcon(String wcIcon) {
    this.wcIcon = wcIcon;
  }

  public String getWcST() {
    return wcST;
  }

  public void setWcST(String wcST) {
    this.wcST = wcST;
  }

  public Long getWcSTLastUpdate() {
    return wcSTLastUpdate;
  }

  public void setWcSTLastUpdate(Long wcSTLastUpdate) {
    this.wcSTLastUpdate = wcSTLastUpdate;
  }
  
  
  

}
