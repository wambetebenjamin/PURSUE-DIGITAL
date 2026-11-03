package com.bytedance.platform.godzilla.sysopt;

import android.app.Application;
import com.bytedance.sysoptimizer.ArtOptimizer;
/* compiled from: ArtOptimizerPlugin.java */
/* loaded from: classes2.dex */
public class a extends com.bytedance.platform.godzilla.plugin.a {

    /* renamed from: a  reason: collision with root package name */
    private Application f11074a;

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void destroy() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "ArtOptimizerPlugin";
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void init(Application application) {
        super.init(application);
        this.f11074a = application;
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        Application application = this.f11074a;
        if (application != null) {
            ArtOptimizer.optSuspendTimeout(application);
        }
    }
}
