package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.Callback;
/* loaded from: classes9.dex */
public interface UmaRecorder {
    void addUserActionCallbackForTesting(Callback<String> callback);

    int getHistogramTotalCountForTesting(String str);

    int getHistogramValueCountForTesting(String str, int i2);

    void recordBooleanHistogram(String str, boolean z);

    void recordExponentialHistogram(String str, int i2, int i3, int i4, int i5);

    void recordLinearHistogram(String str, int i2, int i3, int i4, int i5);

    void recordSparseHistogram(String str, int i2);

    void recordUserAction(String str, long j2);

    void removeUserActionCallbackForTesting(Callback<String> callback);
}
