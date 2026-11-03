package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import java.util.List;
/* compiled from: IVKService.java */
/* loaded from: classes2.dex */
public interface r extends d {

    /* compiled from: IVKService.java */
    /* loaded from: classes2.dex */
    public interface a extends com.bytedance.sdk.account.platform.base.a {
    }

    /* compiled from: IVKService.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String CREATED = "created";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String PHONE_ACCESS_KEY = "phone_access_key";
        public static final String SECRET = "secret";
        public static final String USER_ID = "user_id";
    }

    /* compiled from: IVKService.java */
    /* loaded from: classes2.dex */
    public enum c {
        NOTIFY,
        FRIENDS,
        PHOTOS,
        AUDIO,
        VIDEO,
        STORIES,
        PAGES,
        STATUS,
        NOTES,
        MESSAGES,
        WALL,
        ADS,
        OFFLINE,
        DOCS,
        GROUPS,
        NOTIFICATIONS,
        STATS,
        EMAIL,
        MARKET,
        PHONE
    }

    a authorize(Activity activity, com.bytedance.sdk.account.platform.base.b bVar);

    a authorize(Activity activity, List<c> list, com.bytedance.sdk.account.platform.base.b bVar);
}
