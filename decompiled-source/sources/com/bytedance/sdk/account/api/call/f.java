package com.bytedance.sdk.account.api.call;

import com.bytedance.sdk.account.mobile.query.m;
/* compiled from: MobileApiResponse.java */
/* loaded from: classes2.dex */
public class f<T extends m> extends b {
    public T mobileObj;

    public f(boolean z, int i2, T t) {
        super(z, i2);
        if (z && t != null) {
            t.mError = 0;
            t.mErrorMsg = "";
            t.mDetailErrorCode = 0;
        }
        this.mobileObj = t;
        this.error = t.mError;
        this.errorMsg = t.mErrorMsg;
    }
}
