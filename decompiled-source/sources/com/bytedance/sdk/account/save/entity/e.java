package com.bytedance.sdk.account.save.entity;
/* compiled from: LoginType.java */
/* loaded from: classes2.dex */
public class e {
    public static final int SERVER_TYPE_AUTH = 2;
    public static final int SERVER_TYPE_EMAIL = 4;
    public static final int SERVER_TYPE_MOBILE = 1;
    public static final int SERVER_TYPE_PASSWORD = 3;
    public static final int TYPE_AUTH = 6;
    @Deprecated
    public static final int TYPE_LATEST_ONE_KEY = 7;
    public static final int TYPE_MAIL = 4;
    public static final int TYPE_PHONE_CODE = 2;
    public static final int TYPE_PHONE_ONE_KEY = 1;
    public static final int TYPE_PHONE_PASSWORD = 3;
    public static final int TYPE_USERNAME_PASSWORD = 5;

    public static boolean isInLoginType(int i2) {
        return i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7;
    }

    public static int serverType(int i2) {
        int i3 = 1;
        if (i2 != 1 && i2 != 2) {
            i3 = 3;
            if (i2 != 3 && i2 != 5) {
                return i2 == 6 ? 2 : 4;
            }
        }
        return i3;
    }
}
