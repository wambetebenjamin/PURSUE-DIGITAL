package com.bytedance.apm.impl;

import com.bytedance.services.apm.api.ILaunchTrace;
/* loaded from: classes.dex */
public class LaunchTraceImpl implements ILaunchTrace {
    @Override // com.bytedance.services.apm.api.ILaunchTrace
    public void startTrace() {
        com.bytedance.apm.trace.b.startTrace();
    }

    @Override // com.bytedance.services.apm.api.ILaunchTrace
    public void startSpan(String str, String str2) {
        com.bytedance.apm.trace.b.startSpan(str, str2);
    }

    @Override // com.bytedance.services.apm.api.ILaunchTrace
    public void endSpan(String str, String str2) {
        com.bytedance.apm.trace.b.endSpan(str, str2);
    }

    @Override // com.bytedance.services.apm.api.ILaunchTrace
    public void endTrace(int i2, String str, long j2) {
        com.bytedance.apm.trace.b.endTrace(i2, str, j2);
    }

    @Override // com.bytedance.services.apm.api.ILaunchTrace
    public void cancelTrace() {
        com.bytedance.apm.trace.b.cancelTrace();
    }
}
