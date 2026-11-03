package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.Callback;
import com.ttnet.org.chromium.base.TimeUtils;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.MainDex;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
@JNINamespace("base::android")
@MainDex
/* loaded from: classes9.dex */
public final class NativeUmaRecorder implements UmaRecorder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Map<String, Long> mNativeHints = Collections.synchronizedMap(new HashMap());
    private Map<Callback<String>, Long> mUserActionTestingCallbackNativePtrs;

    /* loaded from: classes9.dex */
    public interface Natives {
        long addActionCallbackForTesting(Callback<String> callback);

        long createHistogramSnapshotForTesting();

        void destroyHistogramSnapshotForTesting(long j2);

        int getHistogramTotalCountForTesting(String str, long j2);

        int getHistogramValueCountForTesting(String str, int i2, long j2);

        long recordBooleanHistogram(String str, long j2, boolean z);

        long recordExponentialHistogram(String str, long j2, int i2, int i3, int i4, int i5);

        long recordLinearHistogram(String str, long j2, int i2, int i3, int i4, int i5);

        long recordSparseHistogram(String str, long j2, int i2);

        void recordUserAction(String str, long j2);

        void removeActionCallbackForTesting(long j2);
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordBooleanHistogram(String str, boolean z) {
        long nativeHint = getNativeHint(str);
        maybeUpdateNativeHint(str, nativeHint, NativeUmaRecorderJni.get().recordBooleanHistogram(str, nativeHint, z));
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordExponentialHistogram(String str, int i2, int i3, int i4, int i5) {
        long nativeHint = getNativeHint(str);
        maybeUpdateNativeHint(str, nativeHint, NativeUmaRecorderJni.get().recordExponentialHistogram(str, nativeHint, i2, i3, i4, i5));
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordLinearHistogram(String str, int i2, int i3, int i4, int i5) {
        long nativeHint = getNativeHint(str);
        maybeUpdateNativeHint(str, nativeHint, NativeUmaRecorderJni.get().recordLinearHistogram(str, nativeHint, i2, i3, i4, i5));
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordSparseHistogram(String str, int i2) {
        long nativeHint = getNativeHint(str);
        maybeUpdateNativeHint(str, nativeHint, NativeUmaRecorderJni.get().recordSparseHistogram(str, nativeHint, i2));
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordUserAction(String str, long j2) {
        NativeUmaRecorderJni.get().recordUserAction(str, TimeUtils.elapsedRealtimeMillis() - j2);
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public int getHistogramValueCountForTesting(String str, int i2) {
        return NativeUmaRecorderJni.get().getHistogramValueCountForTesting(str, i2, 0L);
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public int getHistogramTotalCountForTesting(String str) {
        return NativeUmaRecorderJni.get().getHistogramTotalCountForTesting(str, 0L);
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void addUserActionCallbackForTesting(Callback<String> callback) {
        long addActionCallbackForTesting = NativeUmaRecorderJni.get().addActionCallbackForTesting(callback);
        if (this.mUserActionTestingCallbackNativePtrs == null) {
            this.mUserActionTestingCallbackNativePtrs = Collections.synchronizedMap(new HashMap());
        }
        this.mUserActionTestingCallbackNativePtrs.put(callback, Long.valueOf(addActionCallbackForTesting));
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void removeUserActionCallbackForTesting(Callback<String> callback) {
        Long remove;
        Map<Callback<String>, Long> map = this.mUserActionTestingCallbackNativePtrs;
        if (map == null || (remove = map.remove(callback)) == null) {
            return;
        }
        NativeUmaRecorderJni.get().removeActionCallbackForTesting(remove.longValue());
    }

    private long getNativeHint(String str) {
        Long l = this.mNativeHints.get(str);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    private void maybeUpdateNativeHint(String str, long j2, long j3) {
        if (j2 != j3) {
            this.mNativeHints.put(str, Long.valueOf(j3));
        }
    }
}
