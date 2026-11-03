package com.bytedance.hades.tgrp.api;
/* loaded from: classes2.dex */
public interface TGRPGlobalDownloadListener {
    void onAddToDownloadQueue(ResInfo[] resInfoArr);

    void onAllResDownloadSuccess();

    void onDownloadQueueEmpty(ResInfo[] resInfoArr, ResInfo[] resInfoArr2, int i2, String str);

    void onProgress(long j2, long j3, double d2);
}
