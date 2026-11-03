package com.bytedance.ttgame.channel;

import android.app.Activity;
import com.bytedance.ttgame.channelapi.IChannelTanwan;
import java.util.HashMap;
/* loaded from: classes3.dex */
public class TanwanDelegate {
    private static final String TAG = "gsdk_tanwan_delegate";
    private IChannelTanwan realChannel;

    public TanwanDelegate(IChannelTanwan iChannelTanwan) {
        this.realChannel = iChannelTanwan;
    }

    public void sendGameEventLog(String str, String str2, String str3, String str4, String str5, String str6, int i2) {
        this.realChannel.sendGameEventLog(str, str2, str3, str4, str5, str6, i2);
    }

    public void eventTrack(Activity activity, String str, HashMap<String, Object> hashMap) {
        this.realChannel.eventTrack(activity, str, hashMap);
    }

    public void eventReport(Activity activity, String str, HashMap<String, Object> hashMap) {
        this.realChannel.eventReport(activity, str, hashMap);
    }

    public void ttEventReport(Activity activity, String str, HashMap<String, Object> hashMap) {
        this.realChannel.ttEventReport(activity, str, hashMap);
    }
}
