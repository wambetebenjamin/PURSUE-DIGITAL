package com.bytedance.ttgame.module.rn.utils;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.react.framework.NetworkUtils;
import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.sdk.account.execute.a;
import com.bytedance.ttgame.engine.network.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class RNHttpRequestUtil {

    /* loaded from: classes6.dex */
    public interface HttpRequestListener {
        void onError(Exception exc);

        void onFail(String str);

        void onSuccess(Object obj);
    }

    public static void sendRequest(String str, String str2, Map<String, String> map, Map<String, String> map2, String str3, int i2, HttpRequestListener httpRequestListener) {
        sendRequest(str, str2, map, map2, str3, i2, null, httpRequestListener);
    }

    public static void sendRequest(String str, String str2, Map<String, String> map, Map<String, String> map2, String str3, int i2, HashMap hashMap, final HttpRequestListener httpRequestListener) {
        b a2;
        if (httpRequestListener == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            httpRequestListener.onError(new Exception("url is empty"));
            return;
        }
        String str4 = TextUtils.isEmpty(str2) ? "get" : str2;
        HashMap hashMap2 = map == null ? new HashMap() : map;
        if (!a.METHOD_POST.equals(str4) && !"POST".equals(str4)) {
            String addCommonParams = addCommonParams(str, hashMap2);
            if (hashMap != null) {
                a2 = com.bytedance.ttgame.engine.api.a.a(false, false, addCommonParams, null, null, BRNManager.newInstance().getCommonParams(), String.class, null, map2, null, true, hashMap);
            } else {
                a2 = com.bytedance.ttgame.engine.api.a.a(false, false, addCommonParams, null, null, BRNManager.newInstance().getCommonParams(), String.class, null, map2, null, true);
            }
        } else if (i2 != 0) {
            String str5 = TextUtils.isEmpty(str3) ? "" : str3;
            if (hashMap != null) {
                a2 = com.bytedance.ttgame.engine.api.a.a(true, false, str, null, null, BRNManager.newInstance().getCommonParams(), String.class, null, map2, str5, true, hashMap);
            } else {
                a2 = com.bytedance.ttgame.engine.api.a.a(true, false, str, null, null, BRNManager.newInstance().getCommonParams(), String.class, null, map2, str5, true);
            }
        } else if (hashMap != null) {
            a2 = com.bytedance.ttgame.engine.api.a.a(true, true, str, null, hashMap2, BRNManager.newInstance().getCommonParams(), String.class, null, map2, null, true, hashMap);
        } else {
            a2 = com.bytedance.ttgame.engine.api.a.a(true, true, str, null, hashMap2, BRNManager.newInstance().getCommonParams(), String.class, null, map2, null, true);
        }
        try {
            a2.a(new com.bytedance.ttgame.engine.network.a<String>() { // from class: com.bytedance.ttgame.module.rn.utils.RNHttpRequestUtil.1
                @Override // com.bytedance.ttgame.engine.network.a
                public void onResponse(b<String> bVar, String str6, int i3) {
                    HttpRequestListener.this.onSuccess(str6);
                }

                @Override // com.bytedance.ttgame.engine.network.a
                public void onFailure(b<String> bVar, Throwable th) {
                    HttpRequestListener.this.onFail("network request error " + (th == null ? "unknown reason" : th.getMessage()));
                }
            });
        } catch (Exception e2) {
            httpRequestListener.onError(e2);
        }
    }

    public static String addCommonParams(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(str);
        if (sb.toString().indexOf(63) < 0) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(new Pair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        sb.append(NetworkUtils.format(arrayList, "UTF-8"));
        return sb.toString();
    }
}
