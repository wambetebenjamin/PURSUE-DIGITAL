package com.bytedance.apm.agent.v2.instrumentation;

import android.text.TextUtils;
import com.bytedance.apm.agent.helper.c;
import com.bytedance.apm.agent.tracing.AutoPageTraceHelper;
import com.bytedance.apm.agent.v2.a;
/* loaded from: classes.dex */
public class ActivityAgent {
    private static final String TAG = "ActivityInstrumentation";

    public static void onTrace(String str, String str2, boolean z) {
        if (z && a.sUiSwitch && TextUtils.equals("onResume", str2)) {
            c.onPageShowHideAction(str, true);
        }
        if (a.sPageLoadSwitch) {
            AutoPageTraceHelper.onTrace(str, str2, z);
        }
    }
}
