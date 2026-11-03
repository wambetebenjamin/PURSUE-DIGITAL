package com.facebook.react.animated;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;
/* loaded from: classes8.dex */
class EventAnimationDriver implements RCTEventEmitter {
    private List<String> mEventPath;
    ValueAnimatedNode mValueNode;

    public EventAnimationDriver(List<String> list, ValueAnimatedNode valueAnimatedNode) {
        this.mEventPath = list;
        this.mValueNode = valueAnimatedNode;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i2, String str, WritableMap writableMap) {
        if (writableMap == null) {
            throw new IllegalArgumentException("Native animated events must have event data.");
        }
        int i3 = 0;
        WritableMap writableMap2 = writableMap;
        while (i3 < this.mEventPath.size() - 1) {
            i3++;
            writableMap2 = writableMap2.getMap(this.mEventPath.get(i3));
        }
        ValueAnimatedNode valueAnimatedNode = this.mValueNode;
        List<String> list = this.mEventPath;
        valueAnimatedNode.mValue = writableMap2.getDouble(list.get(list.size() - 1));
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
