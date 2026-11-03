package com.bytedance.router;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.bytedance.router.net.c;
import java.util.HashMap;
import java.util.Map;
/* compiled from: RouteMapper.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12187a = "com.bytedance.router.generator.mapping.SmartrouterMapping$$%s";

    /* renamed from: b  reason: collision with root package name */
    private static final String f12188b = "smartrouter_config";

    /* renamed from: c  reason: collision with root package name */
    private Map<String, String> f12189c;

    /* renamed from: e  reason: collision with root package name */
    private Context f12191e;

    /* renamed from: f  reason: collision with root package name */
    private com.bytedance.router.dynamic.b f12192f;

    /* renamed from: h  reason: collision with root package name */
    private a f12194h;

    /* renamed from: d  reason: collision with root package name */
    private Map<String, String> f12190d = null;

    /* renamed from: g  reason: collision with root package name */
    private Object f12193g = new Object();

    /* compiled from: RouteMapper.java */
    /* loaded from: classes2.dex */
    public interface a {
        void onConfigChanged(com.bytedance.router.dynamic.a aVar);
    }

    public e() {
        this.f12189c = null;
        this.f12189c = new HashMap();
    }

    public Map<String, String> getRouteMap() {
        return this.f12189c;
    }

    public void setRouteMap(Map<String, String> map) {
        this.f12189c = map;
    }

    public void init(Context context) {
        init(context, null, null);
    }

    public void init(Context context, com.bytedance.router.dynamic.b bVar, a aVar) {
        com.bytedance.router.util.b.d("RouteMapper#init RouteMapper");
        this.f12191e = context;
        this.f12194h = aVar;
        synchronized (this.f12193g) {
            new IMappingInitializer() { // from class: com.bytedance.router.mapping.SmartRouter$$Mapping
                @Override // com.bytedance.router.IMappingInitializer
                public void init(Map<String, String> map) {
                }
            }.init(this.f12189c);
        }
        com.bytedance.router.util.b.d(String.format("RouteMapper#loadMappingByRouter routes: %d .", Integer.valueOf(this.f12189c.size())));
        if (bVar != null) {
            a(bVar);
        }
    }

    private void a(com.bytedance.router.dynamic.b bVar) {
        this.f12192f = bVar;
        if (bVar == null || !bVar.isAvailable()) {
            com.bytedance.router.util.b.e("RouteMapper#initDynamicRoutes serverParam is null or unavailable!!!");
        } else {
            AsyncTask.execute(new Runnable() { // from class: com.bytedance.router.e.1
                @Override // java.lang.Runnable
                public void run() {
                    e.this.b();
                    e.this.c();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        com.bytedance.router.dynamic.b bVar = this.f12192f;
        if (bVar == null || !bVar.isAvailable()) {
            com.bytedance.router.util.b.e("RouteMapper#requestRouteConfig serverParam is null or unavailable,You must use SmartRouter#init(Context, ServerParam) before !!!");
        } else {
            AsyncTask.execute(new Runnable() { // from class: com.bytedance.router.e.2
                @Override // java.lang.Runnable
                public void run() {
                    e.this.c();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        String string = this.f12191e.getSharedPreferences(f.SP_CONFIG, 0).getString(f12188b, "");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        com.bytedance.router.dynamic.a load = com.bytedance.router.dynamic.a.load(string);
        if (this.f12194h == null || load == null || com.bytedance.router.dynamic.a.isExpired(this.f12191e, load)) {
            return;
        }
        this.f12194h.onConfigChanged(load);
        if (com.bytedance.router.util.b.isDebug()) {
            com.bytedance.router.util.b.d("Load local routerConfig: " + string);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        a aVar;
        c.a<com.bytedance.router.dynamic.a> requestConfig = com.bytedance.router.net.c.requestConfig(this.f12191e, this.f12192f);
        if (requestConfig.errorCode == 0) {
            if (requestConfig.result == null || (aVar = this.f12194h) == null) {
                return;
            }
            aVar.onConfigChanged(requestConfig.result);
            this.f12191e.getSharedPreferences(f.SP_CONFIG, 0).edit().putString(f12188b, requestConfig.result.toString()).commit();
            return;
        }
        com.bytedance.router.util.b.e("RouteMapper#requestServer error: " + requestConfig.errorCode);
    }

    public com.bytedance.router.dynamic.b getServerParam() {
        return this.f12192f;
    }

    public void updateMapping(Map<String, String> map) {
        synchronized (this.f12193g) {
            if (this.f12190d == null) {
                HashMap hashMap = new HashMap();
                this.f12190d = hashMap;
                hashMap.putAll(this.f12189c);
                this.f12189c.putAll(map);
            } else {
                HashMap hashMap2 = new HashMap();
                hashMap2.putAll(this.f12190d);
                hashMap2.putAll(map);
                this.f12189c = hashMap2;
            }
        }
    }

    public boolean loadModuleMapping(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Object newInstance = Class.forName(String.format(f12187a, str)).newInstance();
            if (newInstance instanceof IMappingInitializer) {
                synchronized (this.f12193g) {
                    ((IMappingInitializer) newInstance).init(this.f12189c);
                }
                return true;
            }
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (InstantiationException e4) {
            e4.printStackTrace();
        }
        return false;
    }

    public String getTargetClass(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = this.f12189c.get(com.bytedance.router.util.c.getRealRouteUrl(str));
        if (TextUtils.isEmpty(str2)) {
            str2 = this.f12189c.get(com.bytedance.router.util.c.getRouteUrl(str));
        }
        com.bytedance.router.util.b.d("RouteMapper#getTargetClass url: " + str + "  |  targetClass: " + str2);
        return str2;
    }
}
