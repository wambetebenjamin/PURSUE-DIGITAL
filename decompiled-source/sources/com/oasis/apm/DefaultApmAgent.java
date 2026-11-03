package com.oasis.apm;

import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public class DefaultApmAgent extends ApmAgent {
    @Override // com.oasis.apm.ApmAgent
    public void endSpan(String str, String str2) {
        Logger.d("DefaultApmAgent", "endSpan() called with: moduleName = [" + str + "], taskName = [" + str2 + "]");
    }

    @Override // com.oasis.apm.ApmAgent
    public void monitorEvent(String str, String str2, String str3) {
        Logger.w("DefaultApmAgent", "monitorEvent() called with: serviceName = [" + str + "], category = [" + str2 + "], metric = [" + str3 + "]");
    }

    @Override // com.oasis.apm.ApmAgent
    public void startSpan(String str, String str2, boolean z) {
        Logger.d("DefaultApmAgent", "startSpan() called with: moduleName = [" + str + "], taskName = [" + str2 + "], forceRefresh = [" + z + "]");
    }

    @Override // com.oasis.apm.ApmAgent
    public void uploadALogInternal(long j2, long j3, String str) {
        Logger.d("DefaultApmAgent", "uploadALogInternal() called with: start = [" + j2 + "], end = [" + j3 + "], scene = [" + str + "]");
    }
}
