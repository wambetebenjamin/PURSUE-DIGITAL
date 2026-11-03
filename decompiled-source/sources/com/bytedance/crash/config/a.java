package com.bytedance.crash.config;

import android.content.Context;
import com.bytedance.crash.Global;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.json.JSONObject;
/* compiled from: BitsConfig.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6323a = "slardar.properties";

    /* renamed from: b  reason: collision with root package name */
    private static final String f6324b = "release_build";

    /* renamed from: c  reason: collision with root package name */
    private static volatile a f6325c;

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, Object> f6326d = new HashMap();

    private static a c() {
        if (f6325c == null) {
            f6325c = new a();
        }
        return f6325c;
    }

    a() {
        Context c2 = Global.c();
        if (c2 == null) {
            return;
        }
        Properties properties = new Properties();
        try {
            properties.load(c2.getAssets().open(f6323a));
            for (Map.Entry entry : properties.entrySet()) {
                this.f6326d.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        } catch (Exception unused) {
        }
    }

    public static Map<String, Object> a() {
        return c().f6326d;
    }

    public static String b() {
        Object obj = a().get("release_build");
        if (obj != null) {
            String valueOf = String.valueOf(obj);
            return valueOf.length() >= 16 ? valueOf : "invalid";
        }
        return "invalid";
    }

    public static void a(JSONObject jSONObject) {
        for (Map.Entry<String, Object> entry : a().entrySet()) {
            if (!jSONObject.has(entry.getKey())) {
                try {
                    jSONObject.put(entry.getKey(), String.valueOf(entry.getValue()));
                } catch (Throwable unused) {
                }
            }
        }
    }
}
