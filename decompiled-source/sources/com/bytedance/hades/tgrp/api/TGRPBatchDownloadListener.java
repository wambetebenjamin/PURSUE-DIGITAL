package com.bytedance.hades.tgrp.api;
/* loaded from: classes2.dex */
public interface TGRPBatchDownloadListener {
    void onBatchEnd(int i2, ResInfo[] resInfoArr, ResInfo[] resInfoArr2, int i3, String str);

    void onBatchProgress(int i2, long j2, long j3, double d2, ResInfo[] resInfoArr);

    void onBatchStart(int i2, long j2, long j3, ResInfo[] resInfoArr, ResInfo[] resInfoArr2);
}
