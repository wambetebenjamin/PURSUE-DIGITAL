package com.bytedance.ttgame.channelapi;

import android.app.Activity;
import java.util.HashMap;
/* loaded from: classes3.dex */
public interface IChannelTanwan {
    void eventReport(Activity activity, String str, HashMap<String, Object> hashMap);

    void eventTrack(Activity activity, String str, HashMap<String, Object> hashMap);

    void sendGameEventLog(String str, String str2, String str3, String str4, String str5, String str6, int i2);

    void ttEventReport(Activity activity, String str, HashMap<String, Object> hashMap);
}
