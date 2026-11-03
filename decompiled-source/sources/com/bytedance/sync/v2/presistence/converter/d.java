package com.bytedance.sync.v2.presistence.converter;

import com.bytedance.sync.v2.protocal.o;
/* compiled from: TopicStatusConverter.java */
/* loaded from: classes3.dex */
public class d {
    public static o revertStatus(int i2) {
        o fromValue = o.fromValue(i2);
        return fromValue == null ? o.NotExist : fromValue;
    }

    public static int converterStatus(o oVar) {
        if (oVar == null) {
            return o.NotExist.getValue();
        }
        return oVar.getValue();
    }
}
