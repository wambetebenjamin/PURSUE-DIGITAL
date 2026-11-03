package com.bytedance.crash.terminate;

import android.app.ApplicationExitInfo;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.List;
import org.json.JSONObject;
/* compiled from: AnrHistoryProcessExitParser.java */
/* loaded from: classes2.dex */
public class a {
    public static void a(JSONObject jSONObject) {
        try {
            List<ApplicationExitInfo> b2 = c.b();
            if (b2 == null) {
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("custom");
            long optLong = jSONObject.optLong("crash_time");
            int optInt = jSONObject.optInt(com.bytedance.crash.entity.b.PID);
            String optString = jSONObject.optString("process_name");
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            for (ApplicationExitInfo applicationExitInfo : b2) {
                if (applicationExitInfo.getProcessName().equals(optString) && optInt == applicationExitInfo.getPid()) {
                    long timestamp = applicationExitInfo.getTimestamp() - optLong;
                    if (timestamp > 0 && timestamp <= 30000) {
                        if (applicationExitInfo.getReason() == 6) {
                            sb3.append(applicationExitInfo.getDescription() + ",");
                        }
                        sb.append(applicationExitInfo.getReason() + ",");
                        sb2.append("timestamp=" + applicationExitInfo.getTimestamp());
                        sb2.append(" crash_pid=" + optInt);
                        sb2.append(" crash_time=" + optLong + " \n");
                        sb2.append(applicationExitInfo);
                        sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    }
                }
            }
            if (sb2.length() > 0) {
                optJSONObject.put("history_exit_process_list", sb2);
            }
            jSONObject.put(com.bytedance.crash.constants.c.E, sb);
            if (sb3.length() > 0) {
                jSONObject.put(com.bytedance.crash.constants.c.D, sb3);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            String optString = jSONObject.optString(com.bytedance.crash.constants.c.E);
            if (!TextUtils.isEmpty(optString)) {
                jSONObject2.put(com.bytedance.crash.constants.c.E, optString);
            }
            String optString2 = jSONObject.optString(com.bytedance.crash.constants.c.D);
            if (TextUtils.isEmpty(optString2)) {
                return;
            }
            jSONObject2.put(com.bytedance.crash.constants.c.D, optString2);
        } catch (Throwable unused) {
        }
    }
}
