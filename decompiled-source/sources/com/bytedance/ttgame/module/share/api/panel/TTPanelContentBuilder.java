package com.bytedance.ttgame.module.share.api.panel;

import android.app.Activity;
import com.bytedance.ttgame.module.share.api.TTPanelItemType;
import com.bytedance.ttgame.module.share.api.callback.TTPanelItemsCallback;
import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
import java.util.HashMap;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class TTPanelContentBuilder {
    private TTPanelContent panelContent;

    public TTPanelContentBuilder(Activity activity) {
        TTPanelContent tTPanelContent = new TTPanelContent();
        this.panelContent = tTPanelContent;
        tTPanelContent.activity = activity;
    }

    public TTPanelContentBuilder withPanelActionCallback(OnTTPanelActionCallback onTTPanelActionCallback) {
        this.panelContent.onPanelActionCallback = onTTPanelActionCallback;
        return this;
    }

    public TTPanelContentBuilder withShareContent(TTShareModel tTShareModel) {
        this.panelContent.shareContent = tTShareModel;
        return this;
    }

    public TTPanelContentBuilder withPanelId(String str) {
        this.panelContent.panelId = str;
        return this;
    }

    private TTPanelContentBuilder withResourceId(String str) {
        this.panelContent.resourceId = str;
        return this;
    }

    private TTPanelContentBuilder withData(JSONObject jSONObject) {
        this.panelContent.data = jSONObject;
        return this;
    }

    public TTPanelContentBuilder withDisableGetShareInfo(boolean z) {
        this.panelContent.disableGetShareInfo = z;
        return this;
    }

    private TTPanelContentBuilder withPanelItemsCallback(TTPanelItemsCallback tTPanelItemsCallback) {
        this.panelContent.panelItemsCallback = tTPanelItemsCallback;
        return this;
    }

    public TTPanelContentBuilder withIconResources(HashMap<TTPanelItemType, Integer> hashMap) {
        this.panelContent.iconResources = hashMap;
        return this;
    }

    public TTPanelContent build() {
        return this.panelContent;
    }
}
