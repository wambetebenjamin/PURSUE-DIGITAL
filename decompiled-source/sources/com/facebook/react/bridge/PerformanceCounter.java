package com.facebook.react.bridge;

import java.util.Map;
/* loaded from: classes8.dex */
public interface PerformanceCounter {
    Map<String, Long> getPerformanceCounters();

    void profileNextBatch();
}
