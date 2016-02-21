package com.ewing.core.express.vo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * 快递响应参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月21日
 */
public class ExpressRespDto {
    
    private Response response;
    
    
    class Response{
        private Header header;
        
        private List<String> body;
        
        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }



        public List<String> getBody() {
            return body;
        }



        public void setBody(List<String> body) {
            this.body = body;
        }



        class Header{
            /**
             * 接口名称
             */
            @SerializedName(value="service_name")
            private String serviceName;
            
            /**
             * 接口授权账号
             */
            @SerializedName(value="partner_name")
            private String partnerName;
            
            /**
             * 接口响应时间
             */
            @SerializedName(value="time_stamp")
            private String timeStamp;
            
            /**
             * 验证状态
             */
            private String status;
            
            /**
             * 验证说明
             */
            private String desc;

            public String getServiceName() {
                return serviceName;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }

            
            public String getPartnerName() {
                return partnerName;
            }

            public void setPartnerName(String partnerName) {
                this.partnerName = partnerName;
            }

            public String getTimeStamp() {
                return timeStamp;
            }

            public void setTimeStamp(String timeStamp) {
                this.timeStamp = timeStamp;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
