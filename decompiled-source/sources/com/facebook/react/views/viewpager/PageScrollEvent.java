package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes8.dex */
class PageScrollEvent extends Event<PageScrollEvent> {
    public static final String EVENT_NAME = "topPageScroll";
    private final float mOffset;
    private final int mPosition;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topPageScroll";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PageScrollEvent(int i2, int i3, float f2) {
        super(i2);
        this.mPosition = i3;
        this.mOffset = (Float.isInfinite(f2) || Float.isNaN(f2)) ? 0.0f : 0.0f;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(ViewProps.POSITION, this.mPosition);
        createMap.putDouble("offset", this.mOffset);
        return createMap;
    }
}
