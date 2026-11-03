package com.google.android.gms.internal.play_billing;

import java.util.concurrent.TimeoutException;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzdf extends TimeoutException {
    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdf(String str, zzdg zzdgVar) {
        super(str);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
