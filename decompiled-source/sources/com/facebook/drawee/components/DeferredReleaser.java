package com.facebook.drawee.components;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.internal.Preconditions;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/* loaded from: classes8.dex */
public class DeferredReleaser {
    public static boolean opt = false;
    private static int releasedCount;
    private static DeferredReleaser sInstance;
    private static final boolean shouldCheck;
    private final Runnable releaseRunnable = new Runnable() { // from class: com.facebook.drawee.components.DeferredReleaser.1
        @Override // java.lang.Runnable
        public void run() {
            if (!DeferredReleaser.opt) {
                DeferredReleaser.ensureOnUiThread();
            }
            for (Releasable releasable : DeferredReleaser.this.mPendingReleasables) {
                releasable.release();
            }
            DeferredReleaser.this.mPendingReleasables.clear();
            if (DeferredReleaser.opt) {
                DeferredReleaser.access$210();
                if (DeferredReleaser.releasedCount < 0) {
                    int unused = DeferredReleaser.releasedCount = 0;
                }
            }
        }
    };
    private final Set<Releasable> mPendingReleasables = new HashSet();
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes8.dex */
    public interface Releasable {
        void release();
    }

    static /* synthetic */ int access$210() {
        int i2 = releasedCount;
        releasedCount = i2 - 1;
        return i2;
    }

    static {
        shouldCheck = new Random().nextFloat() < 0.005f;
    }

    public static synchronized DeferredReleaser getInstance() {
        DeferredReleaser deferredReleaser;
        synchronized (DeferredReleaser.class) {
            if (sInstance == null) {
                sInstance = new DeferredReleaser();
            }
            deferredReleaser = sInstance;
        }
        return deferredReleaser;
    }

    public void scheduleDeferredRelease(Releasable releasable) {
        if (!opt || shouldCheck) {
            ensureOnUiThread();
        }
        if (this.mPendingReleasables.add(releasable) && this.mPendingReleasables.size() == 1) {
            if (opt) {
                int i2 = releasedCount;
                if (i2 > 0) {
                    return;
                }
                releasedCount = i2 + 1;
            }
            this.mUiHandler.post(this.releaseRunnable);
        }
    }

    public void cancelDeferredRelease(Releasable releasable) {
        if (!opt || shouldCheck) {
            ensureOnUiThread();
        }
        this.mPendingReleasables.remove(releasable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ensureOnUiThread() {
        Preconditions.checkState(Looper.getMainLooper().getThread() == Thread.currentThread());
    }
}
