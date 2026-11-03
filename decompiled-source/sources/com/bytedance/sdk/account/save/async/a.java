package com.bytedance.sdk.account.save.async;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* compiled from: Command.java */
/* loaded from: classes2.dex */
public abstract class a<T> {

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f12964a = Executors.newSingleThreadExecutor();

    /* renamed from: b  reason: collision with root package name */
    private static final Handler f12965b = new Handler(Looper.getMainLooper());

    protected void a(T t) {
    }

    protected abstract T b();

    public final void execute() {
        f12964a.execute(new Runnable() { // from class: com.bytedance.sdk.account.save.async.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a aVar = a.this;
                    aVar.b(aVar.b());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final T t) {
        f12965b.post(new Runnable() { // from class: com.bytedance.sdk.account.save.async.a.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                a.this.a(t);
            }
        });
    }
}
