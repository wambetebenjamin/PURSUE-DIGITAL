package com.bytedance.ttgame.framework.gbridge.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes3.dex */
public class ThreadPool {
    private static ExecutorService fixedThreadPool = Executors.newCachedThreadPool();

    public static void submit(Runnable runnable) {
        fixedThreadPool.submit(runnable);
    }
}
