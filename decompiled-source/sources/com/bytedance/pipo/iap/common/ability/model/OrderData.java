package com.bytedance.pipo.iap.common.ability.model;

import android.text.TextUtils;
import com.bytedance.bdturing.setting.g;
import com.bytedance.pipo.iap.common.ability.d;
import com.bytedance.pipo.iap.common.ability.enums.PayState;
import com.bytedance.pipo.iap.common.ability.model.api.entity.GoogleProrationMode;
import com.bytedance.pipo.iap.common.ability.model.enums.PayType;
import com.bytedance.pipo.iap.enums.a;
import com.bytedance.pipo.iap.model.AbsIapChannelOrderData;
import com.bytedance.pipo.iap.model.AbsIapProduct;
import com.bytedance.pipo.iap.model.IapPaymentMethod;
import com.bytedance.pipo.payment.common.lib.c;
import gsdk.impl.account.toutiao.f;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class OrderData extends d {
    private AbsIapChannelOrderData absIapChannelOrderData;
    private AbsIapProduct absIapProduct;
    private long channelPayDuration;
    private String channelUserId;
    private long consumeDuration;
    private a extraScene;
    private com.bytedance.pipo.iap.common.ability.interfaze.a iapObserver;
    private int iapOrderState;
    private com.bytedance.pipo.iap.common.ability.monitor.d iapPayMonitor;
    private IapPaymentMethod iapPaymentMethod;
    private boolean isValidateAsync;
    private volatile boolean mCanceled;
    private volatile boolean mFinished;
    private volatile boolean mValidated;
    private boolean orderFromOtherSystem;
    private PayState payState;
    private PayType payType;
    private final PsrOptimizeFlag psrOptimizeFlag;
    private long totalDuration;
    private int validateCount;
    private long validateDuration;

    public OrderData() {
        this.payType = PayType.NORMAL;
        this.extraScene = a.Unknown;
        this.psrOptimizeFlag = new PsrOptimizeFlag();
        this.isValidateAsync = false;
    }

    public OrderData(IapPaymentMethod iapPaymentMethod, d dVar, PayType payType) {
        this.payType = PayType.NORMAL;
        this.extraScene = a.Unknown;
        this.psrOptimizeFlag = new PsrOptimizeFlag();
        this.isValidateAsync = false;
        if (dVar != null) {
            this.pipoContext = dVar.pipoContext;
            this.productId = dVar.productId;
            this.orderId = dVar.orderId;
            this.isSubscription = dVar.isSubscription;
            this.oldSubSubscribeToken = dVar.oldSubSubscribeToken;
            this.replaceSkusProrationMode = dVar.replaceSkusProrationMode;
            this.obfuscatedAccountId = dVar.obfuscatedAccountId;
            this.obfuscatedProfileId = dVar.obfuscatedProfileId;
            this.extraPayload = dVar.extraPayload;
            this.sign = dVar.sign;
            this.merchantId = dVar.merchantId;
            this.requestHost = dVar.requestHost;
            this.countryOrRegion = dVar.countryOrRegion;
            if (dVar.payRequestParams != null) {
                this.payRequestParams = new HashMap(dVar.payRequestParams);
            } else {
                this.payRequestParams = new HashMap();
            }
            this.startPayTimeStamp = dVar.startPayTimeStamp;
            this.uid = dVar.uid;
            this.GpSkuTokenInfo = dVar.GpSkuTokenInfo;
            this.traceId = dVar.traceId;
            this.isValidateAsync = com.bytedance.pipo.payment.common.lib.support.a.a().g().b().d();
            if (!TextUtils.isEmpty(dVar.requestHost) && payType == PayType.NORMAL) {
                com.bytedance.pipo.payment.common.lib.support.a.a().g().c().e(dVar.requestHost);
            }
        }
        this.iapPaymentMethod = iapPaymentMethod;
        this.payType = payType;
    }

    public OrderData(JSONObject jSONObject) {
        this.payType = PayType.NORMAL;
        this.extraScene = a.Unknown;
        this.psrOptimizeFlag = new PsrOptimizeFlag();
        this.isValidateAsync = false;
        this.uid = jSONObject.optString("user_id");
        this.orderId = jSONObject.optString("order_id");
        this.requestHost = jSONObject.optString(g.HOST_KEY);
    }

    public String toCacheString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pipo_context", this.pipoContext);
            jSONObject.put("channelUserId", this.channelUserId);
            jSONObject.put("merchant_id", this.merchantId);
            jSONObject.put(c.H, this.uid);
            jSONObject.put(g.HOST_KEY, this.requestHost);
            jSONObject.put("orderFromOtherSystem", this.orderFromOtherSystem);
            jSONObject.put("iapPaymentMethod", this.iapPaymentMethod.channelName);
            jSONObject.put(com.bytedance.pipo.game.impl.net.a.E, this.payState.ordinal());
            jSONObject.put("mValidated", this.mValidated);
            jSONObject.put("extraPayload", this.extraPayload);
            jSONObject.put("countryOrRegion", this.countryOrRegion);
            jSONObject.put("trace_id", this.traceId);
            if (this.payRequestParams != null && !this.payRequestParams.isEmpty()) {
                JSONObject jSONObject2 = new JSONObject();
                for (String str : this.payRequestParams.keySet()) {
                    jSONObject2.put(str, this.payRequestParams.get(str));
                }
                jSONObject.put("request_param", jSONObject2.toString());
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public OrderData(String str) {
        this.payType = PayType.NORMAL;
        this.extraScene = a.Unknown;
        this.psrOptimizeFlag = new PsrOptimizeFlag();
        this.isValidateAsync = false;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            setPipoContext(jSONObject.optString("pipo_context"));
            this.channelUserId = jSONObject.optString("channelUserId");
            this.merchantId = jSONObject.optString("merchant_id");
            this.uid = jSONObject.optString(c.H);
            this.requestHost = jSONObject.optString(g.HOST_KEY);
            this.countryOrRegion = jSONObject.optString("countryOrRegion");
            this.orderFromOtherSystem = jSONObject.optBoolean("orderFromOtherSystem");
            this.mValidated = jSONObject.optBoolean("mValidated", false);
            this.traceId = jSONObject.optString("trace_id");
            try {
                this.extraPayload = jSONObject.getString("extraPayload");
            } catch (JSONException unused) {
                this.extraPayload = null;
            }
            String optString = jSONObject.optString("iapPaymentMethod");
            if (!TextUtils.isEmpty(optString)) {
                this.iapPaymentMethod = IapPaymentMethod.parse(optString);
            } else {
                this.iapPaymentMethod = IapPaymentMethod.GOOGLE;
            }
            this.payState = PayState.fromOrdinal(jSONObject.optInt(com.bytedance.pipo.game.impl.net.a.E));
            this.isValidateAsync = com.bytedance.pipo.payment.common.lib.support.a.a().g().b().d();
            String optString2 = jSONObject.optString("request_param");
            if (TextUtils.isEmpty(optString2)) {
                return;
            }
            if (this.payRequestParams == null) {
                this.payRequestParams = new HashMap();
            }
            JSONObject jSONObject2 = new JSONObject(optString2);
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.payRequestParams.put(next, jSONObject2.optString(next));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("productId", this.productId);
            jSONObject.put("orderId", this.orderId);
            jSONObject.put(f.v, this.uid);
            jSONObject.put("extraPayload", this.extraPayload);
            jSONObject.put("paymentMethod", this.iapPaymentMethod.channelName);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public AbsIapProduct getAbsIapProduct() {
        return this.absIapProduct;
    }

    public void setAbsIapProduct(AbsIapProduct absIapProduct) {
        this.absIapProduct = absIapProduct;
    }

    public AbsIapChannelOrderData getAbsIapChannelOrderData() {
        return this.absIapChannelOrderData;
    }

    public void setAbsIapChannelOrderData(AbsIapChannelOrderData absIapChannelOrderData) {
        this.absIapChannelOrderData = absIapChannelOrderData;
    }

    public PayType getPayType() {
        return this.payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public a getExtraScene() {
        if (this.payType == PayType.EXTRA) {
            return this.extraScene;
        }
        return a.Unknown;
    }

    public void setExtraScene(a aVar) {
        this.extraScene = aVar;
    }

    public IapPaymentMethod getIapPaymentMethod() {
        return this.iapPaymentMethod;
    }

    public void setIapPaymentMethod(IapPaymentMethod iapPaymentMethod) {
        this.iapPaymentMethod = iapPaymentMethod;
    }

    public boolean isOrderFromOtherSystem() {
        return TextUtils.isEmpty(this.obfuscatedAccountId) && TextUtils.isEmpty(this.obfuscatedProfileId);
    }

    public void setOrderFromOtherSystem(boolean z) {
        this.orderFromOtherSystem = z;
    }

    public String getChannelUserId() {
        return this.channelUserId;
    }

    public void setChannelUserId(String str) {
        this.channelUserId = str;
    }

    public com.bytedance.pipo.iap.common.ability.monitor.d getIapPayMonitor() {
        return this.iapPayMonitor;
    }

    public void setIapPayMonitor(com.bytedance.pipo.iap.common.ability.monitor.d dVar) {
        this.iapPayMonitor = dVar;
    }

    public boolean isValidated() {
        return this.mValidated;
    }

    public void setValidated(boolean z) {
        this.mValidated = z;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public void setCanceled(boolean z) {
        this.mCanceled = z;
    }

    public boolean isFinished() {
        return this.mFinished;
    }

    public void finish() {
        this.mFinished = true;
    }

    public int getIapOrderState() {
        return this.iapOrderState;
    }

    public void setIapOrderState(int i2) {
        this.iapOrderState = i2;
    }

    public long getTotalDuration() {
        return this.totalDuration;
    }

    public void setTotalDuration(long j2) {
        this.totalDuration = j2;
    }

    public long getChannelPayDuration() {
        return this.channelPayDuration;
    }

    public void setChannelPayDuration(long j2) {
        this.channelPayDuration = j2;
    }

    public long getValidateDuration() {
        return this.validateDuration;
    }

    public void setValidateDuration(long j2) {
        this.validateDuration = j2;
    }

    public int getValidateCount() {
        return this.validateCount;
    }

    public void setValidateCount(int i2) {
        this.validateCount = i2;
    }

    public long getConsumeDuration() {
        return this.consumeDuration;
    }

    public void setConsumeDuration(long j2) {
        this.consumeDuration = j2;
    }

    public PayState getPayState() {
        return this.payState;
    }

    public void setPayState(PayState payState) {
        this.payState = payState;
    }

    public boolean isSuccess() {
        return this.mValidated;
    }

    public com.bytedance.pipo.iap.common.ability.interfaze.a getIapObserver() {
        return this.iapObserver;
    }

    public void setIapObserver(com.bytedance.pipo.iap.common.ability.interfaze.a aVar) {
        this.iapObserver = aVar;
    }

    public PsrOptimizeFlag getPsrOptimizeFlag() {
        return this.psrOptimizeFlag;
    }

    public void useSkuLocalMock(String str) {
        this.psrOptimizeFlag.setSkuLocalMock(true);
        this.psrOptimizeFlag.setSkuLocalMockErrorCode(str);
    }

    public void useSkuQueryRetry(String str) {
        this.psrOptimizeFlag.setSkuQueryRetry(true);
        this.psrOptimizeFlag.setSkuQueryRetryErrorCode(str);
    }

    public void useLaunchFlowRetry(int i2, String str) {
        this.psrOptimizeFlag.setLaunchFlowRetry(true);
        this.psrOptimizeFlag.setLaunchFlowRetryErrorCode(String.valueOf(i2));
        this.psrOptimizeFlag.setLaunchFlowRetryScene(str);
    }

    public boolean isValidateAsync() {
        return this.isValidateAsync;
    }

    public boolean isDeferredChangeType() {
        try {
            return GoogleProrationMode.DEFERRED.name().equals(this.gpChangeType);
        } catch (Exception unused) {
            return false;
        }
    }
}
