package com.facebook.react.views.image;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes8.dex */
public class ImageLoadEvent extends Event<ImageLoadEvent> {
    public static final int ON_ERROR = 1;
    public static final int ON_LOAD = 2;
    public static final int ON_LOAD_END = 3;
    public static final int ON_LOAD_START = 4;
    public static final int ON_PROGRESS = 5;
    private final int mEventType;
    private final int mHeight;
    private final String mImageError;
    private final String mImageUri;
    private final int mWidth;

    public ImageLoadEvent(int i2, int i3) {
        this(i2, i3, null);
    }

    public ImageLoadEvent(int i2, int i3, boolean z, String str) {
        this(i2, i3, null, 0, 0, str);
    }

    public ImageLoadEvent(int i2, int i3, String str) {
        this(i2, i3, str, 0, 0, null);
    }

    public ImageLoadEvent(int i2, int i3, String str, int i4, int i5) {
        this(i2, i3, str, i4, i5, null);
    }

    public ImageLoadEvent(int i2, int i3, String str, int i4, int i5, String str2) {
        super(i2);
        this.mEventType = i3;
        this.mImageUri = str;
        this.mWidth = i4;
        this.mHeight = i5;
        this.mImageError = str2;
    }

    public static String eventNameForType(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 == 5) {
                            return "topProgress";
                        }
                        throw new IllegalStateException("Invalid image event: " + Integer.toString(i2));
                    }
                    return "topLoadStart";
                }
                return "topLoadEnd";
            }
            return "topLoad";
        }
        return "topError";
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return eventNameForType(this.mEventType);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) this.mEventType;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap;
        int i2;
        if (this.mImageUri != null || (i2 = this.mEventType) == 2 || i2 == 1) {
            createMap = Arguments.createMap();
            String str = this.mImageUri;
            if (str != null) {
                createMap.putString("uri", str);
            }
            int i3 = this.mEventType;
            if (i3 == 2) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putDouble("width", this.mWidth);
                createMap2.putDouble("height", this.mHeight);
                String str2 = this.mImageUri;
                if (str2 != null) {
                    createMap2.putString("url", str2);
                }
                createMap.putMap("source", createMap2);
            } else if (i3 == 1) {
                createMap.putString("error", this.mImageError);
            }
        } else {
            createMap = null;
        }
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), createMap);
    }
}
