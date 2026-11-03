package com.bytedance.common.jato.boost.sched;

import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class ThreadAttrFixer {
    public static native int nativeFixThreadAttrInherited(boolean z);

    static {
        d.loadLibrary();
    }
}
