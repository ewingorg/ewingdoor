package com.ewing.busi.customer.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.customer.dto.AddressAddDto;
import com.ewing.busi.customer.dto.AddressDetailReq;
import com.ewing.busi.customer.dto.AddressDetailResp;
import com.ewing.busi.customer.dto.LightAddressInfoReq;
import com.ewing.busi.customer.dto.LightAddressInfoResp;
import com.ewing.busi.customer.service.CustomerAddressService;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.util.StringUtil;

/**
 * 客户收获地址相关的操作
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年2月3日
 *
 */
public class CustomerAddressAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(CustomerAddressAction.class);
    @Resource
    private CustomerAddressService customerAddressService;
    
    /********************@TODO 注意后期的判断是否是对应的客户 ********************/

    /**
     * 获取客户收货地址
     */
    public void queryIndexAddress() {

        try {
            LightAddressInfoReq req = getParamJson(LightAddressInfoReq.class);
            Integer cusId = req.getCusId();
            checkRequired(cusId, "cusId");

            List<LightAddressInfoResp> list = customerAddressService.queryByCusId(cusId);
            outSucResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常！");
        }
    }
    
    /**
     * 根据id查找地址
     * 
     * @author Joeson
     */
    public void queryAddress(){
        try {
            AddressDetailReq req = getParamJson(AddressDetailReq.class);
            Integer id = req.getId();
            checkRequired(id, "id不能为空");

            AddressDetailResp resp = customerAddressService.findById(id);
            outSucResult(resp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outFailResult("内部异常！");
        }
        
    }
    
    
    /**
     * 添加地址
     * 
     * @author Joeson
     */
    public void saveAddress(){
        try {
            AddressAddDto req = getParamJson(AddressAddDto.class);
            checkRequired(req, "req");

            customerAddressService.save(req);
            outSucResult(StringUtil.EMPTY);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    /**
     * 设置地址为默认的，则其他的地址就不是默认了
     * 
     * @author Joeson
     */
    public void setDefaultAddress(){
        try {
            AddressDetailReq req = getParamJson(AddressDetailReq.class);
            Integer id = req.getId();
            checkRequired(id, "id");

            customerAddressService.setDefaultAddress(id);
            outSucResult(StringUtil.EMPTY);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
