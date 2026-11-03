package com.ttnet.org.chromium.net;

import com.bytedance.apm.perf.traffic.a;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace(a.KEY_TRAFFIC_STATUS_NET)
/* loaded from: classes9.dex */
public final class HttpUtil {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        boolean isAllowedHeader(String str, String str2);
    }

    public static boolean isAllowedHeader(String str, String str2) {
        return HttpUtilJni.get().isAllowedHeader(str, str2);
    }
}
