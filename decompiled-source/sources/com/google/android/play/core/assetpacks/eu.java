package com.google.android.play.core.assetpacks;

import com.bytedance.librarian.c;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
abstract class eu {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract long b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String c();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean d();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean e();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] f();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean g() {
        if (c() == null) {
            return false;
        }
        return c().endsWith(c.a.SEPARATOR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean h() {
        return a() == 0;
    }
}
