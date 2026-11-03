package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.Callback;
/* loaded from: classes9.dex */
class NoopUmaRecorder implements UmaRecorder {
    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void addUserActionCallbackForTesting(Callback<String> callback) {
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public int getHistogramTotalCountForTesting(String str) {
        return 0;
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public int getHistogramValueCountForTesting(String str, int i2) {
        return 0;
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordBooleanHistogram(String str, boolean z) {
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordExponentialHistogram(String str, int i2, int i3, int i4, int i5) {
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordLinearHistogram(String str, int i2, int i3, int i4, int i5) {
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordSparseHistogram(String str, int i2) {
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordUserAction(String str, long j2) {
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void removeUserActionCallbackForTesting(Callback<String> callback) {
    }

    NoopUmaRecorder() {
    }
}
