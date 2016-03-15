package com.ewing.busi.resource.dto;

// Generated 2015-10-10 22:43:59 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import com.ewing.core.jdbc.annotation.Column;

/**
 * WebResource generated by hbm2java
 */
public class WebResourceDto implements java.io.Serializable {

  @Column(fieldName = "id")
  private Integer id;
  @Column(fieldName = "user_id")
  private Integer userId;
  @Column(fieldName = "name")
  private String name;
  @Column(fieldName = "template_id")
  private Integer templateId;
  @Column(fieldName = "category_id")
  private Integer categoryId;
  @Column(fieldName = "tag_id")
  private String tagId;
  @Column(fieldName = "image_url")
  private String imageUrl;
  @Column(fieldName = "short_desc")
  private String shortDesc;
  @Column(fieldName = "long_desc")
  private String longDesc;
  @Column(fieldName = "cost")
  private Float cost;
  @Column(fieldName = "price")
  private Float price;
  @Column(fieldName = "unit")
  private String unit;
  @Column(fieldName = "weight")
  private String weight;
  @Column(fieldName = "gift_score")
  private String giftScore;
  @Column(fieldName = "stock_num")
  private Integer stockNum;
  @Column(fieldName = "brand_id")
  private Integer brandId;
  @Column(fieldName = "promotion_type")
  private Integer promotionType;
  @Column(fieldName = "is_online")
  private Integer isOnline;
  @Column(fieldName = "is_hot")
  private Integer isHot;
  @Column(fieldName = "need_carry")
  private Integer needCarry;
  @Column(fieldName = "remark")
  private String remark;
  @Column(fieldName = "search_keyword")
  private String searchKeyword;
  @Column(fieldName = "page_title")
  private String pageTitle;
  @Column(fieldName = "page_keyword")
  private String pageKeyword;
  @Column(fieldName = "page_desc")
  private String pageDesc;
  @Column(fieldName = "iseff")
  private String iseff;
  @Column(fieldName = "create_time")
  private Date createTime;
  @Column(fieldName = "last_update")
  private Date lastUpdate;

  public WebResourceDto() {}

  public WebResourceDto(String name, String imageUrl, String shortDesc, String longDesc,
      String iseff, Date createTime, Date lastUpdate) {
    this.name = name;
    this.imageUrl = imageUrl;
    this.shortDesc = shortDesc;
    this.longDesc = longDesc;
    this.iseff = iseff;
    this.createTime = createTime;
    this.lastUpdate = lastUpdate;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getTemplateId() {
    return this.templateId;
  }

  public void setTemplateId(Integer templateId) {
    this.templateId = templateId;
  }

  public Integer getCategoryId() {
    return this.categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getTagId() {
    return this.tagId;
  }

  public void setTagId(String tagId) {
    this.tagId = tagId;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getShortDesc() {
    return this.shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

  public String getLongDesc() {
    return this.longDesc;
  }

  public void setLongDesc(String longDesc) {
    this.longDesc = longDesc;
  }

  public Float getCost() {
    return cost;
  }

  public void setCost(Float cost) {
    this.cost = cost;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getWeight() {
    return this.weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getGiftScore() {
    return this.giftScore;
  }

  public void setGiftScore(String giftScore) {
    this.giftScore = giftScore;
  }

  public Integer getStockNum() {
    return this.stockNum;
  }

  public void setStockNum(Integer stockNum) {
    this.stockNum = stockNum;
  }

  public Integer getBrandId() {
    return this.brandId;
  }

  public void setBrandId(Integer brandId) {
    this.brandId = brandId;
  }

  public Integer getPromotionType() {
    return this.promotionType;
  }

  public void setPromotionType(Integer promotionType) {
    this.promotionType = promotionType;
  }

  public Integer getIsOnline() {
    return this.isOnline;
  }

  public void setIsOnline(Integer isOnline) {
    this.isOnline = isOnline;
  }



  public Integer getIsHot() {
    return isHot;
  }

  public void setIsHot(Integer isHot) {
    this.isHot = isHot;
  }

  public Integer getNeedCarry() {
    return this.needCarry;
  }

  public void setNeedCarry(Integer needCarry) {
    this.needCarry = needCarry;
  }

  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getSearchKeyword() {
    return this.searchKeyword;
  }

  public void setSearchKeyword(String searchKeyword) {
    this.searchKeyword = searchKeyword;
  }

  public String getPageTitle() {
    return this.pageTitle;
  }

  public void setPageTitle(String pageTitle) {
    this.pageTitle = pageTitle;
  }

  public String getPageKeyword() {
    return this.pageKeyword;
  }

  public void setPageKeyword(String pageKeyword) {
    this.pageKeyword = pageKeyword;
  }

  public String getPageDesc() {
    return this.pageDesc;
  }

  public void setPageDesc(String pageDesc) {
    this.pageDesc = pageDesc;
  }

  public String getIseff() {
    return this.iseff;
  }

  public void setIseff(String iseff) {
    this.iseff = iseff;
  }

  public Date getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastUpdate() {
    return this.lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}