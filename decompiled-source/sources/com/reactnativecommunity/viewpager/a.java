package com.reactnativecommunity.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* compiled from: PageScrollEvent.java */
/* loaded from: classes9.dex */
class a extends Event<a> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17784a = "topPageScroll";

    /* renamed from: b  reason: collision with root package name */
    private final int f17785b;

    /* renamed from: c  reason: collision with root package name */
    private final float f17786c;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topPageScroll";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(int i2, int i3, float f2) {
        super(i2);
        this.f17785b = i3;
        this.f17786c = (Float.isInfinite(f2) || Float.isNaN(f2)) ? 0.0f : 0.0f;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(ViewProps.POSITION, this.f17785b);
        createMap.putDouble("offset", this.f17786c);
        return createMap;
    }
}
