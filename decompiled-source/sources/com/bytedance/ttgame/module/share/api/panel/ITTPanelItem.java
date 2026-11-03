package com.bytedance.ttgame.module.share.api.panel;

import com.bytedance.ttgame.module.share.api.TTPanelItemType;
import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
/* loaded from: classes7.dex */
public interface ITTPanelItem {
    int getIconId();

    String getIconUrl();

    TTPanelItemType getItemType();

    int getTextId();

    String getTextStr();

    void onItemClick(TTPanelItemViewHolder tTPanelItemViewHolder, TTPanelItemType tTPanelItemType, TTShareModel tTShareModel);

    void setItemView(TTPanelItemViewHolder tTPanelItemViewHolder);
}
