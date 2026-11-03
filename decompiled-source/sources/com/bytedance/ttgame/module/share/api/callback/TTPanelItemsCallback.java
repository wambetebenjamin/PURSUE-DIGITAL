package com.bytedance.ttgame.module.share.api.callback;

import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
import com.bytedance.ttgame.module.share.api.panel.ITTPanelItem;
import java.util.List;
/* loaded from: classes7.dex */
public interface TTPanelItemsCallback {

    /* loaded from: classes7.dex */
    public static abstract class EmptySharePanelItemsCallback implements TTPanelItemsCallback {
        @Override // com.bytedance.ttgame.module.share.api.callback.TTPanelItemsCallback
        public void resetPanelItem(List<ITTPanelItem> list) {
        }

        @Override // com.bytedance.ttgame.module.share.api.callback.TTPanelItemsCallback
        public void resetPanelItemOriginalData(TTShareModel tTShareModel) {
        }

        @Override // com.bytedance.ttgame.module.share.api.callback.TTPanelItemsCallback
        public void resetPanelItemServerData(TTShareModel tTShareModel) {
        }
    }

    void resetPanelItem(List<ITTPanelItem> list);

    void resetPanelItemOriginalData(TTShareModel tTShareModel);

    void resetPanelItemServerData(TTShareModel tTShareModel);
}
