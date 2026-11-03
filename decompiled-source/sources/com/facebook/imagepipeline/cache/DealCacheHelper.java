package com.facebook.imagepipeline.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes8.dex */
public class DealCacheHelper {
    private static volatile ExecutorService sIOExecutor;

    public static ExecutorService getIOExecutor() {
        if (sIOExecutor == null) {
            synchronized (DealCacheHelper.class) {
                if (sIOExecutor == null) {
                    sIOExecutor = Executors.newSingleThreadExecutor();
                }
            }
        }
        return sIOExecutor;
    }
}
