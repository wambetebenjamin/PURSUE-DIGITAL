package com.bytedance.ttnet.priority;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.retrofit2.client.Request;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: RequestDropController.java */
/* loaded from: classes8.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private final Set<String> f15416a = new CopyOnWriteArraySet();

    public void a(JSONObject jSONObject) {
        this.f15416a.clear();
        JSONArray optJSONArray = jSONObject.optJSONArray("dl");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                String optString = optJSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString)) {
                    this.f15416a.add(optString);
                }
            }
        }
    }

    public void a(Set<String> set) {
        this.f15416a.clear();
        this.f15416a.addAll(set);
    }

    public void a(Request request) {
        if (i.a(this.f15416a, request.getPath())) {
            if (Logger.debug()) {
                Logger.d("RequestDropController", "check drop and match " + request.getPath());
            }
            throw new com.bytedance.frameworks.baselib.network.http.exception.h();
        }
    }

    public boolean a() {
        return this.f15416a.size() > 0;
    }
}
