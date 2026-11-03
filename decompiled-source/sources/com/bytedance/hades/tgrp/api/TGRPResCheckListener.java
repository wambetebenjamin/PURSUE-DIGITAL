package com.bytedance.hades.tgrp.api;
/* loaded from: classes2.dex */
public interface TGRPResCheckListener {
    void onResCheckEnd(int i2, ResInfo[] resInfoArr, ResInfo[] resInfoArr2);

    void onResCheckProgress(int i2, int i3, int i4, long j2, long j3);

    void onResCheckStart(int i2);
}
