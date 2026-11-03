package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: StaticDispatchStrategy.java */
/* loaded from: classes2.dex */
public class h extends f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8205a = "h";

    /* renamed from: b  reason: collision with root package name */
    private Map<String, String> f8206b;

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public void a(l lVar) {
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public void a(String str, boolean z, int i2) {
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public boolean a() {
        return false;
    }

    public h(JSONObject jSONObject) {
        super(f.a.STATIC_DISPATCH_STRATEGY);
        this.f8206b = new HashMap();
        if (jSONObject == null) {
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString = jSONObject.optString(next);
            if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(optString)) {
                this.f8206b.put(next, optString);
            }
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public String a(Uri uri) {
        String host = uri.getHost();
        String uri2 = uri.toString();
        String str = this.f8206b.get(host);
        if (TextUtils.isEmpty(str)) {
            return uri2;
        }
        if (Logger.debug()) {
            Logger.d(f8205a, "replace host " + str + " for " + host);
        }
        return uri2.replaceFirst(host, str);
    }
}
