package com.bytedance.sync.net;

import com.bytedance.sync.m;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* compiled from: NetThreadPool.java */
/* loaded from: classes3.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final m<ExecutorService> f13496a = new m<ExecutorService>() { // from class: com.bytedance.sync.net.c.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.sync.m
        /* renamed from: a */
        public ExecutorService b(Object... objArr) {
            return Executors.newSingleThreadExecutor();
        }
    };

    public static void submit(Runnable runnable) {
        f13496a.get(new Object[0]).submit(runnable);
    }
}
