package com.ss.android.common.applog;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes9.dex */
public class LaunchObserverHolder implements ILaunchObserver {
    private static volatile LaunchObserverHolder sInstance;
    private final CopyOnWriteArraySet<ILaunchObserver> observers = new CopyOnWriteArraySet<>();

    public static LaunchObserverHolder getInstance() {
        if (sInstance == null) {
            synchronized (LaunchObserverHolder.class) {
                if (sInstance == null) {
                    sInstance = new LaunchObserverHolder();
                }
            }
        }
        return sInstance;
    }

    private LaunchObserverHolder() {
    }

    @Override // com.ss.android.common.applog.ILaunchObserver
    public void onLaunch(String str, long j2, boolean z) {
        Iterator<ILaunchObserver> it = this.observers.iterator();
        while (it.hasNext()) {
            try {
                it.next().onLaunch(str, j2, z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void addObserver(ILaunchObserver iLaunchObserver) {
        if (iLaunchObserver != null) {
            this.observers.add(iLaunchObserver);
        }
    }

    public void removeObserver(ILaunchObserver iLaunchObserver) {
        if (iLaunchObserver != null) {
            this.observers.remove(iLaunchObserver);
        }
    }

    public int getObserverSize() {
        return this.observers.size();
    }
}
