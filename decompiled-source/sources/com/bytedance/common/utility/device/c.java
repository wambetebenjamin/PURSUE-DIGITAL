package com.bytedance.common.utility.device;

import com.bytedance.common.utility.Logger;
/* compiled from: SystemPropertiesUtils.java */
/* loaded from: classes2.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5252a = "SystemPropertiesUtils";

    /* renamed from: b  reason: collision with root package name */
    private static b f5253b = new b();
    public static volatile boolean hasProxy = true;

    private c() {
    }

    public static String getSystemProperty(String str) {
        if (hasProxy) {
            try {
                return f5253b.get(str);
            } catch (Throwable th) {
                Logger.e(f5252a, "android.os.SystemProperties reflect fail.", th);
                return a.a(str);
            }
        }
        return a.a(str);
    }
}
