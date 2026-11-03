package com.bytedance.router.interceptor;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.router.c;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: RewriteManager.java */
/* loaded from: classes2.dex */
public class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, String> f12202a = null;

    /* renamed from: b  reason: collision with root package name */
    private Object f12203b = new Object();

    public void setRewriteMap(Map<String, String> map) {
        synchronized (this.f12203b) {
            if (map == null) {
                this.f12202a = Collections.EMPTY_MAP;
            } else {
                this.f12202a = map;
            }
        }
    }

    public void addRewriteValue(String str, String str2) {
        synchronized (this.f12203b) {
            if (this.f12202a == null) {
                this.f12202a = new HashMap();
            }
            this.f12202a.put(str, str2);
        }
    }

    @Override // com.bytedance.router.interceptor.a
    public boolean onInterceptRoute(Context context, c cVar) {
        com.bytedance.router.util.b.d("RewriteManager#RouteIntent-originUrl: " + cVar.getOriginUrl());
        com.bytedance.router.util.b.d("RewriteManager#RouteIntent-outputUrl: " + cVar.getUrl());
        new c.a(cVar.getUrl()).build();
        return false;
    }

    @Override // com.bytedance.router.interceptor.a
    public boolean matchInterceptRules(c cVar) {
        Map<String, String> map;
        String originUrl = cVar.getOriginUrl();
        if (TextUtils.isEmpty(originUrl)) {
            return false;
        }
        String routeUrl = com.bytedance.router.util.c.getRouteUrl(originUrl);
        if (!TextUtils.isEmpty(routeUrl) && (map = this.f12202a) != null && map.size() != 0) {
            String str = this.f12202a.get(routeUrl);
            if (!TextUtils.isEmpty(str)) {
                cVar.setUrl(str);
                return true;
            }
        }
        return false;
    }

    public String getRewriteUrl(String str) {
        Map<String, String> map = this.f12202a;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }
}
