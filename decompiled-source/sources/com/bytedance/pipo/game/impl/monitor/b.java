package com.bytedance.pipo.game.impl.monitor;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BaseMonitor.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: e  reason: collision with root package name */
    public static final int f10406e = 0;

    /* renamed from: f  reason: collision with root package name */
    public static final int f10407f = 1;

    /* renamed from: g  reason: collision with root package name */
    public static final int f10408g = 2;

    public static void a(JSONObject jSONObject, String str, long j2) {
        try {
            jSONObject.put(str, j2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, float f2) {
        try {
            jSONObject.put(str, f2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
