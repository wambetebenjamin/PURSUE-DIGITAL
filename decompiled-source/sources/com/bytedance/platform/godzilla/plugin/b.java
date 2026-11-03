package com.bytedance.platform.godzilla.plugin;

import android.app.Application;
import java.util.List;
/* compiled from: PluginGroup.java */
/* loaded from: classes2.dex */
public abstract class b extends a {

    /* renamed from: a  reason: collision with root package name */
    private List<a> f11070a;

    /* renamed from: b  reason: collision with root package name */
    private f f11071b;

    protected abstract List<a> a();

    @Override // com.bytedance.platform.godzilla.plugin.a
    public abstract String getName();

    @Override // com.bytedance.platform.godzilla.plugin.a
    public final void init(Application application) {
        super.init(application);
        List<a> a2 = a();
        this.f11070a = a2;
        for (a aVar : a2) {
            aVar.init(application);
        }
    }

    public final void start(d dVar) {
        for (a aVar : this.f11070a) {
            if (aVar.startType() == dVar) {
                aVar.start();
                if (aVar instanceof f) {
                    this.f11071b = (f) aVar;
                }
            }
        }
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public final void start() {
        super.start();
        for (a aVar : this.f11070a) {
            aVar.start();
        }
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public final void stop() {
        super.stop();
        for (a aVar : this.f11070a) {
            aVar.stop();
        }
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public final void destroy() {
        super.destroy();
        for (a aVar : this.f11070a) {
            aVar.destroy();
        }
    }

    public f getXHookPlugin() {
        return this.f11071b;
    }

    public void setXHookPlugin(f fVar) {
        this.f11071b = fVar;
    }
}
