package com.ss.android.socialbase.downloader.impls;

import android.text.TextUtils;
import com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
/* loaded from: classes9.dex */
public class RetryDelayTimeParamCalculator implements IRetryDelayTimeCalculator {
    private final long[] mTimeArray;

    public RetryDelayTimeParamCalculator(String str) {
        this.mTimeArray = parseTimeArray(str);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator
    public long calculateRetryDelayTime(int i2, int i3) {
        long[] jArr = this.mTimeArray;
        if (jArr == null || jArr.length <= 0) {
            return 0L;
        }
        int i4 = i2 - 1;
        if (i4 < 0) {
            i4 = 0;
        }
        if (i4 > jArr.length - 1) {
            i4 = jArr.length - 1;
        }
        return jArr[i4];
    }

    private long[] parseTimeArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] split = str.split(",");
            if (split.length == 0) {
                return null;
            }
            long[] jArr = new long[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                jArr[i2] = Long.parseLong(split[i2]);
            }
            return jArr;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
