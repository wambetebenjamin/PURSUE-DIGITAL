package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import android.os.Bundle;
import java.util.Set;
/* compiled from: IGoogleService.java */
/* loaded from: classes2.dex */
public interface h extends com.bytedance.sdk.account.platform.api.d {
    public static final int APP_AUTH_0 = 0;
    public static final int APP_AUTH_1 = 1;
    public static final int APP_AUTH_2 = 2;

    /* compiled from: IGoogleService.java */
    /* loaded from: classes2.dex */
    public interface a extends com.bytedance.sdk.account.platform.base.a {
        void a();
    }

    /* compiled from: IGoogleService.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String DISPLAY_NAME = "display_name";
        public static final String EXPIRE_IN = "expire_in";
        public static final String ID = "id";
        public static final String ID_TOKEN = "idToken";

        /* renamed from: a  reason: collision with root package name */
        public static final String f12754a = "server_auth_code";

        /* renamed from: b  reason: collision with root package name */
        public static final String f12755b = "phone_number";

        /* renamed from: c  reason: collision with root package name */
        public static final String f12756c = "password";

        /* renamed from: d  reason: collision with root package name */
        public static final String f12757d = "profile_picture_uri";

        /* renamed from: e  reason: collision with root package name */
        public static final String f12758e = "nonce";

        /* renamed from: f  reason: collision with root package name */
        public static final String f12759f = "family_name";

        /* renamed from: g  reason: collision with root package name */
        public static final String f12760g = "given_name";

        /* renamed from: h  reason: collision with root package name */
        public static final String f12761h = "email";

        /* renamed from: i  reason: collision with root package name */
        public static final String f12762i = "granted_scopes";
    }

    /* compiled from: IGoogleService.java */
    /* loaded from: classes2.dex */
    public interface c {
        void a(int i2, String str);
    }

    /* compiled from: IGoogleService.java */
    /* loaded from: classes2.dex */
    public interface d {

        /* renamed from: a  reason: collision with root package name */
        public static final String f12763a = "filter_by_authorized_accounts";

        /* renamed from: b  reason: collision with root package name */
        public static final String f12764b = "auto_select_enabled";

        /* renamed from: c  reason: collision with root package name */
        public static final String f12765c = "nonce";
    }

    a a(Activity activity, int i2, Set<String> set, boolean z, com.bytedance.sdk.account.platform.base.b bVar);

    a a(Activity activity, Bundle bundle, com.bytedance.sdk.account.platform.base.b bVar);

    void a(Activity activity, Bundle bundle, c cVar);

    a authorize(Activity activity, int i2, com.bytedance.sdk.account.platform.base.b bVar);

    void b(Activity activity);

    boolean isGooglePlayServiceAvailable();

    void revokeAccess();
}
