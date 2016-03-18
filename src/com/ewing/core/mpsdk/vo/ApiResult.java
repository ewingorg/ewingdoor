package com.ewing.core.mpsdk.vo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.util.ResourceUtils;

import com.ewing.utils.ConfigReaderUtils;

/**
 * 封装微信api返回结果, 输出实体类
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class ApiResult {

    private static final Log log = Logs.get();

    private static ConfigReaderUtils reader;

    static {
        try {
            reader = new ConfigReaderUtils(ResourceUtils.getURL("classpath:config/properties/ErrorCode.properties").getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       
    }

    private Map<String, Object> content;
    private String json;
    private Integer errCode;
    private String errMsg;
    private String errCNMsg;

    public ApiResult(String json) {
        this.json = json;
        this.content = Json.fromJson(Map.class, json);
        this.errCode = (Integer) this.content.get("errcode");
        this.errMsg = (String) this.content.get("errmsg");
        this.errCNMsg = this.errCode == null ? "请求成功." : reader.get(String.valueOf(this.errCode));

        if (log.isInfoEnabled()) {
            log.infof("Wechat api result: %s", json);
            log.infof("%s", this.getErrCNMsg());
        }
    }

    public static ApiResult create(String json) {
        return new ApiResult(json);
    }

    public Object get(String key) {
        return content.get(key);
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public String getJson() {
        return json;
    }

    public Integer getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg == null ? "Unknow Error!" : this.errMsg;
    }

    public String getErrCNMsg() {
        return this.errCNMsg == null ? "未知错误!" : this.errCNMsg;
    }

    public boolean isSuccess() {
        return (this.errCode == null || this.errCode.intValue() == 0);
    }

}
