package com.bytedance.router.net;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: SmartRouterApi.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f12216a = false;

    /* renamed from: b  reason: collision with root package name */
    private static final String f12217b = "http://10.8.163.168:6789/smart_router/config";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12218c = "http://10.8.163.168:6789/smart_router/report";

    /* renamed from: d  reason: collision with root package name */
    private static String f12219d = "https://lf.snssdk.com/smart_router/config";

    /* renamed from: e  reason: collision with root package name */
    private static String f12220e = "https://lf.snssdk.com/smart_router/report";

    /* renamed from: f  reason: collision with root package name */
    private static Map<String, String> f12221f;

    /* compiled from: SmartRouterApi.java */
    /* loaded from: classes2.dex */
    public static class a<T> {
        public static final int SUCCESS = 0;
        public int errorCode = -1;
        public T result;
    }

    /* JADX WARN: Type inference failed for: r5v12, types: [com.bytedance.router.dynamic.a, T] */
    public static a<com.bytedance.router.dynamic.a> requestConfig(Context context, com.bytedance.router.dynamic.b bVar) {
        a<com.bytedance.router.dynamic.a> aVar = new a<>();
        Map<String, String> a2 = a(context);
        if (a2 == null) {
            com.bytedance.router.util.b.e("SmartRouterApi#requestConfig commonParams is null!!!");
            return aVar;
        } else if (bVar == null) {
            com.bytedance.router.util.b.e("SmartRouterApi#requestConfig serverParam is null!!!");
            return aVar;
        } else {
            a2.put("aid", String.valueOf(bVar.getAid()));
            a2.put("device_id", String.valueOf(bVar.getDeviceId()));
            a2.put("channel", String.valueOf(bVar.getChannel()));
            b bVar2 = com.bytedance.router.net.a.get(f12219d, a2);
            if (bVar2 == null) {
                return aVar;
            }
            if (bVar2.errorCode == 200) {
                try {
                    JSONObject jSONObject = new JSONObject(bVar2.content);
                    aVar.errorCode = jSONObject.optInt("errorCode");
                    if (aVar.errorCode == 0) {
                        String b2 = b(context);
                        if (!TextUtils.isEmpty(b2)) {
                            aVar.result = new com.bytedance.router.dynamic.a(b2);
                            aVar.result.setIncMapping(com.bytedance.router.dynamic.a.parseJsonArray(jSONObject.optJSONArray("mapping")));
                            aVar.result.setRewriteMapping(com.bytedance.router.dynamic.a.parseJsonArray(jSONObject.optJSONArray("rewrite")));
                            aVar.errorCode = 0;
                        }
                        if (com.bytedance.router.util.b.isDebug()) {
                            com.bytedance.router.util.b.d("SmartRouterApi#requestConfig success, content: " + jSONObject.toString());
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                aVar.errorCode = bVar2.errorCode;
            }
            return aVar;
        }
    }

    public static boolean reportRouterConfig(Context context, com.bytedance.router.dynamic.b bVar, com.bytedance.router.dynamic.a aVar) {
        a aVar2 = new a();
        Map<String, String> a2 = a(context);
        if (a2 == null) {
            com.bytedance.router.util.b.e("SmartRouterApi#reportRouterConfig commonParams is null!!!");
            return false;
        } else if (bVar == null) {
            com.bytedance.router.util.b.e("SmartRouterApi#reportRouterConfig serverParam is null!!!");
            return false;
        } else if (aVar == null || (aVar.getIncMapping().isEmpty() && aVar.getRewriteMapping().isEmpty())) {
            com.bytedance.router.util.b.d("SmartRouterApi#reportRouterConfig there is no need to report.");
            return true;
        } else {
            a2.put("aid", String.valueOf(bVar.getAid()));
            a2.put("device_id", String.valueOf(bVar.getDeviceId()));
            a2.put("channel", String.valueOf(bVar.getChannel()));
            JSONObject jSONObject = new JSONObject();
            if (aVar != null) {
                JSONArray jSONArray = new JSONArray();
                try {
                    if (aVar.getIncMapping() != null) {
                        for (Map.Entry<String, String> entry : aVar.getIncMapping().entrySet()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("schema", entry.getKey());
                            jSONObject2.put("target", entry.getValue());
                            jSONObject2.put("type", 1);
                            jSONArray.put(jSONObject2);
                        }
                    }
                    if (aVar.getRewriteMapping() != null) {
                        for (Map.Entry<String, String> entry2 : aVar.getRewriteMapping().entrySet()) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("schema", entry2.getKey());
                            jSONObject3.put("target", entry2.getValue());
                            jSONObject3.put("type", 2);
                            jSONArray.put(jSONObject3);
                        }
                    }
                    jSONObject.put("route", jSONArray);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            b post = com.bytedance.router.net.a.post(f12220e, a2, jSONObject);
            if (200 == post.errorCode) {
                try {
                    aVar2.errorCode = new JSONObject(post.content).optInt("errorCode");
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else {
                aVar2.errorCode = post.errorCode;
            }
            com.bytedance.router.util.b.d("SmartRouterApi#reportRouterConfig result: " + (aVar2.errorCode == 0));
            return aVar2.errorCode == 0;
        }
    }

    private static Map<String, String> a(Context context) {
        Map<String, String> map = f12221f;
        if (map != null) {
            return map;
        }
        f12221f = new HashMap();
        try {
            f12221f.put("version_code", String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionCode));
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                f12221f.put("update_version_code", String.valueOf(bundle.getInt("UPDATE_VERSION_CODE")));
            }
            f12221f.put("device_platform", "android");
            f12221f.put("os_api", String.valueOf(Build.VERSION.SDK_INT));
        } catch (Exception e2) {
            f12221f = null;
            e2.printStackTrace();
        }
        return f12221f;
    }

    private static String b(Context context) {
        if (a(context) != null) {
            String str = f12221f.get("version_code");
            String str2 = f12221f.get("update_version_code");
            String str3 = TextUtils.isEmpty(str) ? "" : "" + str;
            return !TextUtils.isEmpty(str2) ? str3 + "_" + str : str3;
        }
        return "";
    }
}
