package com.ttnet.org.chromium.net;

import com.bytedance.apm.perf.traffic.a;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import java.util.regex.Pattern;
@JNINamespace(a.KEY_TRAFFIC_STATUS_NET)
/* loaded from: classes9.dex */
public class TTPatternUtil {
    public static boolean isPatternStringValid(String str) {
        try {
            Pattern.compile(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
