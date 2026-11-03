package com.google.android.play.core.assetpacks.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public abstract class an implements Closeable {
    public abstract long a();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract InputStream b(long j2, long j3) throws IOException;

    public final synchronized InputStream c() throws IOException {
        return b(0L, a());
    }
}
