package com.bytedance.sync.v2.presistence.converter;

import com.bytedance.sync.v2.protocal.n;
/* compiled from: StatusConverter.java */
/* loaded from: classes3.dex */
public class c {
    public static n revertStatus(int i2) {
        n fromValue = n.fromValue(i2);
        return fromValue == null ? n.InValid : fromValue;
    }

    public static int converterStatus(n nVar) {
        if (nVar == null) {
            return n.InValid.getValue();
        }
        return nVar.getValue();
    }
}
