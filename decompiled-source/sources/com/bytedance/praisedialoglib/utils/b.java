package com.bytedance.praisedialoglib.utils;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: EventReportUtil.java */
/* loaded from: classes2.dex */
public class b {
    public static final String EVENT_NAME_JUMP_MARKET = "evaluate_jump_market";
    public static final String EVENT_NAME_POP_BAD = "evaluate_pop_bad";
    public static final String EVENT_NAME_POP_CLOSE = "evaluate_pop_close";
    public static final String EVENT_NAME_POP_FORCE_CLOSE = "evaluate_pop_force_close";
    public static final String EVENT_NAME_POP_GOOD = "evaluate_pop_good";
    public static final String EVENT_NAME_POP_SHOW = "evaluate_pop_show";

    /* renamed from: a  reason: collision with root package name */
    private static final String f11182a = "EventReportUtil";

    public static void onEvent(String str, String str2) {
        Log.d(f11182a, "onEvent() called with: event = [" + str + "], from = [" + str2 + "]");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("trigger", str2);
            com.bytedance.praisedialoglib.manager.a.getInstance().onEvent(str, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void onMarketEvent(String str) {
        Log.d(f11182a, "onMarketEvent() called with: pkgName = [" + str + "]");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pkgName", str);
            com.bytedance.praisedialoglib.manager.a.getInstance().onEvent(EVENT_NAME_JUMP_MARKET, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
