package com.bytedance.ttgame.core.scheduler;

import java.util.concurrent.CountDownLatch;
/* loaded from: classes3.dex */
public class RaceExecutor {
    public static CountDownLatch start(Runnable... runnableArr) {
        if (runnableArr == null || runnableArr.length < 1) {
            return null;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(runnableArr.length);
        for (final Runnable runnable : runnableArr) {
            SchedulerService.getInstance().getExecutor(0).execute(new Runnable() { // from class: com.bytedance.ttgame.core.scheduler.RaceExecutor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RaceExecutor.lambda$start$0(runnable, countDownLatch);
                }
            });
        }
        return countDownLatch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$start$0(Runnable runnable, CountDownLatch countDownLatch) {
        runnable.run();
        countDownLatch.countDown();
    }

    public static void wait(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }
}
