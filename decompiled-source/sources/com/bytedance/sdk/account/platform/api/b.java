package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
/* compiled from: IAppAuthService.java */
/* loaded from: classes2.dex */
public interface b extends d {

    /* compiled from: IAppAuthService.java */
    /* loaded from: classes2.dex */
    public interface a extends com.bytedance.sdk.account.platform.base.a {
        void onDestroy();
    }

    /* compiled from: IAppAuthService.java */
    /* renamed from: com.bytedance.sdk.account.platform.api.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0197b {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String ACCESS_TOKEN_EXPIRATION_TIME = "access_token_expiration_time";
        public static final String AUTH_CODE = "auth_code";
        public static final String ID_TOKEN = "id_token";
        public static final String REFRESH_TOKEN = "refresh_token";
        public static final String SCOPE = "scope";
        public static final String STATE = "state";
    }

    /* compiled from: IAppAuthService.java */
    /* loaded from: classes2.dex */
    public interface c {
        public static final String ADDRESS = "address";
        public static final String EMAIL = "email";
        public static final String OFFLINE_ACCESS = "offline_access";
        public static final String OPENID = "openid";
        public static final String PHONE = "phone";
        public static final String PROFILE = "profile";
    }

    a authorize(Activity activity, com.bytedance.sdk.account.platform.base.k kVar, com.bytedance.sdk.account.platform.base.b bVar);
}
