package com.ewing.busi.resource.dto;

import com.ewing.core.jdbc.annotation.Column;

public class ProductDetailDto implements java.io.Serializable {
  /**
     * 
     */
  private static final long serialVersionUID = 1L;

  /**
   * 产品ID
   */
  @Column(fieldName="id")
  private Integer id;

  /**
   * 用户ID
   */
  @Column(fieldName="user_id")
  private Integer userId;

  /**
   * 资源名称
   */
  @Column(fieldName="name")
  private String name;

  /**
   * 模板id，web_template的主键
   */
  @Column(fieldName="template_id")
  private Integer templateId;

  /**
   * 资源分类id，category的主键
   */
  @Column(fieldName="category_id")
  private Integer categoryId;

  /**
   * 资源标签id，多个标签以","分隔
   */
  @Column(fieldName="tag_id")
  private String tagId;

  /**
   * 菜单图片链接
   */
  @Column(fieldName="image_url")
  private String imageUrl;

  /**
   * 简单描述
   */
  @Column(fieldName="short_desc")
  private String shortDesc;

  /**
   * 详细描述
   */
  @Column(fieldName="long_desc")
  private String longDesc;

  /**
   * 产品成本价
   */
  @Column(fieldName="cost")
  private Float cost;

  /**
   * 产品价格
   */
  @Column(fieldName="price")
  private Float price;

  /**
   * 产品单位
   */
  @Column(fieldName="unit")
  private String unit;

  /**
   * 产品重量
   */
  @Column(fieldName="weight")
  private String weight;

  /**
   * 赠送积分
   */
  @Column(fieldName="gift_score")
  private String giftScore;

  /**
   * 库存
   */
  @Column(fieldName="stock_num")
  private Integer stockNum;

  /**
   * 品牌ID
   */
  @Column(fieldName="brand_id")
  private Integer brandId;

  /**
   * 是否热门推荐
   */
  @Column(fieldName="is_hot")
  private Integer isHot;

  /**
   * 是否上架 0:发布中 1:上架 2:下架
   */
  @Column(fieldName="is_online")
  private Integer isOnline;
  /**
   * 是否需要物流 0:否 1:是
   */
  @Column(fieldName="need_carry")
  private Integer needCarry;

  /**
   * 是否收藏 1 收藏 0 没有收藏
   */
  @Column(fieldName="is_collect")
  private Integer isCollect;


  public Integer getIsCollect() {
    return isCollect;
  }

  public void setIsCollect(Integer isCollect) {
    this.isCollect = isCollect;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getTemplateId() {
    return templateId;
  }

  public void setTemplateId(Integer templateId) {
    this.templateId = templateId;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getTagId() {
    return tagId;
  }

  public void setTagId(String tagId) {
    this.tagId = tagId;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getShortDesc() {
    return shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

  public String getLongDesc() {
    return longDesc;
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
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getGiftScore() {
    return giftScore;
  }

  public void setGiftScore(String giftScore) {
    this.giftScore = giftScore;
  }

  public Integer getStockNum() {
    return stockNum;
  }

  public void setStockNum(Integer stockNum) {
    this.stockNum = stockNum;
  }

  public Integer getBrandId() {
    return brandId;
  }

  public void setBrandId(Integer brandId) {
    this.brandId = brandId;
  }

  public Integer getIsHot() {
    return isHot;
  }

  public void setIsHot(Integer isHot) {
    this.isHot = isHot;
  }

  public Integer getIsOnline() {
    return isOnline;
  }

  public void setIsOnline(Integer isOnline) {
    this.isOnline = isOnline;
  }

  public Integer getNeedCarry() {
    return needCarry;
  }

  public void setNeedCarry(Integer needCarry) {
    this.needCarry = needCarry;
  }
}
