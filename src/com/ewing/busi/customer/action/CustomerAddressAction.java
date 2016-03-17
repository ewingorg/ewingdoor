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
import com.ewing.common.constants.AjaxRespCode;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.utils.StringUtil;

/**
 * 客户收获地址相关的操作
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月3日
 *
 */
public class CustomerAddressAction extends BaseAction{
    
    private static Logger logger = Logger.getLogger(CustomerAddressAction.class);
    @Resource
    private CustomerAddressService customerAddressService;
    
    /**
     * 获取客户收货地址
     */
//    @CustomerLoginFilter
    public void queryIndexAddress() {

        try {
            LightAddressInfoReq req = getParamJson(LightAddressInfoReq.class);
            Integer page = req.getPage();
            Integer pageSize = req.getPageSize();
            checkRequired(page, "page");
            checkRequired(pageSize, "pageSize");
            

            List<LightAddressInfoResp> list = customerAddressService.queryByCusId(getLoginCusId(), page, pageSize);
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
//    @CustomerLoginFilter
    public void queryAddress(){
        try {
            AddressDetailReq req = getParamJson(AddressDetailReq.class);
            Integer id = req.getId();
            checkRequired(id, "id不能为空");

            AddressDetailResp resp = customerAddressService.findById(id, getLoginCusId());
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

            boolean result = customerAddressService.save(req, getLoginCusId());
            if(result){
              outSucResult(AjaxRespCode.CODE_SUC.code);
            }else{
              outFailResult(AjaxRespCode.CODE_ERROR);
            }
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
    
    /**
     * 删除地址
     * 
     * @author Joeson
     */
    public void delAddress(){
        try {
            AddressDetailReq req = getParamJson(AddressDetailReq.class);
            Integer id = req.getId();
            checkRequired(id, "id");

            customerAddressService.delAddress(id, getLoginCusId());
            outSucResult(StringUtil.EMPTY);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}