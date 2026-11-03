package com.facebook.react.uimanager.events;

import com.facebook.react.common.SystemClock;
import com.facebook.react.uimanager.events.Event;
/* loaded from: classes8.dex */
public abstract class Event<T extends Event> {
    private static int sUniqueID;
    private boolean mInitialized;
    private long mTimestampMs;
    private int mUniqueID;
    private int mViewTag;

    public boolean canCoalesce() {
        return true;
    }

    public abstract void dispatch(RCTEventEmitter rCTEventEmitter);

    public short getCoalescingKey() {
        return (short) 0;
    }

    public abstract String getEventName();

    public void onDispose() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Event() {
        int i2 = sUniqueID;
        sUniqueID = i2 + 1;
        this.mUniqueID = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Event(int i2) {
        int i3 = sUniqueID;
        sUniqueID = i3 + 1;
        this.mUniqueID = i3;
        init(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(int i2) {
        this.mViewTag = i2;
        this.mTimestampMs = SystemClock.uptimeMillis();
        this.mInitialized = true;
    }

    public final int getViewTag() {
        return this.mViewTag;
    }

    public final long getTimestampMs() {
        return this.mTimestampMs;
    }

    public T coalesce(T t) {
        return getTimestampMs() >= t.getTimestampMs() ? this : t;
    }

    public int getUniqueID() {
        return this.mUniqueID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInitialized() {
        return this.mInitialized;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void dispose() {
        this.mInitialized = false;
        onDispose();
    }
}
