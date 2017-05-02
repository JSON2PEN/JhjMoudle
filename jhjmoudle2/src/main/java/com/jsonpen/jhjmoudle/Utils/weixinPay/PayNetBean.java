package com.jsonpen.jhjmoudle.Utils.weixinPay;

/**
 * Created by shiqiang on 2016/8/26.
 */
public class PayNetBean {

    /**
     * message : 成功
     * data : {"payJsRequestJson":{"timestamp":"1472197096","resultCode":"SUCCESS","returnCode":"SUCCESS","errCodeDes":null,"codeUrl":null,"sign":"AFA7F82879883DE3B4F42D50505EFCA1","errCode":null,"returnMsg":"OK","mchId":"1277675401","appid":"wxd43cf1d0c0397eed","prepayId":"wx20160826153815807a1ba79b0160279874","nonceStr":"y5pWE2tyAeEwRT2M","deviceInfo":null,"tradeType":"APP"}}
     * infoCode : 200
     * success : 1
     */

    private String message;
    /**
     * payJsRequestJson : {"timestamp":"1472197096","resultCode":"SUCCESS","returnCode":"SUCCESS","errCodeDes":null,"codeUrl":null,"sign":"AFA7F82879883DE3B4F42D50505EFCA1","errCode":null,"returnMsg":"OK","mchId":"1277675401","appid":"wxd43cf1d0c0397eed","prepayId":"wx20160826153815807a1ba79b0160279874","nonceStr":"y5pWE2tyAeEwRT2M","deviceInfo":null,"tradeType":"APP"}
     */

    private DataBean data;
    private int infoCode;
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(int infoCode) {
        this.infoCode = infoCode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * timestamp : 1472197096
         * resultCode : SUCCESS
         * returnCode : SUCCESS
         * errCodeDes : null
         * codeUrl : null
         * sign : AFA7F82879883DE3B4F42D50505EFCA1
         * errCode : null
         * returnMsg : OK
         * mchId : 1277675401
         * appid : wxd43cf1d0c0397eed
         * prepayId : wx20160826153815807a1ba79b0160279874
         * nonceStr : y5pWE2tyAeEwRT2M
         * deviceInfo : null
         * tradeType : APP
         */

        private PayJsRequestJsonBean payJsRequestJson;

        public PayJsRequestJsonBean getPayJsRequestJson() {
            return payJsRequestJson;
        }

        public void setPayJsRequestJson(PayJsRequestJsonBean payJsRequestJson) {
            this.payJsRequestJson = payJsRequestJson;
        }

        public static class PayJsRequestJsonBean {
            private String timestamp;
            private String resultCode;
            private String returnCode;
            private Object errCodeDes;
            private Object codeUrl;
            private String sign;
            private Object errCode;
            private String returnMsg;
            private String mchId;
            private String appid;
            private String prepayId;
            private String nonceStr;
            private Object deviceInfo;
            private String tradeType;

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getResultCode() {
                return resultCode;
            }

            public void setResultCode(String resultCode) {
                this.resultCode = resultCode;
            }

            public String getReturnCode() {
                return returnCode;
            }

            public void setReturnCode(String returnCode) {
                this.returnCode = returnCode;
            }

            public Object getErrCodeDes() {
                return errCodeDes;
            }

            public void setErrCodeDes(Object errCodeDes) {
                this.errCodeDes = errCodeDes;
            }

            public Object getCodeUrl() {
                return codeUrl;
            }

            public void setCodeUrl(Object codeUrl) {
                this.codeUrl = codeUrl;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public Object getErrCode() {
                return errCode;
            }

            public void setErrCode(Object errCode) {
                this.errCode = errCode;
            }

            public String getReturnMsg() {
                return returnMsg;
            }

            public void setReturnMsg(String returnMsg) {
                this.returnMsg = returnMsg;
            }

            public String getMchId() {
                return mchId;
            }

            public void setMchId(String mchId) {
                this.mchId = mchId;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPrepayId() {
                return prepayId;
            }

            public void setPrepayId(String prepayId) {
                this.prepayId = prepayId;
            }

            public String getNonceStr() {
                return nonceStr;
            }

            public void setNonceStr(String nonceStr) {
                this.nonceStr = nonceStr;
            }

            public Object getDeviceInfo() {
                return deviceInfo;
            }

            public void setDeviceInfo(Object deviceInfo) {
                this.deviceInfo = deviceInfo;
            }

            public String getTradeType() {
                return tradeType;
            }

            public void setTradeType(String tradeType) {
                this.tradeType = tradeType;
            }
        }
    }
}
