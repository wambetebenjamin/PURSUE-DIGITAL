package com.bytedance.ug.sdk.share.api.callback;

import java.util.List;
/* compiled from: PanelItemsCallback.java */
/* loaded from: classes8.dex */
public interface j {

    /* compiled from: PanelItemsCallback.java */
    /* loaded from: classes8.dex */
    public static abstract class a implements j {
        @Override // com.bytedance.ug.sdk.share.api.callback.j
        public void resetPanelItem(com.bytedance.ug.sdk.share.impl.ui.panel.c cVar, List<List<com.bytedance.ug.sdk.share.api.panel.a>> list) {
        }

        @Override // com.bytedance.ug.sdk.share.api.callback.j
        public void resetPanelItemOriginalData(com.bytedance.ug.sdk.share.api.entity.h hVar) {
        }

        @Override // com.bytedance.ug.sdk.share.api.callback.j
        public void resetPanelItemServerData(com.bytedance.ug.sdk.share.api.entity.h hVar) {
        }
    }

    void resetPanelItem(com.bytedance.ug.sdk.share.impl.ui.panel.c cVar, List<List<com.bytedance.ug.sdk.share.api.panel.a>> list);

    void resetPanelItemOriginalData(com.bytedance.ug.sdk.share.api.entity.h hVar);

    void resetPanelItemServerData(com.bytedance.ug.sdk.share.api.entity.h hVar);
}
