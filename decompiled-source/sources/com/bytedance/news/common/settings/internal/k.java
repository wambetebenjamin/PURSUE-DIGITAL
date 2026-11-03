package com.bytedance.news.common.settings.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* compiled from: SettingsNetworkUtils.java */
/* loaded from: classes2.dex */
public class k {
    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
