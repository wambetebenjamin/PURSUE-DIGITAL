package com.bytedance.sdk.bridge.js.util;

import com.just.agentweb.DefaultWebClient;
/* compiled from: StringUtil.java */
/* loaded from: classes3.dex */
public class a {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isHttpUrl(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.startsWith(DefaultWebClient.HTTP_SCHEME) || str.startsWith("https://");
    }
}
