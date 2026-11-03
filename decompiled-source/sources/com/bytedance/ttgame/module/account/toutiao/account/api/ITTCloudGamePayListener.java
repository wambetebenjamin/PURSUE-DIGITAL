package com.bytedance.ttgame.module.account.toutiao.account.api;

import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes4.dex */
public interface ITTCloudGamePayListener {
    void onPayEvent(String str, Map<String, String> map);

    void onPayMonitor(String str, int i2, JSONObject jSONObject);

    void onPayResult(int i2, String str, String str2);
}
