package com.twitter.sdk.android.core.internal;
/* loaded from: classes10.dex */
public class SystemCurrentTimeProvider implements CurrentTimeProvider {
    @Override // com.twitter.sdk.android.core.internal.CurrentTimeProvider
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
