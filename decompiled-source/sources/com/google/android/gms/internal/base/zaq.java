package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes5.dex */
public interface zaq {
    ExecutorService zaa(ThreadFactory threadFactory, int i2);

    ExecutorService zab(int i2, int i3);

    ExecutorService zac(int i2, ThreadFactory threadFactory, int i3);
}
