package com.bytedance.ttgame.module.share.api.panel;

import com.bytedance.ttgame.module.share.api.entity.TTShareResult;
/* loaded from: classes7.dex */
public interface OnTTPanelActionCallback {

    /* loaded from: classes7.dex */
    public static class EmptyPanelActionCallback implements OnTTPanelActionCallback {
        @Override // com.bytedance.ttgame.module.share.api.panel.OnTTPanelActionCallback
        public void onPanelClick(ITTPanelItem iTTPanelItem) {
        }

        @Override // com.bytedance.ttgame.module.share.api.panel.OnTTPanelActionCallback
        public void onPanelDismiss(boolean z) {
        }

        @Override // com.bytedance.ttgame.module.share.api.panel.OnTTPanelActionCallback
        public void onPanelDismissByShareCanel(TTShareResult tTShareResult) {
        }

        @Override // com.bytedance.ttgame.module.share.api.panel.OnTTPanelActionCallback
        public void onPanelShow() {
        }
    }

    void onPanelClick(ITTPanelItem iTTPanelItem);

    void onPanelDismiss(boolean z);

    default void onPanelDismissByShareCanel(TTShareResult tTShareResult) {
    }

    void onPanelShow();
}
