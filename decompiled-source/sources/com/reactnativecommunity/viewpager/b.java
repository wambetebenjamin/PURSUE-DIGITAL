package com.reactnativecommunity.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* compiled from: PageScrollStateChangedEvent.java */
/* loaded from: classes9.dex */
class b extends Event<b> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17787a = "topPageScrollStateChanged";

    /* renamed from: b  reason: collision with root package name */
    private final String f17788b;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topPageScrollStateChanged";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b(int i2, String str) {
        super(i2);
        this.f17788b = str;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("pageScrollState", this.f17788b);
        return createMap;
    }
}
