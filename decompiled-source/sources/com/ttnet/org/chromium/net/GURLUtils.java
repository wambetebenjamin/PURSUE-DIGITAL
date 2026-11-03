package com.ttnet.org.chromium.net;

import com.bytedance.apm.perf.traffic.a;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace(a.KEY_TRAFFIC_STATUS_NET)
/* loaded from: classes9.dex */
public final class GURLUtils {

    /* loaded from: classes9.dex */
    public interface Natives {
        String getOrigin(String str);
    }

    public static String getOrigin(String str) {
        return GURLUtilsJni.get().getOrigin(str);
    }
}
