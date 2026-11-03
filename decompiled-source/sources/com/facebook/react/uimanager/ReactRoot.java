package com.facebook.react.uimanager;

import android.os.Bundle;
import android.view.ViewGroup;
/* loaded from: classes8.dex */
public interface ReactRoot {
    Bundle getAppProperties();

    int getHeightMeasureSpec();

    String getInitialUITemplate();

    String getJSModuleName();

    ViewGroup getRootViewGroup();

    int getRootViewTag();

    @Deprecated
    String getSurfaceID();

    int getUIManagerType();

    int getWidthMeasureSpec();

    void onStage(int i2);

    void runApplication();

    void setRootViewTag(int i2);

    void setShouldLogContentAppeared(boolean z);
}
