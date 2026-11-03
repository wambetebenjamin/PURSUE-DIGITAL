package com.appsflyer.internal;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
/* loaded from: classes.dex */
public abstract class AFi1nSDK extends Observable {
    public final String AFInAppEventParameterName;
    final Runnable AFInAppEventType;
    public final String AFLogger;

    /* renamed from: d  reason: collision with root package name */
    long f107d;
    public final Map<String, Object> values = new HashMap();
    public AFa1uSDK unregisterClient = AFa1uSDK.NOT_STARTED;

    /* loaded from: classes.dex */
    public enum AFa1uSDK {
        NOT_STARTED,
        STARTED,
        FINISHED
    }

    public abstract void values(Context context);

    public AFi1nSDK(String str, String str2, Runnable runnable) {
        this.AFInAppEventType = runnable;
        this.AFInAppEventParameterName = str2;
        this.AFLogger = str;
    }

    /* renamed from: com.appsflyer.internal.AFi1nSDK$3  reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass3 implements Observer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public AnonymousClass3() {
        }

        @Override // java.util.Observer
        public final void update(Observable observable, Object obj) {
            AFi1nSDK.this.AFInAppEventType.run();
        }
    }

    public final void AFInAppEventType() {
        this.values.put("source", this.AFInAppEventParameterName);
        this.values.put("type", this.AFLogger);
        this.values.put("latency", Long.valueOf(System.currentTimeMillis() - this.f107d));
        this.unregisterClient = AFa1uSDK.FINISHED;
        setChanged();
        notifyObservers();
    }
}
