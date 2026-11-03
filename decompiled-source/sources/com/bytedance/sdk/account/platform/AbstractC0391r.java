package com.bytedance.sdk.account.platform;

import android.text.TextUtils;
import com.bytedance.sdk.account.api.b;
import com.bytedance.sdk.account.platform.base.d;
import java.util.Map;
/* compiled from: OnekeyBaseAdapter.java */
/* renamed from: com.bytedance.sdk.account.platform.r  reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0391r {

    /* renamed from: b  reason: collision with root package name */
    protected static final String f12879b = "carrier_app_id";
    protected Map<String, String> l;

    public void a(Map<String, String> map) {
        this.l = map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(d dVar) {
        if (dVar instanceof com.bytedance.sdk.account.platform.base.j) {
            com.bytedance.sdk.account.platform.base.j jVar = (com.bytedance.sdk.account.platform.base.j) dVar;
            int i2 = jVar.errorType;
            int i3 = b.InterfaceC0184b.COMMON_UNKNOWN_ERROR;
            if (i2 == 1) {
                i3 = -1002;
            } else if (i2 == 2 || i2 == 3) {
                i3 = -1003;
            }
            jVar.error = i3;
            jVar.mDetailErrorCode = jVar.error;
            try {
                if (!TextUtils.isEmpty(jVar.platformErrorCode)) {
                    jVar.mDetailErrorCode = Integer.parseInt(jVar.platformErrorCode);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            jVar.mDetailErrorMsg = dVar.platformErrorDetail;
        }
    }

    public com.bytedance.sdk.account.platform.base.j getLoginErrorResponse(com.bytedance.sdk.account.api.response.ae aeVar, String str) {
        com.bytedance.sdk.account.platform.base.j jVar = new com.bytedance.sdk.account.platform.base.j();
        jVar.netType = str;
        jVar.errorType = 4;
        jVar.platformErrorCode = String.valueOf(aeVar.error);
        jVar.platformErrorMsg = aeVar.errorMsg;
        jVar.smsCodeKey = aeVar.mSmsCodeKey;
        jVar.mDetailErrorCode = aeVar.mDetailErrorCode;
        jVar.mDetailErrorMsg = aeVar.mDetailErrorMsg;
        jVar.error = aeVar.error;
        jVar.errorMsg = aeVar.errorMsg;
        if (aeVar.result != null) {
            jVar.rawResult = aeVar.result.optJSONObject("data");
        }
        if (aeVar.error == 1075) {
            jVar.mCancelApplyTime = aeVar.mCancelApplyTime;
            jVar.mCancelAvatarUrl = aeVar.mCancelAvatarUrl;
            jVar.mCancelNickName = aeVar.mCancelNickName;
            jVar.mCancelTime = aeVar.mCancelTime;
            jVar.mCancelToken = aeVar.mCancelToken;
        }
        return jVar;
    }

    public com.bytedance.sdk.account.platform.base.j getBindErrorResponse(com.bytedance.sdk.account.api.call.f<com.bytedance.sdk.account.mobile.query.o> fVar, String str) {
        com.bytedance.sdk.account.platform.base.j jVar = new com.bytedance.sdk.account.platform.base.j();
        jVar.netType = str;
        jVar.errorType = 4;
        jVar.platformErrorCode = String.valueOf(fVar.error);
        jVar.platformErrorMsg = fVar.errorMsg;
        jVar.mDetailErrorCode = fVar.mDetailErrorCode;
        jVar.mDetailErrorMsg = fVar.mDetailErrorMsg;
        jVar.error = fVar.error;
        jVar.errorMsg = fVar.errorMsg;
        if (fVar.mobileObj != null) {
            if (fVar.mobileObj.jsonResult != null) {
                jVar.rawResult = fVar.mobileObj.jsonResult.optJSONObject("data");
            }
            if (fVar.error == 1057) {
                jVar.mConfirmSwitchBindTips = fVar.mobileObj.mConfirmSwitchBindTips;
                jVar.mConfirmSwitchBindUrl = fVar.mobileObj.mConfirmSwitchBindUrl;
            }
        }
        return jVar;
    }

    public com.bytedance.sdk.account.platform.base.j getForceBindErrorResponse(com.bytedance.sdk.account.api.call.f<com.bytedance.sdk.account.mobile.query.p> fVar, String str) {
        com.bytedance.sdk.account.platform.base.j jVar = new com.bytedance.sdk.account.platform.base.j();
        jVar.netType = str;
        jVar.errorType = 4;
        jVar.platformErrorCode = String.valueOf(fVar.error);
        jVar.platformErrorMsg = fVar.errorMsg;
        jVar.mDetailErrorCode = fVar.mDetailErrorCode;
        jVar.mDetailErrorMsg = fVar.mDetailErrorMsg;
        jVar.error = fVar.error;
        jVar.errorMsg = fVar.errorMsg;
        if (fVar.mobileObj != null) {
            if (fVar.mobileObj.mResultCode != null) {
                jVar.resultCode = fVar.mobileObj.mResultCode;
            }
            if (fVar.mobileObj.jsonResult != null) {
                jVar.rawResult = fVar.mobileObj.jsonResult.optJSONObject("data");
            }
        }
        return jVar;
    }
}
