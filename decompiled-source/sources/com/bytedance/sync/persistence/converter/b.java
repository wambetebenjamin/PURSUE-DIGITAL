package com.bytedance.sync.persistence.converter;

import com.bytedance.sync.protocal.i;
/* compiled from: ConsumeTypeConverter.java */
/* loaded from: classes3.dex */
public class b {
    public static i revertDate(int i2) {
        i fromValue = i.fromValue(i2);
        return fromValue == null ? i.Increment : fromValue;
    }

    public static int converterDate(i iVar) {
        return iVar.getValue();
    }
}
