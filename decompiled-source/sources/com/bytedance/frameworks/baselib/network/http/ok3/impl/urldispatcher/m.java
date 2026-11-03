package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f;
import com.bytedance.retrofit2.client.Header;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: RequestHeaderDispatchStrategy.java */
/* loaded from: classes2.dex */
public class m extends f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8225a = "m";

    /* renamed from: b  reason: collision with root package name */
    private Map<String, String> f8226b;

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public void a(String str, boolean z, int i2) {
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public boolean a() {
        return false;
    }

    public m(JSONObject jSONObject) {
        super(f.a.REQUEST_HEADER_DISPATCH_STRATEGY);
        this.f8226b = new HashMap();
        if (jSONObject == null) {
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString = jSONObject.optString(next);
            if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(optString)) {
                this.f8226b.put(next, optString);
            }
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public String a(Uri uri) {
        return uri.toString();
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public void a(l lVar) {
        if (lVar.d() != null) {
            for (Map.Entry<String, String> entry : this.f8226b.entrySet()) {
                lVar.d().add(new Header(entry.getKey(), entry.getValue()));
            }
        }
    }
}
