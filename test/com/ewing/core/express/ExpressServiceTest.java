package com.ewing.core.express;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.junit.Ignore;
import org.junit.Test;

import com.ewing.busi.customer.dao.CustomerAddressDao;
import com.ewing.busi.customer.model.CustomerAddress;
import com.ewing.common.utils.SystemPropertyUtils;
import com.ewing.core.express.service.ExpressApiService;
import com.ewing.core.express.vo.ExpressRespDto;
import com.ewing.core.factory.SpringCtx;
import com.ewing.utils.JsonUtils;

/**
 * 
 * 
 * @author Joeson Chan
 * @creation 2015年1月30日
 */
public class ExpressServiceTest {
  
  @Ignore
    @Test
    public void testJson() {
        String json = "{\"response\":{\"header\":{\"service_name\":\"express.get\",\"partner_name\":\"kuaidihelp\",\"time_stamp\":\"2013-12-04 10:00:18\", \"status\":\"success\",\"desc\":\"\"},\"body\": [\"2013-11-15 19:23:17 福建龙岩公司 的收件员 工业园已收件\", \"2013-11-15 20:30:25 福建龙岩公司 正在进行 装袋 扫描\", \"2013-11-15 20:30:25 由福建龙岩公司 发往 福建漳州中转部\", \"2013-11-15 20:35:16 由福建龙岩公司 发往 福建漳州中转部\", \"2013-11-16 04:11:39 由福建漳州中转部 发往 浙江杭州航空部\", \"2013-11-17 00:50:09 由上海中转部 发往 上海浦东中转部\", \"2013-11-17 05:16:28 由上海浦东中转部 发往 上海合庆公司\", \"2013-11-17 08:44:41 快件已到达上海合庆公司\", \"2013-11-17 09:17:12 上海合庆公司 的派件员 魏航明 正在派件\", \"2013-11-17 14:03:40 已签收,签收人是邮件签收章\"]}}";

        ExpressRespDto dto = JsonUtils.toObject(json, ExpressRespDto.class);

        System.out.println(JsonUtils.toJson(dto));
        System.out.println("=================");
    }
  
    @Test
    public void request(){
      try {
        ExpressApiService expressApiService = (ExpressApiService) SpringCtx
                .getByBeanName("expressApiService");
        
        ExpressRespDto dto = expressApiService.request("yuantong", "881142919526377939");
        
        System.out.println(dto);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
}
