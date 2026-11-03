package com.bytedance.apm.report;

import android.text.TextUtils;
import com.bytedance.apm.c;
/* compiled from: ExtendHeaderHelper.java */
/* loaded from: classes.dex */
public class a {
    public static final String TEST_RUNTIME_KEY = "test_runtime";

    /* renamed from: a  reason: collision with root package name */
    private static String f1424a;

    public static String getTestInfoPropPath() {
        if (TextUtils.isEmpty(f1424a)) {
            try {
                f1424a = c.getContext().getExternalFilesDir(null).getParentFile().getAbsolutePath() + "/AutomationTestInfo.json";
            } catch (Throwable unused) {
            }
        }
        return f1424a;
    }

    public static void setTestInfoPropPath(String str) {
        f1424a = str;
    }
}
