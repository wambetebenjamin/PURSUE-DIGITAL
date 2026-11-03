package com.ttnet.org.chromium.net.impl;

import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public class CronetUrlRequestMapping {
    public static final String TAG = "CronetUrlRequestMapping";
    private static Map<String, CronetUrlRequest> sRequestMapping = new ConcurrentHashMap();

    public static void AddRequest(String str, CronetUrlRequest cronetUrlRequest) {
        if (TextUtils.isEmpty(str) || cronetUrlRequest == null) {
            return;
        }
        sRequestMapping.put(str, cronetUrlRequest);
    }

    public static void RemoveRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        sRequestMapping.remove(str);
    }

    public static CronetUrlRequest GetRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return sRequestMapping.get(str);
    }
}
