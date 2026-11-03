package com.bytedance.hades.tgrp.api;
/* loaded from: classes2.dex */
public interface TGRPInitListener {
    void onInitFail(int i2, String str);

    void onInitProgress(long j2, long j3);

    void onInitSuccess(ResInfo[] resInfoArr, ResInfo[] resInfoArr2, ResInfo[] resInfoArr3);
}
