package com.bytedance.sync.persistence.converter;

import com.bytedance.sync.protocal.r;
/* compiled from: StatusConverter.java */
/* loaded from: classes3.dex */
public class d {
    public static r revertStatus(int i2) {
        r fromValue = r.fromValue(i2);
        return fromValue == null ? r.InValid : fromValue;
    }

    public static int converterStatus(r rVar) {
        if (rVar == null) {
            return r.InValid.getValue();
        }
        return rVar.getValue();
    }
}
