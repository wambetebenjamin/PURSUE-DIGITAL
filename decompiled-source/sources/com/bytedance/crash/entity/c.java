package com.bytedance.crash.entity;

import com.bytedance.crash.util.q;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: CustomLong.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6648a = "custom_long";

    public static void a(JSONObject jSONObject, String str, JSONArray jSONArray) {
        if (jSONArray.length() != 0) {
            q.a(q.a(jSONObject, f6648a), str, jSONArray);
        }
    }
}
