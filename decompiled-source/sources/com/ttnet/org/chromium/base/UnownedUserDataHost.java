package com.ttnet.org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import com.ttnet.org.chromium.base.ThreadUtils;
import com.ttnet.org.chromium.base.lifetime.DestroyChecker;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes9.dex */
public final class UnownedUserDataHost {
    private final DestroyChecker mDestroyChecker;
    private Handler mHandler;
    private final ThreadUtils.ThreadChecker mThreadChecker;
    private HashMap<UnownedUserDataKey<?>, WeakReference<? extends UnownedUserData>> mUnownedUserDataMap;

    private static Looper retrieveNonNullLooperOrThrow() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            return myLooper;
        }
        throw new IllegalStateException();
    }

    public UnownedUserDataHost() {
        this(new Handler(retrieveNonNullLooperOrThrow()));
    }

    UnownedUserDataHost(Handler handler) {
        this.mThreadChecker = new ThreadUtils.ThreadChecker();
        this.mDestroyChecker = new DestroyChecker();
        this.mUnownedUserDataMap = new HashMap<>();
        this.mHandler = handler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T extends UnownedUserData> void set(UnownedUserDataKey<T> unownedUserDataKey, T t) {
        checkState();
        if (this.mUnownedUserDataMap.containsKey(unownedUserDataKey) && !t.equals(get(unownedUserDataKey))) {
            unownedUserDataKey.detachFromHost(this);
        }
        this.mUnownedUserDataMap.put(unownedUserDataKey, new WeakReference<>(t));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T extends UnownedUserData> T get(UnownedUserDataKey<T> unownedUserDataKey) {
        checkState();
        WeakReference<? extends UnownedUserData> weakReference = this.mUnownedUserDataMap.get(unownedUserDataKey);
        if (weakReference == null) {
            return null;
        }
        UnownedUserData unownedUserData = weakReference.get();
        if (unownedUserData == null) {
            unownedUserDataKey.detachFromHost(this);
            return null;
        }
        return unownedUserDataKey.getValueClass().cast(unownedUserData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T extends UnownedUserData> void remove(UnownedUserDataKey<T> unownedUserDataKey) {
        final UnownedUserData unownedUserData;
        checkState();
        WeakReference<? extends UnownedUserData> remove = this.mUnownedUserDataMap.remove(unownedUserDataKey);
        if (remove == null || (unownedUserData = remove.get()) == null || !unownedUserData.informOnDetachmentFromHost()) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.ttnet.org.chromium.base.UnownedUserDataHost$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UnownedUserDataHost.this.m713lambda$remove$0$comttnetorgchromiumbaseUnownedUserDataHost(unownedUserData);
            }
        });
    }

    /* renamed from: lambda$remove$0$com-ttnet-org-chromium-base-UnownedUserDataHost  reason: not valid java name */
    public /* synthetic */ void m713lambda$remove$0$comttnetorgchromiumbaseUnownedUserDataHost(UnownedUserData unownedUserData) {
        unownedUserData.onDetachedFromHost(this);
    }

    public void destroy() {
        this.mThreadChecker.assertOnValidThread();
        if (this.mDestroyChecker.isDestroyed()) {
            return;
        }
        for (UnownedUserDataKey unownedUserDataKey : new HashSet(this.mUnownedUserDataMap.keySet())) {
            unownedUserDataKey.detachFromHost(this);
        }
        this.mUnownedUserDataMap = null;
        this.mHandler = null;
        this.mDestroyChecker.destroy();
    }

    int getMapSize() {
        checkState();
        return this.mUnownedUserDataMap.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDestroyed() {
        return this.mDestroyChecker.isDestroyed();
    }

    private void checkState() {
        this.mThreadChecker.assertOnValidThread();
        this.mDestroyChecker.checkNotDestroyed();
    }
}
