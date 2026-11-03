package com.reactnativecommunity.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* compiled from: PageSelectedEvent.java */
/* loaded from: classes9.dex */
class c extends Event<c> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17789a = "topPageSelected";

    /* renamed from: b  reason: collision with root package name */
    private final int f17790b;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topPageSelected";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c(int i2, int i3) {
        super(i2);
        this.f17790b = i3;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(ViewProps.POSITION, this.f17790b);
        return createMap;
    }
}
