package com.bytedance.apm.entity;
/* compiled from: UploadInfo.java */
/* loaded from: classes.dex */
public class l {
    public static final long DEFAULT_SEND_DURATION = 600000;
    public String mKey;
    public long mLastSendTime;
    public boolean mUploading = false;

    public l(String str, long j2) {
        this.mKey = str;
        this.mLastSendTime = j2;
    }

    public void setUploading(boolean z) {
        this.mUploading = z;
    }
}
