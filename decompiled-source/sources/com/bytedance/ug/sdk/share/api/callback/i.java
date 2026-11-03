package com.bytedance.ug.sdk.share.api.callback;
/* compiled from: OnPanelActionCallback.java */
/* loaded from: classes8.dex */
public interface i {

    /* compiled from: OnPanelActionCallback.java */
    /* loaded from: classes8.dex */
    public static class a implements i {
        @Override // com.bytedance.ug.sdk.share.api.callback.i
        public boolean interceptPanelClick(com.bytedance.ug.sdk.share.api.panel.a aVar, com.bytedance.ug.sdk.share.api.entity.h hVar, d dVar) {
            return false;
        }

        @Override // com.bytedance.ug.sdk.share.api.callback.i
        public void onPanelClick(com.bytedance.ug.sdk.share.api.panel.a aVar) {
        }

        @Override // com.bytedance.ug.sdk.share.api.callback.i
        public void onPanelDismiss(boolean z) {
        }

        @Override // com.bytedance.ug.sdk.share.api.callback.i
        public void onPanelShow() {
        }
    }

    boolean interceptPanelClick(com.bytedance.ug.sdk.share.api.panel.a aVar, com.bytedance.ug.sdk.share.api.entity.h hVar, d dVar);

    void onPanelClick(com.bytedance.ug.sdk.share.api.panel.a aVar);

    void onPanelDismiss(boolean z);

    void onPanelShow();
}
