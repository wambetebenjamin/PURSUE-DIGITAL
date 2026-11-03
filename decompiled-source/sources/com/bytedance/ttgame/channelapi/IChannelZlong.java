package com.bytedance.ttgame.channelapi;

import android.app.Activity;
import android.content.Context;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public interface IChannelZlong {
    void gameStarted(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, int i3, String str8, Long l);

    String getChannelID();

    String getDeviceId(Context context);

    String getECID();

    void getNoticeState(int i2, String str, IChannelCallback<Map<String, Object>> iChannelCallback);

    void getProductsList(IChannelCallback<List<Object>> iChannelCallback);

    void initBBS(String str);

    void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr);

    void openBBS(int i2, String str, String str2, IChannelCallback<Map<String, Object>> iChannelCallback);

    void registerRewardsOutside(IChannelCallback<Map<String, Object>> iChannelCallback);

    void rewardsPurchaseOutside(Activity activity, String str, String str2);

    String sendExtAction(String str, String str2, IChannelCallback<Map<String, Object>> iChannelCallback);

    void sendGameEventLog(String str, String str2);

    void showUserCenter();

    void startQRLoginPC(String str, IChannelCallback<Map<String, Object>> iChannelCallback);
}
