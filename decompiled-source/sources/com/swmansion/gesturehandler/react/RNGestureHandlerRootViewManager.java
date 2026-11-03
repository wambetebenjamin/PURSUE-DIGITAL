package com.swmansion.gesturehandler.react;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import java.util.Map;
@ReactModule(name = RNGestureHandlerRootViewManager.REACT_CLASS)
/* loaded from: classes9.dex */
public class RNGestureHandlerRootViewManager extends ViewGroupManager<RNGestureHandlerRootView> {
    public static final String REACT_CLASS = "GestureHandlerRootView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RNGestureHandlerRootView createViewInstance(ThemedReactContext themedReactContext) {
        return new RNGestureHandlerRootView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(RNGestureHandlerRootView rNGestureHandlerRootView) {
        rNGestureHandlerRootView.tearDown();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(RNGestureHandlerEvent.EVENT_NAME, MapBuilder.of("registrationName", RNGestureHandlerEvent.EVENT_NAME), RNGestureHandlerStateChangeEvent.EVENT_NAME, MapBuilder.of("registrationName", RNGestureHandlerStateChangeEvent.EVENT_NAME));
    }
}
