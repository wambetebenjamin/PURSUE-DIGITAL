package com.monitor.cloudmessage;

import android.content.Context;
import com.bytedance.apm.f;
import com.bytedance.services.apm.api.l;
/* compiled from: CloudMessageWidget.java */
/* loaded from: classes9.dex */
public class b extends f {
    @Override // com.bytedance.apm.f, com.bytedance.services.apm.api.j
    public String getTag() {
        return "b";
    }

    @Override // com.bytedance.apm.f, com.bytedance.services.apm.api.j
    public boolean isOnlyMainProcess() {
        return false;
    }

    @Override // com.bytedance.services.apm.api.j
    public void notifyParams(l lVar) {
    }

    @Override // com.bytedance.apm.f, com.bytedance.services.apm.api.j
    public void init(Context context) {
        super.init(context);
    }

    @Override // com.bytedance.apm.f, com.bytedance.services.apm.api.j
    public void start() {
        super.start();
    }

    @Override // com.bytedance.apm.f, com.bytedance.services.apm.api.j
    public void stop() {
        super.stop();
    }

    @Override // com.bytedance.apm.f, com.bytedance.services.apm.api.j
    public void destroy() {
        super.destroy();
    }
}
