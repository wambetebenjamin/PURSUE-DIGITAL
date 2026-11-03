package com.bytedance.pipo.iap.common.ability;

import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.pipo.iap.common.ability.model.DecryptResult;
import com.bytedance.pipo.iap.common.ability.model.PIPOContextDecrypter;
import com.bytedance.pipo.iap.common.ability.model.PIPOContextHelper;
import com.bytedance.pipo.iap.common.ability.model.api.entity.GoogleProrationMode;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: IapRequest.java */
/* loaded from: classes2.dex */
public class d {
    public String countryOrRegion;
    public String extraPayload;
    public boolean isSubscription;
    public String merchantId;
    public String obfuscatedAccountId;
    public String obfuscatedProfileId;
    public String oldSubSubscribeToken;
    public String orderId;
    public String pipoContext;
    public String productId;
    public String requestHost;
    public String sign;
    public long startPayTimeStamp;
    public String uid;
    public int replaceSkusProrationMode = GoogleProrationMode.SERVER_ERROR.getValue();
    public String gpChangeType = "";
    public String GpSkuTokenInfo = "";
    public String traceId = "";
    public Map<String, String> payRequestParams = new HashMap();
    public String pipoContextDecryptErrorMessage = "";

    public void setPipoContext(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.pipoContext = str;
        PIPOContextHelper.PIPOContext parse = PIPOContextHelper.parse(str);
        if (parse != null) {
            this.productId = parse.ChannelSkuId;
            this.merchantId = parse.MerchantId;
            this.uid = parse.MerchantUserId;
            this.orderId = parse.PipoTradeOrderId;
            this.isSubscription = !TextUtils.equals(PIPOContextHelper.PIPOContext.TradeType_One_Off, parse.TradeProduct);
            this.requestHost = parse.requestHost;
            this.sign = parse.requestSign;
            if (parse.GP_changeType != null) {
                this.replaceSkusProrationMode = GoogleProrationMode.parseGoogleChangeType(parse);
            }
            this.oldSubSubscribeToken = parse.GP_oldSubSubscribeToken;
            this.obfuscatedAccountId = parse.GP_obfuscatedAccountId;
            this.obfuscatedProfileId = parse.GP_obfuscatedProfileId;
            this.countryOrRegion = parse.CountryOrRegion;
            this.traceId = parse.traceId;
        }
    }

    /* compiled from: IapRequest.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        d f10614a = new d();

        public a a(String str) {
            return a(PIPOContextDecrypter.parsePipoContext(str));
        }

        public a a(DecryptResult decryptResult) {
            PIPOContextHelper.PIPOContext parse;
            String decryptedPipoContext = decryptResult.getDecryptedPipoContext();
            this.f10614a.pipoContext = decryptedPipoContext;
            this.f10614a.pipoContextDecryptErrorMessage = decryptResult.getErrMsg();
            if (!TextUtils.isEmpty(this.f10614a.pipoContext) && (parse = PIPOContextHelper.parse(decryptedPipoContext)) != null) {
                this.f10614a.productId = parse.ChannelSkuId;
                this.f10614a.merchantId = parse.MerchantId;
                this.f10614a.uid = parse.MerchantUserId;
                this.f10614a.orderId = parse.PipoTradeOrderId;
                this.f10614a.isSubscription = !TextUtils.equals(PIPOContextHelper.PIPOContext.TradeType_One_Off, parse.TradeProduct);
                this.f10614a.requestHost = parse.requestHost;
                this.f10614a.sign = parse.requestSign;
                if (parse.GP_changeType != null) {
                    this.f10614a.gpChangeType = parse.GP_changeType;
                    this.f10614a.replaceSkusProrationMode = GoogleProrationMode.parseGoogleChangeType(parse);
                }
                this.f10614a.oldSubSubscribeToken = parse.GP_oldSubSubscribeToken;
                this.f10614a.obfuscatedAccountId = parse.GP_obfuscatedAccountId;
                this.f10614a.obfuscatedProfileId = parse.GP_obfuscatedProfileId;
                this.f10614a.countryOrRegion = parse.CountryOrRegion;
                this.f10614a.GpSkuTokenInfo = parse.GpSkuTokenInfo;
                this.f10614a.traceId = parse.traceId;
            }
            return this;
        }

        public a a(PIPOContextHelper.PIPOContext pIPOContext) {
            if (pIPOContext != null) {
                this.f10614a.productId = pIPOContext.ChannelSkuId;
                this.f10614a.merchantId = pIPOContext.MerchantId;
                this.f10614a.uid = pIPOContext.MerchantUserId;
                this.f10614a.orderId = pIPOContext.PipoTradeOrderId;
                this.f10614a.isSubscription = !TextUtils.equals(PIPOContextHelper.PIPOContext.TradeType_One_Off, pIPOContext.TradeProduct);
                this.f10614a.requestHost = pIPOContext.requestHost;
                this.f10614a.sign = pIPOContext.requestSign;
                if (pIPOContext.GP_changeType != null) {
                    this.f10614a.gpChangeType = pIPOContext.GP_changeType;
                    this.f10614a.replaceSkusProrationMode = GoogleProrationMode.parseGoogleChangeType(pIPOContext);
                }
                this.f10614a.oldSubSubscribeToken = pIPOContext.GP_oldSubSubscribeToken;
                this.f10614a.obfuscatedAccountId = pIPOContext.GP_obfuscatedAccountId;
                this.f10614a.obfuscatedProfileId = pIPOContext.GP_obfuscatedProfileId;
                this.f10614a.countryOrRegion = pIPOContext.CountryOrRegion;
                this.f10614a.GpSkuTokenInfo = pIPOContext.GpSkuTokenInfo;
                this.f10614a.traceId = pIPOContext.traceId;
            }
            return this;
        }

        public a b(String str) {
            this.f10614a.extraPayload = str;
            return this;
        }

        public a a(Map<String, String> map) {
            if (map != null) {
                this.f10614a.payRequestParams = new HashMap(map);
            }
            return this;
        }

        public d a() {
            this.f10614a.startPayTimeStamp = SystemClock.uptimeMillis();
            return this.f10614a;
        }
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public boolean isSubscription() {
        return this.isSubscription;
    }

    public void setSubscription(boolean z) {
        this.isSubscription = z;
    }

    public String getOldSubSubscribeToken() {
        return this.oldSubSubscribeToken;
    }

    public void setOldSubSubscribeToken(String str) {
        this.oldSubSubscribeToken = str;
    }

    public int getReplaceSkusProrationMode() {
        return this.replaceSkusProrationMode;
    }

    public void setReplaceSkusProrationMode(int i2) {
        this.replaceSkusProrationMode = i2;
    }

    public String getObfuscatedAccountId() {
        return this.obfuscatedAccountId;
    }

    public void setObfuscatedAccountId(String str) {
        this.obfuscatedAccountId = str;
    }

    public String getObfuscatedProfileId() {
        return this.obfuscatedProfileId;
    }

    public void setObfuscatedProfileId(String str) {
        this.obfuscatedProfileId = str;
    }

    public String getExtraPayload() {
        return this.extraPayload;
    }

    public void setExtraPayload(String str) {
        this.extraPayload = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public String getRequestHost() {
        return this.requestHost;
    }

    public void setRequestHost(String str) {
        this.requestHost = str;
    }

    public Map<String, String> getPayRequestParams() {
        return this.payRequestParams;
    }

    public void setPayRequestParams(Map<String, String> map) {
        this.payRequestParams = map;
    }

    public long getStartPayTimeStamp() {
        return this.startPayTimeStamp;
    }

    public void setStartPayTimeStamp(long j2) {
        this.startPayTimeStamp = j2;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getGpSkuDetailsToken() {
        JSONObject parseGpSkuTokenInfo = parseGpSkuTokenInfo();
        return parseGpSkuTokenInfo == null ? "" : parseGpSkuTokenInfo.optString("SkuDetailsToken");
    }

    public String getGpOfferIdToken() {
        JSONObject parseGpSkuTokenInfo = parseGpSkuTokenInfo();
        return parseGpSkuTokenInfo == null ? "" : parseGpSkuTokenInfo.optString("OfferIdToken");
    }

    private JSONObject parseGpSkuTokenInfo() {
        if (TextUtils.isEmpty(this.GpSkuTokenInfo)) {
            return null;
        }
        try {
            return new JSONObject(this.GpSkuTokenInfo);
        } catch (Exception unused) {
            return null;
        }
    }
}
