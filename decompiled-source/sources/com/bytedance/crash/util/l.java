package com.bytedance.crash.util;

import java.util.List;
/* compiled from: FdCollector.java */
@Deprecated
/* loaded from: classes2.dex */
public class l {
    public static List<String> collect() {
        return NativeTools.get().getFdListForAPM();
    }
}
