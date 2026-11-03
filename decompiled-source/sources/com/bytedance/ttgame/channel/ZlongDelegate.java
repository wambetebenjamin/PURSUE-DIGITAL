package com.bytedance.ttgame.channel;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channelapi.IChannelCallback;
import com.bytedance.ttgame.channelapi.IChannelZlong;
import com.bytedance.ttgame.rocketapi.account.IResultCallback;
import java.util.Map;
/* loaded from: classes3.dex */
public class ZlongDelegate {
    private static final String TAG = "gsdk_account_zlong_delegate";
    private IChannelZlong realChannel;

    public ZlongDelegate(IChannelZlong iChannelZlong) {
        this.realChannel = iChannelZlong;
    }

    public String getDeviceId(Context context) {
        return this.realChannel.getDeviceId(context);
    }

    public String getChannelID() {
        return this.realChannel.getChannelID();
    }

    public String getECID() {
        return this.realChannel.getECID();
    }

    public void gameStarted(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, int i3, String str8, Long l) {
        this.realChannel.gameStarted(str, str2, str3, str4, str5, str6, i2, str7, i3, str8, l);
    }

    public void sendGameEventLog(String str, String str2) {
        this.realChannel.sendGameEventLog(str, str2);
    }

    public void showUserCenter() {
        this.realChannel.showUserCenter();
    }

    public String sendExtAction(String str, String str2, final IResultCallback<Map<String, Object>> iResultCallback) {
        return this.realChannel.sendExtAction(str, str2, new IChannelCallback<Map<String, Object>>() { // from class: com.bytedance.ttgame.channel.ZlongDelegate.1
            @Override // com.bytedance.ttgame.channelapi.IChannelCallback
            public void onResult(int i2, Map<String, Object> map) {
                ZlongDelegate.this.handleResult(i2, map, iResultCallback);
            }
        });
    }

    public void startQRLoginPC(String str, final IResultCallback<Map<String, Object>> iResultCallback) {
        this.realChannel.startQRLoginPC(str, new IChannelCallback<Map<String, Object>>() { // from class: com.bytedance.ttgame.channel.ZlongDelegate.2
            @Override // com.bytedance.ttgame.channelapi.IChannelCallback
            public void onResult(int i2, Map<String, Object> map) {
                ZlongDelegate.this.handleResult(i2, map, iResultCallback);
            }
        });
    }

    public void getNoticeState(int i2, String str, final IResultCallback<Map<String, Object>> iResultCallback) {
        this.realChannel.getNoticeState(i2, str, new IChannelCallback<Map<String, Object>>() { // from class: com.bytedance.ttgame.channel.ZlongDelegate.3
            @Override // com.bytedance.ttgame.channelapi.IChannelCallback
            public void onResult(int i3, Map<String, Object> map) {
                ZlongDelegate.this.handleResult(i3, map, iResultCallback);
            }
        });
    }

    public void rewardsPurchaseOutside(Activity activity, String str, String str2) {
        this.realChannel.rewardsPurchaseOutside(activity, str, str2);
    }

    public void initBBS(String str) {
        this.realChannel.initBBS(str);
    }

    public void openBBS(int i2, String str, String str2, final IResultCallback<Map<String, Object>> iResultCallback) {
        this.realChannel.openBBS(i2, str, str2, new IChannelCallback<Map<String, Object>>() { // from class: com.bytedance.ttgame.channel.ZlongDelegate.4
            @Override // com.bytedance.ttgame.channelapi.IChannelCallback
            public void onResult(int i3, Map<String, Object> map) {
                ZlongDelegate.this.handleResult(i3, map, iResultCallback);
            }
        });
    }

    public void registerRewardsOutside(final IResultCallback<Map<String, Object>> iResultCallback) {
        this.realChannel.registerRewardsOutside(new IChannelCallback<Map<String, Object>>() { // from class: com.bytedance.ttgame.channel.ZlongDelegate.5
            @Override // com.bytedance.ttgame.channelapi.IChannelCallback
            public void onResult(int i2, Map<String, Object> map) {
                ZlongDelegate.this.handleResult(i2, map, iResultCallback);
            }
        });
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.realChannel.onRequestPermissionsResult(i2, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(int i2, Map<String, Object> map, IResultCallback<Map<String, Object>> iResultCallback) {
        if (i2 == 0) {
            iResultCallback.onResultSuccess(map);
            return;
        }
        Object obj = map.get("message");
        String str = obj instanceof String ? (String) obj : "";
        iResultCallback.onResultFailed(new GSDKError(i2, str, i2, str));
    }
}
