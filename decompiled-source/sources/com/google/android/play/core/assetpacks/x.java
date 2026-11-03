package com.google.android.play.core.assetpacks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class x implements com.google.android.play.core.assetpacks.internal.as {
    @Override // com.google.android.play.core.assetpacks.internal.as
    public final /* synthetic */ Object a() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.google.android.play.core.assetpacks.o
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return new Thread(runnable, "UpdateListenerExecutor");
            }
        });
        com.google.android.play.core.assetpacks.internal.ar.a(newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }
}
