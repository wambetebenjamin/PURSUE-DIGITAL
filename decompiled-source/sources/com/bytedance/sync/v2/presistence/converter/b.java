package com.bytedance.sync.v2.presistence.converter;

import com.bytedance.sync.v2.protocal.h;
/* compiled from: ConsumeTypeConverter.java */
/* loaded from: classes3.dex */
public class b {
    public static h revertDate(int i2) {
        h fromValue = h.fromValue(i2);
        return fromValue == null ? h.Increment : fromValue;
    }

    public static int converterDate(h hVar) {
        return hVar.getValue();
    }
}
