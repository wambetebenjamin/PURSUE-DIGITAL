package com.bytedance.geckox.utils;
/* compiled from: ExceptionUtils.java */
/* loaded from: classes2.dex */
public class e {
    public static void a(Throwable th) {
        if (h.c()) {
            com.bytedance.geckox.logger.b.e(com.bytedance.geckox.d.f8984a, "throwIfDebug:", th);
            throw new RuntimeException(th);
        } else {
            com.bytedance.geckox.logger.b.w(com.bytedance.geckox.d.f8984a, "throwIfDebug:", th);
        }
    }
}
