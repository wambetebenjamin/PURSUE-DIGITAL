package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
/* loaded from: classes8.dex */
public interface ModalHostViewManagerInterface<T extends View> {
    void setAnimated(T t, boolean z);

    void setAnimationType(T t, String str);

    void setHardwareAccelerated(T t, boolean z);

    void setIdentifier(T t, int i2);

    void setPresentationStyle(T t, String str);

    void setStatusBarTranslucent(T t, boolean z);

    void setSupportedOrientations(T t, ReadableArray readableArray);

    void setTransparent(T t, boolean z);
}
