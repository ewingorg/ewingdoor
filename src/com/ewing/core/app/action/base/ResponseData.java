package com.ewing.core.app.action.base;

import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;

import com.google.common.collect.Lists;

/**
 * @author tanson lin
 * 
 * @create:2012-2-23
 * @description: action中向页面返回的封装信息.
 */
public class ResponseData {
	private Boolean success;
	private String retinfo;
	private String retCode;
	private Integer totalProperty;
	private List<Cookie> cookies;
	private String page;
	private Object result;
	private Integer respType;// 响应类型(由于使用前后端分离，有一些操作可能需要前端协助，比如redirect) 0
								// normal 1 forword 2 redirect
	
	public static class Cookie{
		/**
		 * 秒
		 */
		private int maxValue;
		
		private String path;
		
		private String name;
		
		private String value;
		
		public Cookie(int maxValue, String path, String name, String value) {
			super();
			this.maxValue = maxValue;
			this.path = path;
			this.name = name;
			this.value = value;
		}

		public int getMaxValue() {
			return maxValue;
		}

		public void setMaxValue(int maxValue) {
			this.maxValue = maxValue;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public ResponseData(Boolean success, Cookie cookie, Object result, Integer respType) {
		super();
		this.success = success;
		this.result = result;
		this.respType = respType;
		this.cookies = Lists.newArrayList();
		cookies.add(cookie);
	}

	/**
	 * 当respType为redirect，result 为 重定向的url
	 * 
	 * @param success
	 * @param result
	 * @param respType
	 */
	public ResponseData(Boolean success, List<Cookie> cookies, Object result, Integer respType) {
		super();
		this.success = success;
		this.result = result;
		this.respType = respType;
		this.cookies = cookies;
	}
	
	public ResponseData(Boolean success, javax.servlet.http.Cookie[] cookies, Object data,
			Integer respType) {
		if(ArrayUtils.isEmpty(cookies)){
			ResponseData(success, ListUtils.EMPTY_LIST, result, respType);
		}
		
		List<Cookie> cookieList = Lists.newArrayList();
		for(javax.servlet.http.Cookie c : cookies){
			cookieList.add(new Cookie(c.getMaxAge(), c.getPath(), c.getName(), c.getValue()));
		}
		
		ResponseData(success, cookieList, result, respType);
	}

	private void ResponseData(Boolean success2, List emptyList, Object result2,
			Integer respType2) {
		// TODO Auto-generated method stub
		
	}

	public List<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}

	public Integer getRespType() {
		return respType;
	}

	public void setRespType(Integer respType) {
		this.respType = respType;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public ResponseData() {

	}

	public String getRetinfo() {
		return retinfo;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Boolean getSuccess() {
		return success;
	}

	public Integer getTotalProperty() {
		return totalProperty;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public void setRetinfo(String retinfo) {
		this.retinfo = retinfo;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setTotalProperty(Integer totalProperty) {
		this.totalProperty = totalProperty;
	}
}
