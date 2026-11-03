package com.bytedance.android.service.manager.push.trace;

import android.util.Log;
/* loaded from: classes.dex */
public class PushTraceExternalServiceImplOfMock implements PushTraceExternalService {
    @Override // com.bytedance.android.service.manager.push.trace.PushTraceExternalService
    public ITraceScene getTraceScene(PushTraceSceneType pushTraceSceneType) {
        Log.w("PushTraceExternalService", "cur getTraceScene method is empty impl in PushTraceExternalServiceImplOfMockClass");
        return new ITraceSceneImplOfMock();
    }
}
