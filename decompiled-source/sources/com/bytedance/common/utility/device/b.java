package com.bytedance.common.utility.device;
/* compiled from: SystemPropertiesProxy.java */
/* loaded from: classes2.dex */
public final class b {
    public String get(String str) {
        return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
    }
}
