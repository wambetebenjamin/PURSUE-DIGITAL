package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
/* compiled from: IKakaoService.java */
/* loaded from: classes2.dex */
public interface j extends d {

    /* compiled from: IKakaoService.java */
    /* loaded from: classes2.dex */
    public interface a extends com.bytedance.sdk.account.platform.base.a {
    }

    /* compiled from: IKakaoService.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String EXPIRES_IN = "expires_in";
    }

    a authorize(Activity activity, com.bytedance.sdk.account.platform.base.b bVar);

    void onDestroy();
}
