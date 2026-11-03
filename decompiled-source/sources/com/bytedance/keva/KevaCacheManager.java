package com.bytedance.keva;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import com.bytedance.Interface.a;
import com.bytedance.Interface.b;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
public final class KevaCacheManager {
    public static boolean IS_LOW_MEMORY = false;
    private static KevaCacheManager kevaCacheManagerInstance;
    private Thread delayTimeClearCacheThread;
    private Long lastClearTime;
    private final int mCacheClearExpirationTime;
    private a mICleanCacheMessage;
    private final int mIntervalMillisTime;
    private final boolean mIsNeedReleaseNativeMemory;
    private final boolean mIsOpenReleaseJavaMemory;
    private static final AtomicBoolean mStartClear = new AtomicBoolean(false);
    private static final ConcurrentLinkedQueue<b> kevaImplListener = new ConcurrentLinkedQueue<>();

    public static KevaCacheManager getInstance() {
        return kevaCacheManagerInstance;
    }

    public void setICleanCacheMessage(a aVar) {
        if (this.mICleanCacheMessage == null) {
            this.mICleanCacheMessage = aVar;
        }
    }

    void cleanCache(int i2, boolean z) {
        Iterator<b> it = kevaImplListener.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            b next = it.next();
            String repoName = next.getRepoName();
            long clearCache = next.clearCache(i2, z);
            a aVar = this.mICleanCacheMessage;
            if (aVar != null) {
                aVar.a(repoName, clearCache);
            }
            i3 = (int) (i3 + clearCache);
        }
        a aVar2 = this.mICleanCacheMessage;
        if (aVar2 != null) {
            aVar2.a(null, i3);
        }
    }

    public void startDelayClearCache() {
        if (this.mIsOpenReleaseJavaMemory && this.delayTimeClearCacheThread == null) {
            this.lastClearTime = Long.valueOf(System.currentTimeMillis());
            Thread thread = new Thread() { // from class: com.bytedance.keva.KevaCacheManager.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    while (true) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (KevaCacheManager.mStartClear.compareAndSet(false, true)) {
                            if (currentTimeMillis - KevaCacheManager.this.lastClearTime.longValue() <= KevaCacheManager.this.mIntervalMillisTime) {
                                KevaCacheManager.mStartClear.set(false);
                            } else {
                                KevaCacheManager.IS_LOW_MEMORY = false;
                                KevaCacheManager kevaCacheManager = KevaCacheManager.this;
                                kevaCacheManager.cleanCache(kevaCacheManager.mCacheClearExpirationTime, KevaCacheManager.this.mIsNeedReleaseNativeMemory);
                                KevaCacheManager.mStartClear.set(false);
                                return;
                            }
                        }
                        try {
                            sleep(KevaCacheManager.this.mIntervalMillisTime);
                            Log.d("KevaCacheManage", "delayTimeClearCacheThread wakeup");
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            };
            this.delayTimeClearCacheThread = thread;
            thread.start();
        }
    }

    private KevaCacheManager(boolean z, int i2, int i3, boolean z2) {
        this.mICleanCacheMessage = null;
        this.mIntervalMillisTime = i2;
        this.mCacheClearExpirationTime = i3;
        this.mIsNeedReleaseNativeMemory = z2;
        this.mIsOpenReleaseJavaMemory = z;
    }

    public static KevaCacheManager init(Context context, boolean z, int i2, int i3, boolean z2) {
        KevaCacheManager kevaCacheManager = kevaCacheManagerInstance;
        return kevaCacheManager != null ? kevaCacheManager : new KevaCacheManager(context, z, i2, i3, z2);
    }

    private KevaCacheManager(Context context, boolean z, int i2, int i3, boolean z2) {
        this(z, i2, i3, z2);
        InitLowMemoryListener(context);
        kevaCacheManagerInstance = this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void registerClearListener(b bVar) {
        kevaImplListener.add(bVar);
    }

    private void InitLowMemoryListener(Context context) {
        if (this.mIsOpenReleaseJavaMemory) {
            if (context instanceof Activity) {
                context = context.getApplicationContext();
            } else if (!(context instanceof Application)) {
                return;
            }
            ((Application) context).registerComponentCallbacks(new ComponentCallbacks2() { // from class: com.bytedance.keva.KevaCacheManager.2
                private final Runnable realTimeClearCacheThread = new Runnable() { // from class: com.bytedance.keva.KevaCacheManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        KevaCacheManager.IS_LOW_MEMORY = true;
                        KevaCacheManager kevaCacheManager = KevaCacheManager.getInstance();
                        if (kevaCacheManager != null) {
                            kevaCacheManager.cleanCache(10000, false);
                        }
                        KevaCacheManager.mStartClear.set(false);
                    }
                };

                @Override // android.content.ComponentCallbacks
                public void onConfigurationChanged(Configuration configuration) {
                }

                @Override // android.content.ComponentCallbacks2
                public void onTrimMemory(int i2) {
                }

                @Override // android.content.ComponentCallbacks
                public void onLowMemory() {
                    if (KevaCacheManager.mStartClear.compareAndSet(false, true)) {
                        new Thread(this.realTimeClearCacheThread).start();
                    }
                }
            });
        }
    }
}
