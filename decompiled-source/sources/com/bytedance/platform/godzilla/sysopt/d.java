package com.bytedance.platform.godzilla.sysopt;

import android.app.Application;
import com.bytedance.sysoptimizer.DvmOptimizer;
/* compiled from: DvmOptimizerPlugin.java */
/* loaded from: classes2.dex */
public class d extends com.bytedance.platform.godzilla.plugin.a {

    /* renamed from: a  reason: collision with root package name */
    private Application f11075a;

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void destroy() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "DvmOptimizerPlugin";
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void init(Application application) {
        super.init(application);
        this.f11075a = application;
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        Application application = this.f11075a;
        if (application != null) {
            DvmOptimizer.optDvmLinearAllocBuffer(application);
        }
    }
}
