package com.ss.android.deviceregister.utils;

import android.content.Context;
import android.util.Pair;
import com.ss.android.deviceregister.i;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* compiled from: GaidGetUitls.java */
/* loaded from: classes9.dex */
public class c {
    public static Pair<String, Boolean> getGaid(final Context context, long j2) {
        if (i.isInitGaid()) {
            return i.getGaid(context);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Pair<String, Boolean>[] pairArr = {new Pair<>(null, false)};
        new a("DeviceRegister.gaid").execute(new Runnable() { // from class: com.ss.android.deviceregister.utils.c.1
            @Override // java.lang.Runnable
            public void run() {
                pairArr[0] = i.getGaid(context);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(j2, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        return pairArr[0];
    }

    /* compiled from: GaidGetUitls.java */
    /* loaded from: classes9.dex */
    private static final class a implements Executor {

        /* renamed from: a  reason: collision with root package name */
        private final String f18274a;

        a(String str) {
            this.f18274a = str;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new com.bytedance.common.utility.concurrent.e(runnable, this.f18274a, false).start();
        }
    }
}
