package com.bytedance.push.utils;

import android.content.Context;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.i;
/* compiled from: ProxyUtils.java */
/* loaded from: classes2.dex */
public class k {
    public static boolean isWifiProxy(Context context) {
        int port;
        String str;
        if (Build.VERSION.SDK_INT >= 14) {
            str = System.getProperty("http.proxyHost");
            String property = System.getProperty("http.proxyPort");
            if (property == null) {
                property = i.a.ERROR_CODE_UNKNOW;
            }
            port = Integer.parseInt(property);
        } else {
            String host = Proxy.getHost(context);
            port = Proxy.getPort(context);
            str = host;
        }
        return (TextUtils.isEmpty(str) || port == -1) ? false : true;
    }
}
