package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.Locale;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzfa extends IOException {
    zzfa() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfa(long j2, long j3, int i2, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(String.format(Locale.US, "Pos: %d, limit: %d, len: %d", Long.valueOf(j2), Long.valueOf(j3), Integer.valueOf(i2)))), th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfa(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
