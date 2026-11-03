package com.facebook.react;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.List;
/* loaded from: classes8.dex */
public interface ViewManagerOnDemandReactPackage {
    ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str);

    List<String> getViewManagerNames(ReactApplicationContext reactApplicationContext);
}
