package com.bytedance.sdk.account;

import com.bytedance.sdk.account.api.call.b;
/* compiled from: CommonCallBack.java */
/* loaded from: classes2.dex */
public abstract class i<T extends com.bytedance.sdk.account.api.call.b> extends com.bytedance.sdk.account.api.call.a<T> {
    @Deprecated
    public String getCaptchaInfo(T t) {
        return null;
    }

    @Deprecated
    public boolean needShowCaptcha(T t) {
        return false;
    }

    @Deprecated
    public boolean needShowPicCaptcha(T t) {
        return false;
    }

    @Deprecated
    public boolean needShowSecureCaptcha(T t) {
        return false;
    }

    public abstract void onError(T t, int i2);

    @Deprecated
    public void onNeedCaptcha(T t, String str) {
    }

    @Deprecated
    public void onNeedSecureCaptcha(T t) {
    }

    public abstract void onSuccess(T t);

    @Override // com.bytedance.sdk.account.api.call.a
    public void onResponse(T t) {
        if (t.success) {
            onSuccess(t);
        } else if (needShowCaptcha(t)) {
            if (needShowPicCaptcha(t)) {
                onNeedCaptcha(t, getCaptchaInfo(t));
            } else {
                onError(t, t.error);
            }
        } else {
            onError(t, t.error);
        }
    }
}
