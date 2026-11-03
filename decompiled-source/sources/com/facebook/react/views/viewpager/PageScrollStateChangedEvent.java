package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes8.dex */
class PageScrollStateChangedEvent extends Event<PageScrollStateChangedEvent> {
    public static final String EVENT_NAME = "topPageScrollStateChanged";
    private final String mPageScrollState;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topPageScrollStateChanged";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PageScrollStateChangedEvent(int i2, String str) {
        super(i2);
        this.mPageScrollState = str;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("pageScrollState", this.mPageScrollState);
        return createMap;
    }
}
