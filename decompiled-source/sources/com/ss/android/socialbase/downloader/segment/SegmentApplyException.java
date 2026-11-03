package com.ss.android.socialbase.downloader.segment;

import com.ss.android.socialbase.downloader.exception.BaseException;
/* loaded from: classes9.dex */
class SegmentApplyException extends BaseException {
    public static final int ALREADY_APPLY_BY_OTHER = 1;
    public static final int BAD_SEGMENT = 6;
    public static final int CHANGE_SEGMENT = 5;
    public static final int PREV_END_ADJUST_FAIL = 4;
    public static final int PREV_OVERSTEP = 3;
    public static final int SEGMENT_NOT_EXIST = 2;
    private int applyCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SegmentApplyException(int i2, String str) {
        super(1072, "applyCode=" + i2 + ", " + str);
        this.applyCode = i2;
    }

    public int getSegmentApplyErrorCode() {
        return this.applyCode;
    }
}
