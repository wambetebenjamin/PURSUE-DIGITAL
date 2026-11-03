package com.bytedance.ug.sdk.share.api.callback;
/* compiled from: ExposedPanelItemsCallback.java */
/* loaded from: classes8.dex */
public interface b {

    /* compiled from: ExposedPanelItemsCallback.java */
    /* loaded from: classes8.dex */
    public static abstract class a implements b {
        @Override // com.bytedance.ug.sdk.share.api.callback.b
        public void resetPanelItemOriginalData(com.bytedance.ug.sdk.share.api.entity.h hVar) {
        }

        @Override // com.bytedance.ug.sdk.share.api.callback.b
        public void resetPanelItemServerData(com.bytedance.ug.sdk.share.api.entity.h hVar) {
        }
    }

    void resetPanelItemOriginalData(com.bytedance.ug.sdk.share.api.entity.h hVar);

    void resetPanelItemServerData(com.bytedance.ug.sdk.share.api.entity.h hVar);
}
