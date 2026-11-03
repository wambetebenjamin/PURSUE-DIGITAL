package com.bytedance.push.utils;

import android.content.Context;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.i;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: NetworkUtils.java */
/* loaded from: classes2.dex */
public class u {
    public static boolean a(Context context) {
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

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(63);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        Matcher matcher = Pattern.compile("https?://([^/]+)/(.*)").matcher(str);
        return matcher.find() ? matcher.group(2) : str;
    }
}
