package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.MountingManager;
/* loaded from: classes8.dex */
public class SendAccessibilityEvent implements MountItem {
    private final int mEventType;
    private final int mReactTag;

    public SendAccessibilityEvent(int i2, int i3) {
        this.mReactTag = i2;
        this.mEventType = i3;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.sendAccessibilityEvent(this.mReactTag, this.mEventType);
    }

    public String toString() {
        return "SendAccessibilityEvent [" + this.mReactTag + "] " + this.mEventType;
    }
}
