package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import java.util.Collection;
/* compiled from: IFacebookService.java */
/* loaded from: classes2.dex */
public interface g extends d {

    /* compiled from: IFacebookService.java */
    /* loaded from: classes2.dex */
    public interface a extends com.bytedance.sdk.account.platform.base.a {
    }

    /* compiled from: IFacebookService.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String PUBLIC = "public_profile";
    }

    /* compiled from: IFacebookService.java */
    /* loaded from: classes2.dex */
    public interface c {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String EXPIRES_IN = "expires_in";
        public static final String USER_ID = "user_id";
    }

    a loginWithPublishPermissions(Activity activity, Collection<String> collection, com.bytedance.sdk.account.platform.base.b bVar);

    a loginWithReadPermissions(Activity activity, Collection<String> collection, com.bytedance.sdk.account.platform.base.b bVar);
}
