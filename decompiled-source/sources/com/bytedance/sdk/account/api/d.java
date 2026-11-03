package com.bytedance.sdk.account.api;

import android.text.TextUtils;
import com.bytedance.sdk.account.api.a;
/* compiled from: BDAccountEvent.java */
/* loaded from: classes2.dex */
public class d {
    public static final int EVENT_ACCOUNT_LOGOUT = 1;
    public static final int EVENT_ACCOUNT_REFRESH = 0;
    public static final int EVENT_ACCOUNT_SESSION_EXIPIRED = 2;
    public int logoutScene;
    public String sessionDropMessage;
    public int sessionDropProtocolType;
    public boolean success;
    public final int type;

    public d(int i2) {
        this.type = i2;
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -2085722047:
                if (str.equals(a.e.CANCEL_ACCOUNT_LOGOUT)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1242784755:
                if (str.equals("frontier")) {
                    c2 = 1;
                    break;
                }
                break;
            case -355378050:
                if (str.equals(a.e.USER_LOGOUT)) {
                    c2 = 2;
                    break;
                }
                break;
            case 90865289:
                if (str.equals("sdk_expired_logout")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return 2;
            case 1:
                return 4;
            case 2:
                return 0;
            case 3:
                return 1;
            default:
                return 3;
        }
    }
}
