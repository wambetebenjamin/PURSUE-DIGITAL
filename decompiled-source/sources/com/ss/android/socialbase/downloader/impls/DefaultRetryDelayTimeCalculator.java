package com.ss.android.socialbase.downloader.impls;

import com.bytedance.apm.constant.r;
import com.bytedance.common.wschannel.WsConstants;
import com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
/* loaded from: classes9.dex */
public class DefaultRetryDelayTimeCalculator implements IRetryDelayTimeCalculator {
    @Override // com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator
    public long calculateRetryDelayTime(int i2, int i3) {
        if (i2 == 1) {
            return WsConstants.EXIT_DELAY_TIME;
        }
        if (i2 == 2) {
            return r.APP_START_MAX_LIMIT_MS;
        }
        if (i2 == 3) {
            return 30000L;
        }
        return i2 > 3 ? 300000L : 0L;
    }
}
