package com.bytedance.platform.godzilla;

import android.app.Application;
import android.text.TextUtils;
import com.bytedance.platform.godzilla.common.i;
import com.bytedance.platform.godzilla.common.j;
import com.bytedance.platform.godzilla.common.k;
import com.bytedance.platform.godzilla.plugin.d;
import com.bytedance.platform.godzilla.plugin.f;
import java.util.HashMap;
/* compiled from: Godzilla.java */
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11020a = "Godzilla";

    /* renamed from: b  reason: collision with root package name */
    private static volatile a f11021b;

    /* renamed from: c  reason: collision with root package name */
    private final Application f11022c;

    /* renamed from: d  reason: collision with root package name */
    private final HashMap<String, com.bytedance.platform.godzilla.plugin.a> f11023d;

    public static a init(a aVar) {
        if (aVar == null) {
            throw new RuntimeException("Godzilla should not be null.");
        }
        synchronized (a.class) {
            if (f11021b == null) {
                f11021b = aVar;
            } else {
                k.e(f11020a, "Godzilla instance is already set. this invoking will be ignored");
            }
        }
        return f11021b;
    }

    public static a with() {
        if (f11021b == null) {
            throw new RuntimeException("Godzilla.init() method must be called first");
        }
        return f11021b;
    }

    public com.bytedance.platform.godzilla.plugin.a getPlugin(String str) {
        return this.f11023d.get(str);
    }

    public void addPlugin(com.bytedance.platform.godzilla.plugin.a aVar) {
        if (getPlugin(aVar.getName()) == null) {
            stop();
            destroy();
            this.f11023d.put(aVar.getName(), aVar);
            start(d.REGISTER_EXCEPTION);
        }
    }

    public void removePlugin(com.bytedance.platform.godzilla.plugin.a aVar) {
        if (getPlugin(aVar.getName()) != null) {
            stop();
            destroy();
            this.f11023d.remove(aVar.getName());
            start(d.REGISTER_EXCEPTION);
        }
    }

    public void start() {
        start(d.IMMEDIATE);
    }

    public void start(d dVar) {
        f fVar = null;
        for (com.bytedance.platform.godzilla.plugin.a aVar : this.f11023d.values()) {
            if (aVar instanceof com.bytedance.platform.godzilla.plugin.b) {
                com.bytedance.platform.godzilla.plugin.b bVar = (com.bytedance.platform.godzilla.plugin.b) aVar;
                bVar.start(dVar);
                if (bVar.getXHookPlugin() != null) {
                    fVar = bVar.getXHookPlugin();
                    bVar.setXHookPlugin(null);
                }
            } else if (aVar.startType() == dVar) {
                aVar.start();
                if (aVar instanceof f) {
                    fVar = (f) aVar;
                }
            }
        }
        if (fVar != null) {
            fVar.invokeXHookRefresh();
        }
    }

    public void stop() {
        for (com.bytedance.platform.godzilla.plugin.a aVar : this.f11023d.values()) {
            aVar.stop();
        }
    }

    public void destroy() {
        for (com.bytedance.platform.godzilla.plugin.a aVar : this.f11023d.values()) {
            aVar.destroy();
        }
    }

    private a(Application application, HashMap<String, com.bytedance.platform.godzilla.plugin.a> hashMap, i iVar, k.a aVar, j jVar) {
        this.f11022c = application;
        this.f11023d = hashMap;
        b.INSTANCE.init(application, iVar, aVar);
        for (com.bytedance.platform.godzilla.plugin.a aVar2 : hashMap.values()) {
            aVar2.init(this.f11022c);
        }
        com.bytedance.platform.godzilla.utils.d.init(jVar);
    }

    /* compiled from: Godzilla.java */
    /* renamed from: com.bytedance.platform.godzilla.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0163a {

        /* renamed from: a  reason: collision with root package name */
        private final Application f11024a;

        /* renamed from: b  reason: collision with root package name */
        private final HashMap<String, com.bytedance.platform.godzilla.plugin.a> f11025b = new HashMap<>();

        /* renamed from: c  reason: collision with root package name */
        private i f11026c;

        /* renamed from: d  reason: collision with root package name */
        private k.a f11027d;

        /* renamed from: e  reason: collision with root package name */
        private j f11028e;

        public C0163a(Application application) {
            if (application == null) {
                throw new RuntimeException("Godzilla init, application is null");
            }
            this.f11024a = application;
        }

        public C0163a plugin(com.bytedance.platform.godzilla.plugin.a aVar) {
            String name = aVar.getName();
            if (TextUtils.isEmpty(name)) {
                throw new RuntimeException(String.format("%s plugin name is null", aVar.getClass().getName()));
            }
            if (this.f11025b.get(name) != null) {
                throw new RuntimeException(String.format("%s plugin is already exist", name));
            }
            this.f11025b.put(name, aVar);
            return this;
        }

        public C0163a setReflectHackHelper(j jVar) {
            this.f11028e = jVar;
            return this;
        }

        public C0163a setLog(i iVar) {
            this.f11026c = iVar;
            return this;
        }

        public C0163a setLogLevel(k.a aVar) {
            this.f11027d = aVar;
            return this;
        }

        public a build() {
            return new a(this.f11024a, this.f11025b, this.f11026c, this.f11027d, this.f11028e);
        }
    }
}
