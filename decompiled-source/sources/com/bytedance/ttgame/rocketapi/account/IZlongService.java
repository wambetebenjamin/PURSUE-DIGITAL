package com.bytedance.ttgame.rocketapi.account;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.util.Map;
/* loaded from: classes7.dex */
public interface IZlongService extends IModuleApi {
    void gameStarted(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, int i3, String str8, Long l);

    String getChannelID();

    String getDeviceId(Context context);

    String getECID();

    void getNoticeState(int i2, String str, IResultCallback<Map<String, Object>> iResultCallback);

    void initBBS(String str);

    void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr);

    void openBBS(int i2, String str, String str2, IResultCallback<Map<String, Object>> iResultCallback);

    void registerRewardsOutside(IResultCallback<Map<String, Object>> iResultCallback);

    void rewardsPurchaseOutside(Activity activity, String str, String str2);

    String sendExtAction(String str, String str2, IResultCallback<Map<String, Object>> iResultCallback);

    void sendGameEventLog(String str, String str2);

    void showUserCenter();

    void startQRLoginPC(String str, IResultCallback<Map<String, Object>> iResultCallback);
}
