package gsdk.impl.dynamic.DEFAULT;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* compiled from: ItemSelectedEvent.java */
/* loaded from: classes10.dex */
public class au extends Event<au> {

    /* renamed from: a  reason: collision with root package name */
    private final int f20118a;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "wheelCurvedPickerPageSelected";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public au(int i2, int i3) {
        super(i2);
        this.f20118a = i3;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), a());
    }

    private WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("data", this.f20118a);
        return createMap;
    }
}
