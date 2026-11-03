package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.TimeUtils;
/* loaded from: classes9.dex */
public class RecordUserAction {
    public static void record(String str) {
        UmaRecorderHolder.get().recordUserAction(str, TimeUtils.elapsedRealtimeMillis());
    }
}
