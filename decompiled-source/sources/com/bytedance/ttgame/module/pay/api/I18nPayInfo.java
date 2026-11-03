package com.bytedance.ttgame.module.pay.api;
/* loaded from: classes6.dex */
public class I18nPayInfo extends PayInfo {
    public static final int SUBSCRIPTION = 3;
    public static final String TAG = "I18nPayInfo";
    private int amount;
    private String basePlaneId;
    private String bizContent;
    private String currency;
    private int goodType;
    private String merchantId;
    private String offerId;
    private String offerIdToken;
    private String openId;
    private String orderId;
    private int price;
    private String productId;
    private String purchaseId;
    private String roleId;
    private String serverId;
    private String sign;
    private long startPay;
    private long timestamp;

    public long getStartPay() {
        return this.startPay;
    }

    public void setStartPay(long j2) {
        this.startPay = j2;
    }

    public String getPurchaseId() {
        return this.purchaseId;
    }

    public void setPurchaseId(String str) {
        this.purchaseId = str;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int i2) {
        this.amount = i2;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String str) {
        this.roleId = str;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String str) {
        this.serverId = str;
    }

    public String getSign() {
        return this.sign;
    }

    public I18nPayInfo setSign(String str) {
        this.sign = str;
        return this;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public I18nPayInfo setMerchantId(String str) {
        this.merchantId = str;
        return this;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public I18nPayInfo setTimestamp(long j2) {
        this.timestamp = j2;
        return this;
    }

    public String getProductId() {
        return this.productId;
    }

    public I18nPayInfo setProductId(String str) {
        this.productId = str;
        return this;
    }

    public String getBizContent() {
        return this.bizContent;
    }

    public I18nPayInfo setBizContent(String str) {
        this.bizContent = str;
        return this;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public I18nPayInfo setOrderId(String str) {
        this.orderId = str;
        return this;
    }

    public int getPrice() {
        return this.price;
    }

    public I18nPayInfo setPrice(int i2) {
        this.price = i2;
        return this;
    }

    public int getGoodType() {
        return this.goodType;
    }

    public I18nPayInfo setGoodType(int i2) {
        this.goodType = i2;
        return this;
    }

    public boolean isSubscription() {
        return this.goodType == 3;
    }

    public String getOfferIdToken() {
        return this.offerIdToken;
    }

    public void setOfferIdToken(String str) {
        this.offerIdToken = str;
    }

    public String getBasePlaneId() {
        return this.basePlaneId;
    }

    public void setBasePlaneId(String str) {
        this.basePlaneId = str;
    }

    public String getOfferId() {
        return this.offerId;
    }

    public void setOfferId(String str) {
        this.offerId = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00bd A[Catch: JSONException -> 0x00c8, TRY_LEAVE, TryCatch #0 {JSONException -> 0x00c8, blocks: (B:6:0x000f, B:8:0x0020, B:10:0x002d, B:12:0x0042, B:21:0x0091, B:23:0x00bd, B:20:0x0086, B:9:0x0027), top: B:33:0x000f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isLocalSharkBlock() {
        /*
            r10 = this;
            java.lang.String r0 = "I18nPayInfo"
            java.lang.String r1 = r10.getBizContent()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 1
            if (r1 == 0) goto Le
            return r2
        Le:
            r1 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lc8
            java.lang.String r4 = r10.getBizContent()     // Catch: org.json.JSONException -> Lc8
            r3.<init>(r4)     // Catch: org.json.JSONException -> Lc8
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lc8
            boolean r5 = r10.isSubscription()     // Catch: org.json.JSONException -> Lc8
            if (r5 == 0) goto L27
            java.lang.String r5 = "RiskInfo"
            java.lang.String r3 = r3.optString(r5)     // Catch: org.json.JSONException -> Lc8
            goto L2d
        L27:
            java.lang.String r5 = "risk_info"
            java.lang.String r3 = r3.optString(r5)     // Catch: org.json.JSONException -> Lc8
        L2d:
            r4.<init>(r3)     // Catch: org.json.JSONException -> Lc8
            java.lang.String r3 = "uid"
            java.lang.String r3 = r4.optString(r3)     // Catch: org.json.JSONException -> Lc8
            java.lang.String r5 = "did"
            java.lang.String r4 = r4.optString(r5)     // Catch: org.json.JSONException -> Lc8
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch: org.json.JSONException -> Lc8
            if (r5 != 0) goto Lc7
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: org.json.JSONException -> Lc8
            if (r5 == 0) goto L4a
            goto Lc7
        L4a:
            r5 = 0
            java.lang.String r6 = "com.bytedance.ttgame.main.internal.IMainInternalService"
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch: java.lang.Exception -> L85
            java.lang.String r7 = "getDeviceId"
            java.lang.Class[] r8 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L85
            java.lang.reflect.Method r7 = r6.getDeclaredMethod(r7, r8)     // Catch: java.lang.Exception -> L85
            com.bytedance.ttgame.library.module_manager.ModuleManager r8 = com.bytedance.ttgame.library.module_manager.ModuleManager.INSTANCE     // Catch: java.lang.Exception -> L85
            com.bytedance.ttgame.framework.module.spi.IModuleApi r6 = r8.getService(r6)     // Catch: java.lang.Exception -> L85
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L85
            java.lang.Object r6 = r7.invoke(r6, r8)     // Catch: java.lang.Exception -> L85
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Exception -> L85
            java.lang.String r7 = "com.bytedance.ttgame.main.internal.IGameSdkConfigService"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch: java.lang.Exception -> L86
            java.lang.String r8 = "getsUniqueIdLast"
            java.lang.Class[] r9 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L86
            java.lang.reflect.Method r8 = r7.getDeclaredMethod(r8, r9)     // Catch: java.lang.Exception -> L86
            com.bytedance.ttgame.library.module_manager.ModuleManager r9 = com.bytedance.ttgame.library.module_manager.ModuleManager.INSTANCE     // Catch: java.lang.Exception -> L86
            com.bytedance.ttgame.framework.module.spi.IModuleApi r7 = r9.getService(r7)     // Catch: java.lang.Exception -> L86
            java.lang.Object[] r9 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L86
            java.lang.Object r7 = r8.invoke(r7, r9)     // Catch: java.lang.Exception -> L86
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Exception -> L86
            r5 = r7
            goto L91
        L85:
            r6 = r5
        L86:
            timber.log.Timber$Tree r7 = timber.log.Timber.tag(r0)     // Catch: org.json.JSONException -> Lc8
            java.lang.String r8 = "IMainInternalService or IGameSdkConfigService not found."
            java.lang.Object[] r9 = new java.lang.Object[r1]     // Catch: org.json.JSONException -> Lc8
            r7.e(r8, r9)     // Catch: org.json.JSONException -> Lc8
        L91:
            timber.log.Timber$Tree r7 = timber.log.Timber.tag(r0)     // Catch: org.json.JSONException -> Lc8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Lc8
            r8.<init>()     // Catch: org.json.JSONException -> Lc8
            java.lang.String r9 = "local device id: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: org.json.JSONException -> Lc8
            java.lang.StringBuilder r8 = r8.append(r6)     // Catch: org.json.JSONException -> Lc8
            java.lang.String r9 = " unique id: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: org.json.JSONException -> Lc8
            java.lang.StringBuilder r8 = r8.append(r5)     // Catch: org.json.JSONException -> Lc8
            java.lang.String r8 = r8.toString()     // Catch: org.json.JSONException -> Lc8
            java.lang.Object[] r9 = new java.lang.Object[r1]     // Catch: org.json.JSONException -> Lc8
            r7.d(r8, r9)     // Catch: org.json.JSONException -> Lc8
            boolean r4 = android.text.TextUtils.equals(r6, r4)     // Catch: org.json.JSONException -> Lc8
            if (r4 == 0) goto Lc4
            boolean r0 = android.text.TextUtils.equals(r5, r3)     // Catch: org.json.JSONException -> Lc8
            if (r0 == 0) goto Lc4
            r1 = r2
        Lc4:
            r0 = r1 ^ 1
            return r0
        Lc7:
            return r2
        Lc8:
            r3 = move-exception
            timber.log.Timber$Tree r0 = timber.log.Timber.tag(r0)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r10.getBizContent()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " parse to json error:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r3 = r3.getLocalizedMessage()
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r0.e(r3, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.module.pay.api.I18nPayInfo.isLocalSharkBlock():boolean");
    }
}
