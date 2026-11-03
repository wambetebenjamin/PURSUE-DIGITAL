package com.bytedance.ttgame.channelapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.bytedance.ttgame.channelapi.pojo.SwitchInfo;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface IChannelAccount {
    default void generalAction(JSONObject jSONObject) {
    }

    boolean isTTSdkAccount();

    void login(Activity activity, String str, IChannelCallback<Map<String, Object>> iChannelCallback);

    default void onActivityResult(int i2, int i3, Intent intent) {
    }

    default void init(Context context, String str, IChannelCallback<String> iChannelCallback) {
        iChannelCallback.onResult(0, "");
    }

    default void initOnHomeActivity(Activity activity, IChannelCallback<String> iChannelCallback) {
        iChannelCallback.onResult(0, "");
    }

    default void logout(IChannelCallback<Map<String, Object>> iChannelCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("code", 0);
        iChannelCallback.onResult(0, hashMap);
    }

    default void switchLogin(Activity activity, SwitchInfo switchInfo, IChannelCallback<String> iChannelCallback) {
        iChannelCallback.onResult(0, "");
    }

    default void afterLogout(Context context, IChannelCallback<String> iChannelCallback) {
        iChannelCallback.onResult(0, "");
    }

    default void registerGeneralCallback(IChannelCallback<JSONObject> iChannelCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", Integer.MIN_VALUE);
            jSONObject.put("payload", "just test");
            iChannelCallback.onResult(0, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
