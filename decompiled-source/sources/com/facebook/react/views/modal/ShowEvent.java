package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes8.dex */
class ShowEvent extends Event<ShowEvent> {
    public static final String EVENT_NAME = "topShow";

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ShowEvent(int i2) {
        super(i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), null);
    }
}
