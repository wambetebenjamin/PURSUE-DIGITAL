package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes8.dex */
public class ReactTextChangedEvent extends Event<ReactTextChangedEvent> {
    public static final String EVENT_NAME = "topChange";
    private int mEventCount;
    private String mText;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topChange";
    }

    public ReactTextChangedEvent(int i2, String str, int i3) {
        super(i2);
        this.mText = str;
        this.mEventCount = i3;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("text", this.mText);
        createMap.putInt("eventCount", this.mEventCount);
        createMap.putInt("target", getViewTag());
        return createMap;
    }
}
