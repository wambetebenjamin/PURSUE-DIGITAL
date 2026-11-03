package com.bytedance.apm.util;
/* compiled from: MemUtils.java */
/* loaded from: classes.dex */
public class p {
    public static long getJavaUsedMemBytes() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}
