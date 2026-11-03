package com.facebook.react.modules.debug.interfaces;
/* loaded from: classes8.dex */
public interface DeveloperSettings {
    void addMenuItem(String str);

    boolean isAnimationFpsDebugEnabled();

    boolean isElementInspectorEnabled();

    boolean isFpsDebugEnabled();

    boolean isJSDevModeEnabled();

    boolean isJSMinifyEnabled();

    boolean isNuclideJSDebugEnabled();

    boolean isRemoteJSDebugEnabled();

    boolean isStartSamplingProfilerOnInit();

    void setRemoteJSDebugEnabled(boolean z);
}
