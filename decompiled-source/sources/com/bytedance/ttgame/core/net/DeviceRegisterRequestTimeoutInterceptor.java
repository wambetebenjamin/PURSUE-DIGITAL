package com.bytedance.ttgame.core.net;

import android.text.TextUtils;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.ttnet.http.e;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class DeviceRegisterRequestTimeoutInterceptor implements Interceptor {
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public SsResponse intercept(Interceptor.Chain chain) throws Exception {
        Request request = chain.request();
        String path = request.getPath();
        if (!TextUtils.isEmpty(path)) {
            if (path.contains("device_register")) {
                e eVar = new e();
                eVar.protect_timeout = 10000L;
                request.setExtraInfo(eVar);
            } else if (path.contains("/service/2/app_log/")) {
                e eVar2 = new e();
                eVar2.protect_timeout = 60000L;
                request.setExtraInfo(eVar2);
            }
        }
        return chain.proceed(request);
    }
}
