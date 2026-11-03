package com.bytedance.hades.tgrp.api;
/* loaded from: classes2.dex */
public interface TGRPPatchListener {
    void onPatchEnd(int i2);

    void onPatchProgress(int i2, int i3, int i4, long j2, long j3);

    void onPatchStart(int i2);
}
