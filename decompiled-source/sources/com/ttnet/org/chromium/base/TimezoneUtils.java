package com.ttnet.org.chromium.base;

import android.os.StrictMode;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.MainDex;
import java.util.TimeZone;
@JNINamespace("base::android")
@MainDex
/* loaded from: classes9.dex */
class TimezoneUtils {
    private TimezoneUtils() {
    }

    private static String getDefaultTimeZoneId() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        String id = TimeZone.getDefault().getID();
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return id;
    }
}
