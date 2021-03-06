package com.ewing.busi.resource.model;

// Generated 2015-10-10 22:43:59 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * WebResourceSpec generated by hbm2java
 */
public class WebResourcePrice implements java.io.Serializable {

	private Integer id;
	private int resourceId;
	private String specIds;
	private Float cost;
	private Float price;
	private String giftScore;
	private Integer stockNum;
	private Integer rank;
	private String iseff;
	private Date createTime;
	private Date lastUpdate;

	public WebResourcePrice() {
	}

	public String getSpecIds() {
		return specIds;
	}

	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
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
