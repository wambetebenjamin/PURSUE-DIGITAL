package com.bytedance.pipeline;

import com.bytedance.pipeline.j;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* compiled from: ParallelInterceptor.java */
/* loaded from: classes2.dex */
public class h<T> extends d<List<T>, T> {

    /* renamed from: a  reason: collision with root package name */
    private Executor f10014a;

    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public final Object a_(final b<T> bVar, List<T> list) {
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        final CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
        for (final T t : list) {
            this.f10014a.execute(new Runnable() { // from class: com.bytedance.pipeline.h.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        try {
                            copyOnWriteArrayList.add(bVar.a((b) t));
                        } catch (j.a e2) {
                            Throwable cause = e2.getCause();
                            copyOnWriteArrayList2.add(cause);
                            h.this.e(cause);
                        }
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        if (copyOnWriteArrayList2.isEmpty()) {
            return copyOnWriteArrayList;
        }
        throw new com.bytedance.pipeline.exception.d(copyOnWriteArrayList2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.d
    public void a(Object... objArr) {
        super.a(objArr);
        if (objArr != null) {
            if (objArr.length != 1) {
                throw new IllegalArgumentException("ParallelInterceptor only need one param");
            }
            if (!(objArr[0] instanceof Executor)) {
                throw new IllegalArgumentException("ParallelInterceptor args must be instance of Executor");
            }
            this.f10014a = (Executor) objArr[0];
            return;
        }
        this.f10014a = Executors.newFixedThreadPool(2);
    }
}
