package com.bytedance.sdk.account.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: AccountDef.java */
/* loaded from: classes2.dex */
public interface a {

    /* compiled from: AccountDef.java */
    /* renamed from: com.bytedance.sdk.account.api.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0183a {
        public static final String BOOT = "boot";
        public static final String LOGIN = "login";
        public static final String NORMAL = "normal";
        public static final String POLLING = "polling";
        public static final String WAP_LOGIN = "wap_login";
    }

    /* compiled from: AccountDef.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface b {
    }

    /* compiled from: AccountDef.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface c {
    }

    /* compiled from: AccountDef.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface d {
    }

    /* compiled from: AccountDef.java */
    /* loaded from: classes2.dex */
    public interface e {
        public static final String CANCEL_ACCOUNT_LOGOUT = "cancel_account_logout";
        public static final String FRONTIER_LOGOUT = "frontier";
        public static final String SESSION_LOGOUT = "sdk_expired_logout";
        public static final String USER_LOGOUT = "user_logout";
    }

    /* compiled from: AccountDef.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface f {
    }

    /* compiled from: AccountDef.java */
    /* loaded from: classes2.dex */
    public interface g {
        public static final int CANCEL_ACCOUNT_LOGOUT = 2;
        public static final int FRONTIER_LOGOUT = 4;
        public static final int OTHER_LOGOUT = 3;
        public static final int SESSION_LOGOUT = 1;
        public static final int USER_LOGOUT = 0;
    }

    /* compiled from: AccountDef.java */
    /* loaded from: classes2.dex */
    public interface h {
        public static final int CHECK = 1;
        public static final int DEFAULT = -1;
        public static final int NO_CHECK = 0;
    }

    /* compiled from: AccountDef.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface i {
    }

    /* compiled from: AccountDef.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface j {
    }
}
