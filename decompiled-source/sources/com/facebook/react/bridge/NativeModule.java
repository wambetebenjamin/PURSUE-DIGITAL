package com.facebook.react.bridge;
/* loaded from: classes8.dex */
public interface NativeModule {

    /* loaded from: classes8.dex */
    public interface NativeMethod {
        String getType();

        void invoke(JSInstance jSInstance, ReadableArray readableArray);
    }

    boolean canOverrideExistingModule();

    String getName();

    void initialize();

    void onCatalystInstanceDestroy();
}
