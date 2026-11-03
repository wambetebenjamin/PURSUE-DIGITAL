package com.bytedance.ttgame.module.pay.api;

import com.bytedance.pipo.payment.common.lib.c;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes6.dex */
public class CnPayInfo extends PayInfo {
    @SerializedName("app_id")
    private String appId;
    @SerializedName("body")
    private String body;
    @SerializedName("currency")
    private String currency;
    @SerializedName("exts")
    private String exts;
    @SerializedName("merchant_id")
    private String merchantId;
    @SerializedName("notify_url")
    private String notifyUrl;
    @SerializedName("out_order_no")
    private String outOrderNo;
    @SerializedName("payment_type")
    private String paymentType;
    @SerializedName("product_code")
    private String productCode;
    @SerializedName(c.am)
    private String productId;
    @SerializedName("risk_info")
    private String riskInfo;
    @SerializedName("sign")
    private String sign;
    @SerializedName("sign_type")
    private String signType;
    @SerializedName("subject")
    private String subject;
    @SerializedName("total_amount")
    private long totalAmount;
    @SerializedName("trade_time")
    private String tradeTime;
    @SerializedName("trade_type")
    private String tradeType;
    @SerializedName("uid")
    private String uid;
    @SerializedName("valid_time")
    private String validTime;
    @SerializedName("version")
    private String version;

    public String getOutOrderNo() {
        return this.outOrderNo;
    }

    public CnPayInfo setOutOrderNo(String str) {
        this.outOrderNo = str;
        return this;
    }

    public String getUid() {
        return this.uid;
    }

    public CnPayInfo setUid(String str) {
        this.uid = str;
        return this;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public CnPayInfo setMerchantId(String str) {
        this.merchantId = str;
        return this;
    }

    public String getAppId() {
        return this.appId;
    }

    public CnPayInfo setAppId(String str) {
        this.appId = str;
        return this;
    }

    public long getTotalAmount() {
        return this.totalAmount;
    }

    public CnPayInfo setTotalAmount(long j2) {
        this.totalAmount = j2;
        return this;
    }

    public String getCurrency() {
        return this.currency;
    }

    public CnPayInfo setCurrency(String str) {
        this.currency = str;
        return this;
    }

    public String getSubject() {
        return this.subject;
    }

    public CnPayInfo setSubject(String str) {
        this.subject = str;
        return this;
    }

    public String getBody() {
        return this.body;
    }

    public CnPayInfo setBody(String str) {
        this.body = str;
        return this;
    }

    public String getTradeTime() {
        return this.tradeTime;
    }

    public CnPayInfo setTradeTime(String str) {
        this.tradeTime = str;
        return this;
    }

    public String getValidTime() {
        return this.validTime;
    }

    public CnPayInfo setValidTime(String str) {
        this.validTime = str;
        return this;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public CnPayInfo setProductCode(String str) {
        this.productCode = str;
        return this;
    }

    public String getProductId() {
        return this.productId;
    }

    public CnPayInfo setProductId(String str) {
        this.productId = str;
        return this;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public CnPayInfo setPaymentType(String str) {
        this.paymentType = str;
        return this;
    }

    public String getTradeType() {
        return this.tradeType;
    }

    public CnPayInfo setTradeType(String str) {
        this.tradeType = str;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public CnPayInfo setVersion(String str) {
        this.version = str;
        return this;
    }

    public String getSignType() {
        return this.signType;
    }

    public CnPayInfo setSignType(String str) {
        this.signType = str;
        return this;
    }

    public String getSign() {
        return this.sign;
    }

    public CnPayInfo setSign(String str) {
        this.sign = str;
        return this;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public CnPayInfo setNotifyUrl(String str) {
        this.notifyUrl = str;
        return this;
    }

    public String getRiskInfo() {
        return this.riskInfo;
    }

    public CnPayInfo setRiskInfo(String str) {
        this.riskInfo = str;
        return this;
    }

    public String getExts() {
        return this.exts;
    }

    public CnPayInfo setExts(String str) {
        this.exts = str;
        return this;
    }
}
