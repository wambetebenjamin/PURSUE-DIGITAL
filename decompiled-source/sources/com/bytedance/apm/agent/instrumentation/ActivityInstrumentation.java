package com.bytedance.apm.agent.instrumentation;

import android.text.TextUtils;
import java.util.HashSet;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class ActivityInstrumentation {
    private static final String TAG = "ActivityInstrumentation";
    static boolean isFirstWindowFocusChanged = false;
    private static String sActivityName;
    private static HashSet<String> sMethodSet = new HashSet<>(32);
    private static long sOnCreateEnd;
    private static long sOnCreateStart;
    private static long sOnResumeEnd;
    private static long sOnResumeStart;
    private static long sOnWindowFocusedChangeStart;

    static boolean isValid(String str) {
        return !TextUtils.isEmpty(sActivityName) && TextUtils.equals(str, sActivityName);
    }

    public static void onTrace(String str, String str2, boolean z) {
        if (TextUtils.equals("onCreate", str2)) {
            if (z) {
                sActivityName = str;
                sOnCreateStart = System.currentTimeMillis();
                if (isFirstWindowFocusChanged) {
                    return;
                }
                AppInstrumentation.launcherActivityOnCreateStart(sActivityName);
                return;
            } else if (isValid(str)) {
                sOnCreateEnd = System.currentTimeMillis();
                if (isFirstWindowFocusChanged) {
                    return;
                }
                AppInstrumentation.launcherActivityOnCreateEnd();
                return;
            } else {
                return;
            }
        }
        boolean z2 = true;
        if (TextUtils.equals("onResume", str2) && isValid(str)) {
            if (z) {
                if (sOnCreateStart > 0) {
                    sOnResumeStart = System.currentTimeMillis();
                    if (!isFirstWindowFocusChanged) {
                        AppInstrumentation.launcherActivityOnResumeStart(sActivityName);
                    }
                    com.bytedance.apm.agent.helper.d.onPageShowHideAction(sActivityName, true);
                }
            } else if (sOnCreateStart > 0) {
                sOnResumeEnd = System.currentTimeMillis();
                if (isFirstWindowFocusChanged) {
                    return;
                }
                AppInstrumentation.launcherActivityOnResumeEnd();
            }
        } else if (TextUtils.equals(com.bytedance.apm.constant.a.ON_WINDOW_FOCUS_CHANGED, str2) && isValid(str) && sOnCreateStart > 0) {
            if (!isFirstWindowFocusChanged) {
                AppInstrumentation.launcherActivityOnWindowFocusChangedStart(sActivityName);
                isFirstWindowFocusChanged = true;
            }
            long currentTimeMillis = System.currentTimeMillis();
            sOnWindowFocusedChangeStart = currentTimeMillis;
            if (currentTimeMillis - sOnCreateStart < 5000) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(com.bytedance.apm.constant.a.BEGIN_TIME, sOnCreateStart);
                    jSONObject.put("end_time", sOnWindowFocusedChangeStart);
                    jSONObject.put("page_type", "activity");
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("onCreate", sOnCreateEnd - sOnCreateStart);
                    jSONObject2.put("onResume", sOnResumeEnd - sOnResumeStart);
                    jSONObject2.put("activityOnCreateToWindowFocusChanged", sOnWindowFocusedChangeStart - sOnCreateStart);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("scene", sActivityName);
                    if (sMethodSet.contains(sActivityName + str2)) {
                        z2 = false;
                    }
                    jSONObject3.put("is_first", z2);
                    sMethodSet.add(sActivityName + str2);
                    com.bytedance.apm.agent.monitor.a.monitorPerformance("page_load", jSONObject2, jSONObject3, jSONObject);
                } catch (Exception unused) {
                }
            }
            sOnCreateStart = 0L;
            sActivityName = null;
        }
    }
}
