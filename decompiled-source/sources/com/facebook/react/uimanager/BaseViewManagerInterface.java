package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes8.dex */
public interface BaseViewManagerInterface<T extends View> {
    void setAccessibilityActions(T t, ReadableArray readableArray);

    void setAccessibilityHint(T t, String str);

    void setAccessibilityLabel(T t, String str);

    void setAccessibilityLiveRegion(T t, String str);

    void setAccessibilityRole(T t, String str);

    void setBackgroundColor(T t, int i2);

    void setBorderBottomLeftRadius(T t, float f2);

    void setBorderBottomRightRadius(T t, float f2);

    void setBorderRadius(T t, float f2);

    void setBorderTopLeftRadius(T t, float f2);

    void setBorderTopRightRadius(T t, float f2);

    void setElevation(T t, float f2);

    void setImportantForAccessibility(T t, String str);

    void setNativeId(T t, String str);

    void setOpacity(T t, float f2);

    void setRenderToHardwareTexture(T t, boolean z);

    void setRotation(T t, float f2);

    void setScaleX(T t, float f2);

    void setScaleY(T t, float f2);

    void setTestId(T t, String str);

    void setTransform(T t, ReadableArray readableArray);

    void setTranslateX(T t, float f2);

    void setTranslateY(T t, float f2);

    void setViewState(T t, ReadableMap readableMap);

    void setZIndex(T t, float f2);
}
