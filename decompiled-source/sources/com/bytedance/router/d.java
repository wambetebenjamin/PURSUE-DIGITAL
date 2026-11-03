package com.bytedance.router;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.router.e;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RouteManager.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private e f12173a;

    /* renamed from: b  reason: collision with root package name */
    private g f12174b;

    /* renamed from: c  reason: collision with root package name */
    private b f12175c;

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.router.interceptor.b f12176d;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.router.plugin.b f12177e;

    /* renamed from: f  reason: collision with root package name */
    private List<com.bytedance.router.plugin.a> f12178f;

    /* renamed from: g  reason: collision with root package name */
    private Context f12179g;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RouteManager.java */
    /* loaded from: classes2.dex */
    public static class a {
        public static d sInstance = new d();

        private a() {
        }
    }

    private d() {
        this.f12174b = g.a();
        this.f12173a = new e();
        this.f12175c = new b();
        this.f12176d = new com.bytedance.router.interceptor.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final d a() {
        return a.sInstance;
    }

    public Context getContext() {
        return this.f12179g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, Map<String, String> map) {
        a(context, map, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, Map<String, String> map, com.bytedance.router.dynamic.b bVar) {
        this.f12179g = context;
        this.f12173a.setRouteMap(map);
        this.f12173a.init(context, bVar, new e.a() { // from class: com.bytedance.router.d.1
            @Override // com.bytedance.router.e.a
            public void onConfigChanged(com.bytedance.router.dynamic.a aVar) {
                if (aVar == null) {
                    return;
                }
                d.this.f12173a.updateMapping(aVar.getIncMapping());
                d.this.a(aVar.getRewriteMapping());
                com.bytedance.router.net.c.reportRouterConfig(d.this.f12179g, d.this.f12173a.getServerParam(), aVar);
            }
        });
        this.f12175c.addInterceptor(this.f12176d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.f12173a.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(g gVar) {
        this.f12174b = gVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(com.bytedance.router.interceptor.a aVar) {
        this.f12175c.addInterceptor(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Map<String, String> map) {
        this.f12176d.setRewriteMap(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2) {
        this.f12176d.addRewriteValue(str, str2);
    }

    public void open(Context context, c cVar) {
        c a2;
        if (!b(cVar) || this.f12175c.a(context, cVar) || (a2 = a(cVar)) == null) {
            return;
        }
        String targetClass = this.f12173a.getTargetClass(a2.getUrl());
        if (TextUtils.isEmpty(targetClass)) {
            if (!b(a2.getUrl())) {
                com.bytedance.router.util.b.w("RouteManager#open cannot find the routeUri with " + a2.getUrl());
                return;
            }
            targetClass = this.f12173a.getTargetClass(a2.getUrl());
        }
        com.bytedance.router.route.d a3 = a(a2, targetClass);
        if (a3 == null) {
            com.bytedance.router.util.b.e("RouteManager#Not support the route with url：" + a2.getUrl());
            return;
        }
        try {
            a3.open(context);
        } catch (Exception e2) {
            com.bytedance.router.util.b.e("Please check the scheme and its mapping class!!!");
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            com.bytedance.router.util.b.w("SmartRoute#url is null!!!");
            return false;
        } else if (!com.bytedance.router.util.c.isLegalUrl(str)) {
            com.bytedance.router.util.b.w("SmartRoute#url is illegal and url is " + str);
            return false;
        } else {
            String realRouteUrl = com.bytedance.router.util.c.getRealRouteUrl(str);
            String rewriteUrl = this.f12176d.getRewriteUrl(realRouteUrl);
            if (!TextUtils.isEmpty(rewriteUrl)) {
                realRouteUrl = rewriteUrl;
            }
            String scheme = Uri.parse(realRouteUrl).getScheme();
            if (TextUtils.isEmpty(scheme)) {
                scheme = Uri.parse(str).getScheme();
            }
            if (!this.f12174b.supportScheme(scheme)) {
                com.bytedance.router.util.b.w("SmartRouter not supports this scheme: " + scheme);
                return false;
            } else if (!this.f12174b.supportScheme(scheme)) {
                com.bytedance.router.util.b.w("SmartRouter not supports this scheme: " + scheme);
                return false;
            } else {
                String targetClass = this.f12173a.getTargetClass(realRouteUrl);
                if (TextUtils.isEmpty(targetClass) && b(realRouteUrl)) {
                    targetClass = this.f12173a.getTargetClass(realRouteUrl);
                }
                return !TextUtils.isEmpty(targetClass) || this.f12175c.a(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Intent a(Context context, c cVar) {
        c a2;
        if (!b(cVar) || this.f12175c.a(context, cVar) || (a2 = a(cVar)) == null) {
            return null;
        }
        String targetClass = this.f12173a.getTargetClass(a2.getUrl());
        if (TextUtils.isEmpty(targetClass)) {
            if (!b(a2.getUrl())) {
                com.bytedance.router.util.b.w("RouteManager#buildIntent cannot find the routeUri with " + a2.getUrl());
                return null;
            }
            targetClass = this.f12173a.getTargetClass(a2.getUrl());
        }
        a2.getExtra().setComponent(new ComponentName(context.getPackageName(), targetClass));
        return a2.getExtra();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(com.bytedance.router.plugin.b bVar) {
        this.f12177e = bVar;
    }

    private c a(c cVar) {
        String url = cVar.getUrl();
        if (!com.bytedance.router.util.c.isLegalUrl(url)) {
            com.bytedance.router.util.b.e("RouteManager#RouteIntent-outputUrl is illegal and url is : " + url);
            return null;
        }
        cVar.setUrl(com.bytedance.router.util.c.completeUrl(this.f12174b.getScheme(), url));
        com.bytedance.router.util.b.d("RouteManager#processRouteIntent originUlr: " + cVar.getOriginUrl());
        com.bytedance.router.util.b.d("RouteManager#processRouteIntent outputUlr: " + cVar.getUrl());
        return cVar;
    }

    private boolean b(c cVar) {
        String url = cVar.getUrl();
        if (com.bytedance.router.util.c.isLegalUrl(url, this.f12174b)) {
            return true;
        }
        com.bytedance.router.util.b.e("RouteManager#checkLegality originUrl is illegal: " + url + ". \n" + this.f12174b.toString());
        return false;
    }

    private com.bytedance.router.route.d a(c cVar, String str) {
        com.bytedance.router.route.b createRoute = com.bytedance.router.route.e.createRoute(cVar.getUrl(), str, this.f12174b);
        if (createRoute != null) {
            createRoute.init(cVar, this.f12173a);
        }
        return createRoute;
    }

    private synchronized boolean b(String str) {
        com.bytedance.router.plugin.b bVar = this.f12177e;
        boolean z = false;
        if (bVar == null) {
            com.bytedance.router.util.b.w("if you use urls in plugins, you must set SupportPluginCallback to support plugins.");
            return false;
        }
        if (this.f12178f == null) {
            this.f12178f = bVar.initPlugins();
        }
        List<com.bytedance.router.plugin.a> list = this.f12178f;
        if (list == null && list.size() == 0) {
            return false;
        }
        Iterator<com.bytedance.router.plugin.a> it = this.f12178f.iterator();
        while (it.hasNext()) {
            com.bytedance.router.plugin.a next = it.next();
            if (next.containsUrl(str)) {
                this.f12177e.loadPlugin(next, str);
                if (this.f12173a.loadModuleMapping(next.getName())) {
                    z = true;
                }
                it.remove();
            }
        }
        return z;
    }
}
