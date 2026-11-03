package com.bytedance.platform.godzilla.sysopt;

import android.app.Application;
import com.bytedance.sysoptimizer.fake.name.SmFakeNameHandler;
/* compiled from: SmFakeNameHandlerPlugin.java */
/* loaded from: classes2.dex */
public class i extends com.bytedance.platform.godzilla.plugin.a {

    /* renamed from: a  reason: collision with root package name */
    private Application f11077a;

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void destroy() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "SmFakeNameHandlerPlugin";
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void init(Application application) {
        super.init(application);
        this.f11077a = application;
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        Application application = this.f11077a;
        if (application != null) {
            SmFakeNameHandler.start(application);
        }
    }
}
