package com.bytedance.sdk.account;

import com.bytedance.sdk.account.api.call.f;
import com.bytedance.sdk.account.mobile.query.m;
/* compiled from: NeedCaptchaCallBack.java */
/* loaded from: classes2.dex */
public abstract class p<T extends com.bytedance.sdk.account.api.call.f<K>, K extends com.bytedance.sdk.account.mobile.query.m> extends i<T> {
    public abstract void onError(T t, int i2);

    public abstract void onSuccess(T t);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.sdk.account.i
    @Deprecated
    public /* bridge */ /* synthetic */ String getCaptchaInfo(com.bytedance.sdk.account.api.call.b bVar) {
        return getCaptchaInfo((p<T, K>) ((com.bytedance.sdk.account.api.call.f) bVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.sdk.account.i
    @Deprecated
    public /* bridge */ /* synthetic */ boolean needShowCaptcha(com.bytedance.sdk.account.api.call.b bVar) {
        return needShowCaptcha((p<T, K>) ((com.bytedance.sdk.account.api.call.f) bVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.sdk.account.i
    @Deprecated
    public /* bridge */ /* synthetic */ boolean needShowPicCaptcha(com.bytedance.sdk.account.api.call.b bVar) {
        return needShowPicCaptcha((p<T, K>) ((com.bytedance.sdk.account.api.call.f) bVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.sdk.account.i
    @Deprecated
    public /* bridge */ /* synthetic */ boolean needShowSecureCaptcha(com.bytedance.sdk.account.api.call.b bVar) {
        return needShowSecureCaptcha((p<T, K>) ((com.bytedance.sdk.account.api.call.f) bVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.sdk.account.i
    public /* bridge */ /* synthetic */ void onError(com.bytedance.sdk.account.api.call.b bVar, int i2) {
        onError((p<T, K>) ((com.bytedance.sdk.account.api.call.f) bVar), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.sdk.account.i
    public /* bridge */ /* synthetic */ void onSuccess(com.bytedance.sdk.account.api.call.b bVar) {
        onSuccess((p<T, K>) ((com.bytedance.sdk.account.api.call.f) bVar));
    }

    @Deprecated
    public final boolean needShowCaptcha(T t) {
        if (t == null || t.mobileObj == 0) {
            return false;
        }
        return t.mobileObj.isNeedShowCaptcha();
    }

    @Deprecated
    public final boolean needShowPicCaptcha(T t) {
        if (t == null || t.mobileObj == 0) {
            return false;
        }
        return t.mobileObj.isNeedShowPicCaptcha();
    }

    @Deprecated
    public final boolean needShowSecureCaptcha(T t) {
        if (t == null || t.mobileObj == 0) {
            return false;
        }
        return t.mobileObj.isNeedShowSecureCaptcha();
    }

    @Deprecated
    public final String getCaptchaInfo(T t) {
        if (t == null || t.mobileObj == 0) {
            return null;
        }
        return t.mobileObj.mErrorCaptcha;
    }
}
