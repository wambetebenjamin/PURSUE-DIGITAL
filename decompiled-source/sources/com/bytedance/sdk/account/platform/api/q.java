package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
/* compiled from: ITwitterService.java */
/* loaded from: classes2.dex */
public interface q extends d {

    /* compiled from: ITwitterService.java */
    /* loaded from: classes2.dex */
    public interface a extends com.bytedance.sdk.account.platform.base.a {
    }

    /* compiled from: ITwitterService.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String AUTH_TOKEN = "auth_token";
        public static final String ID = "id";
        public static final String SECRET = "secret";
        public static final String USER_NAME = "user_name";
    }

    a authorize(Activity activity, com.bytedance.sdk.account.platform.base.b bVar);
}
