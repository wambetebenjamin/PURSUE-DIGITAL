package com.bytedance.pipo.game;

import android.content.Context;
import org.json.JSONObject;
/* compiled from: PipoConfigManager.java */
/* loaded from: classes2.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private static com.bytedance.pipo.game.api.f f10573a;

    public static void a(com.bytedance.pipo.game.api.f fVar) {
        f10573a = fVar;
    }

    public static String a() {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        return fVar != null ? fVar.a() : "";
    }

    public static String b() {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        return fVar != null ? fVar.b() : "";
    }

    public static String c() {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        return fVar != null ? fVar.c() : "";
    }

    public static String d() {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        return fVar != null ? fVar.d() : "";
    }

    public static String a(Context context) {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        return fVar != null ? fVar.a(context) : "";
    }

    public static int e() {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        if (fVar != null) {
            return fVar.e();
        }
        return 0;
    }

    public static int b(Context context) {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        if (fVar != null) {
            return fVar.b(context);
        }
        return 0;
    }

    public static int c(Context context) {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        if (fVar != null) {
            return fVar.c(context);
        }
        return 0;
    }

    public static void a(String str, String str2) {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        if (fVar != null) {
            fVar.a(str, str2);
        }
    }

    public static void a(String str, JSONObject jSONObject) {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        if (fVar != null) {
            fVar.a(str, jSONObject);
        }
    }

    public static void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        com.bytedance.pipo.game.api.f fVar = f10573a;
        if (fVar != null) {
            fVar.a(str, jSONObject, jSONObject2, jSONObject3);
        }
    }
}
