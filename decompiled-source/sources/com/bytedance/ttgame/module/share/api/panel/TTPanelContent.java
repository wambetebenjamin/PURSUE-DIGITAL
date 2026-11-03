package com.bytedance.ttgame.module.share.api.panel;

import android.app.Activity;
import com.bytedance.ttgame.module.share.api.TTPanelItemType;
import com.bytedance.ttgame.module.share.api.callback.TTPanelItemsCallback;
import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
import java.util.HashMap;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class TTPanelContent {
    public Activity activity;
    public boolean disableGetShareInfo;
    public HashMap<TTPanelItemType, Integer> iconResources;
    public OnTTPanelActionCallback onPanelActionCallback;
    public String panelId;
    public TTPanelItemsCallback panelItemsCallback;
    public TTShareModel shareContent;
    public String resourceId = "0";
    public JSONObject data = new JSONObject();
}
