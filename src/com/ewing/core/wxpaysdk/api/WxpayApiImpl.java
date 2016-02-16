package com.ewing.core.wxpaysdk.api;

import java.util.Map;

import org.apache.log4j.Logger;

import com.ewing.core.wxpaysdk.api.applyrefund.vo.ApplyRefundReqParam;
import com.ewing.core.wxpaysdk.api.applyrefund.vo.ApplyRefundResDto;
import com.ewing.core.wxpaysdk.api.closeorder.vo.CloseOrderReqParam;
import com.ewing.core.wxpaysdk.api.closeorder.vo.CloseOrderResDto;
import com.ewing.core.wxpaysdk.api.downloadbill.vo.DownloadBillReqParam;
import com.ewing.core.wxpaysdk.api.downloadbill.vo.DownloadBillResDto;
import com.ewing.core.wxpaysdk.api.orderquery.vo.OrderQueryReqParam;
import com.ewing.core.wxpaysdk.api.orderquery.vo.OrderQueryResDto;
import com.ewing.core.wxpaysdk.api.payitilreport.vo.PayitilReportReqParam;
import com.ewing.core.wxpaysdk.api.payitilreport.vo.PayitilReportResDto;
import com.ewing.core.wxpaysdk.api.refundquery.vo.RefundQueryReqParam;
import com.ewing.core.wxpaysdk.api.refundquery.vo.RefundQueryResDto;
import com.ewing.core.wxpaysdk.api.unifiedorders.vo.UnifiedOrdersReqParam;
import com.ewing.core.wxpaysdk.api.unifiedorders.vo.UnifiedOrdersResDto;
import com.ewing.core.wxpaysdk.protocol.ApiClient;
import com.ewing.utils.JsonUtils;
import com.ewing.utils.MapUtils;

/**
 * 支付接口的实现
 * 
 * @author Joeson Chan<chenxuegui.cxg@alibaba-inc.com>
 * @since 2016年1月24日
 *
 */
public class WxpayApiImpl implements WxpayApi {

    private static Logger logger = Logger.getLogger(WxpayApiImpl.class);

    @Override
    public UnifiedOrdersResDto unifiedOrders(UnifiedOrdersReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, UnifiedOrdersReqParam.class);
        UnifiedOrdersResDto result = ApiClient.post(unified_order_url, paramsMap,
                UnifiedOrdersResDto.class);

        logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public OrderQueryResDto orderQuery(OrderQueryReqParam param) {

        Map<String, Object> paramsMap = MapUtils.toMap(param, OrderQueryReqParam.class);

        OrderQueryResDto result = ApiClient
                .post(order_query_url, paramsMap, OrderQueryResDto.class);

        logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public CloseOrderResDto orderQuery(CloseOrderReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, CloseOrderReqParam.class);

        CloseOrderResDto result = ApiClient
                .post(order_query_url, paramsMap, CloseOrderResDto.class);

        logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public ApplyRefundResDto applyRefund(ApplyRefundReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, ApplyRefundReqParam.class);

        ApplyRefundResDto result = ApiClient.post(order_query_url, paramsMap,
                ApplyRefundResDto.class);

        logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public RefundQueryResDto refundQuery(RefundQueryReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, RefundQueryReqParam.class);

        RefundQueryResDto result = ApiClient.post(order_query_url, paramsMap,
                RefundQueryResDto.class);

        logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public DownloadBillResDto downBill(DownloadBillReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, DownloadBillReqParam.class);

        DownloadBillResDto result = ApiClient.post(order_query_url, paramsMap,
                DownloadBillResDto.class);
        logger.info(JsonUtils.toJson(result));
        return result;
    }

    @Override
    public PayitilReportResDto payitilReport(PayitilReportReqParam param) {
        Map<String, Object> paramsMap = MapUtils.toMap(param, PayitilReportReqParam.class);

        PayitilReportResDto result = ApiClient.post(order_query_url, paramsMap,
                PayitilReportResDto.class);
        logger.info(JsonUtils.toJson(result));
        return result;
    }

}
