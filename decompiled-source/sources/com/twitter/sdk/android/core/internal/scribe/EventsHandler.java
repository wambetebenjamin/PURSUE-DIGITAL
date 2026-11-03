package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import com.twitter.sdk.android.core.internal.CommonUtils;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes10.dex */
public abstract class EventsHandler<T> implements EventsStorageListener {
    protected final Context context;
    protected final ScheduledExecutorService executor;
    protected EventsStrategy<T> strategy;

    protected abstract EventsStrategy<T> getDisabledEventsStrategy();

    public EventsHandler(Context context, EventsStrategy<T> eventsStrategy, EventsFilesManager eventsFilesManager, ScheduledExecutorService scheduledExecutorService) {
        this.context = context.getApplicationContext();
        this.executor = scheduledExecutorService;
        this.strategy = eventsStrategy;
        eventsFilesManager.registerRollOverListener(this);
    }

    public void recordEventAsync(final T t, final boolean z) {
        executeAsync(new Runnable() { // from class: com.twitter.sdk.android.core.internal.scribe.EventsHandler$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                EventsHandler.this.m725x2232fc4f(t, z);
            }
        });
    }

    /* renamed from: lambda$recordEventAsync$0$com-twitter-sdk-android-core-internal-scribe-EventsHandler  reason: not valid java name */
    public /* synthetic */ void m725x2232fc4f(Object obj, boolean z) {
        try {
            this.strategy.recordEvent(obj);
            if (z) {
                this.strategy.rollFileOver();
            }
        } catch (Exception e2) {
            CommonUtils.logControlledError(this.context, "Failed to record event.", e2);
        }
    }

    public void recordEventSync(final T t) {
        executeSync(new Runnable() { // from class: com.twitter.sdk.android.core.internal.scribe.EventsHandler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                EventsHandler.this.m726x3ec20957(t);
            }
        });
    }

    /* renamed from: lambda$recordEventSync$1$com-twitter-sdk-android-core-internal-scribe-EventsHandler  reason: not valid java name */
    public /* synthetic */ void m726x3ec20957(Object obj) {
        try {
            this.strategy.recordEvent(obj);
        } catch (Exception e2) {
            CommonUtils.logControlledError(this.context, "Failed to record event", e2);
        }
    }

    @Override // com.twitter.sdk.android.core.internal.scribe.EventsStorageListener
    public void onRollOver(String str) {
        executeAsync(new Runnable() { // from class: com.twitter.sdk.android.core.internal.scribe.EventsHandler$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                EventsHandler.this.m724xe869dbb0();
            }
        });
    }

    /* renamed from: lambda$onRollOver$2$com-twitter-sdk-android-core-internal-scribe-EventsHandler  reason: not valid java name */
    public /* synthetic */ void m724xe869dbb0() {
        try {
            this.strategy.sendEvents();
        } catch (Exception e2) {
            CommonUtils.logControlledError(this.context, "Failed to send events files.", e2);
        }
    }

    public void disable() {
        executeAsync(new Runnable() { // from class: com.twitter.sdk.android.core.internal.scribe.EventsHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EventsHandler.this.m723xd9e877b1();
            }
        });
    }

    /* renamed from: lambda$disable$3$com-twitter-sdk-android-core-internal-scribe-EventsHandler  reason: not valid java name */
    public /* synthetic */ void m723xd9e877b1() {
        try {
            EventsStrategy<T> eventsStrategy = this.strategy;
            this.strategy = getDisabledEventsStrategy();
            eventsStrategy.deleteAllEvents();
        } catch (Exception e2) {
            CommonUtils.logControlledError(this.context, "Failed to disable events.", e2);
        }
    }

    protected void executeSync(Runnable runnable) {
        try {
            this.executor.submit(runnable).get();
        } catch (Exception e2) {
            CommonUtils.logControlledError(this.context, "Failed to run events task", e2);
        }
    }

    protected void executeAsync(Runnable runnable) {
        try {
            this.executor.submit(runnable);
        } catch (Exception e2) {
            CommonUtils.logControlledError(this.context, "Failed to submit events task", e2);
        }
    }
}
