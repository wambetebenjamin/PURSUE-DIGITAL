package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
/* compiled from: ITiktokService.java */
/* loaded from: classes2.dex */
public interface o extends com.bytedance.sdk.account.platform.api.d {

    /* compiled from: ITiktokService.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final String REQURE_MOBILE_BIND = "require_tel_num_bind";
    }

    /* compiled from: ITiktokService.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String AUTH_CODE = "auth_code";
        public static final String EXTRA = "extras";
        public static final String GRANTED_PERMISSION = "granted_permission";
        public static final String STATE = "state";

        /* renamed from: a  reason: collision with root package name */
        public static final String f12767a = "granted_fields";

        /* renamed from: b  reason: collision with root package name */
        public static final String f12768b = "ttop_version";

        /* renamed from: c  reason: collision with root package name */
        public static final String f12769c = "code_verifier";

        /* renamed from: d  reason: collision with root package name */
        public static final String f12770d = "redirect_url";
    }

    /* compiled from: ITiktokService.java */
    @Deprecated
    /* loaded from: classes2.dex */
    public interface c {
        public static final String MOBILE = "mobile";
        public static final String MOBILE_ALERT = "mobile_alert";
        public static final String USER_INFO = "user_info";
    }

    /* compiled from: ITiktokService.java */
    /* loaded from: classes2.dex */
    public interface d {

        /* renamed from: a  reason: collision with root package name */
        public static final int f12771a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f12772b = 2;
    }

    boolean authorize(Activity activity, com.bytedance.sdk.account.platform.base.k kVar, com.bytedance.sdk.account.platform.base.b bVar);

    boolean isAppSupportAuthorization(Activity activity);

    void onDestroy();
}
