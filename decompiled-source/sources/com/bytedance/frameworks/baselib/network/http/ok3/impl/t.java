package com.bytedance.frameworks.baselib.network.http.ok3.impl;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: MiscConfigManager.java */
/* loaded from: classes2.dex */
public class t {

    /* renamed from: a  reason: collision with root package name */
    private static volatile t f8152a;

    /* renamed from: b  reason: collision with root package name */
    private final List<String> f8153b = new CopyOnWriteArrayList();

    public static t a() {
        if (f8152a == null) {
            synchronized (t.class) {
                if (f8152a == null) {
                    f8152a = new t();
                }
            }
        }
        return f8152a;
    }

    private t() {
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("log_header_rm_list");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                String optString = optJSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString)) {
                    arrayList.add(optString);
                }
            }
        }
        this.f8153b.clear();
        this.f8153b.addAll(arrayList);
    }

    public List<String> b() {
        return this.f8153b;
    }
}
