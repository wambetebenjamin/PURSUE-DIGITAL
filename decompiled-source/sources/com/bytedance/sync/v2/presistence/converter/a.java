package com.bytedance.sync.v2.presistence.converter;

import com.bytedance.sync.v2.protocal.g;
/* compiled from: BucketConverter.java */
/* loaded from: classes3.dex */
public class a {
    public static g revertDate(int i2) {
        g fromValue = g.fromValue(i2);
        return fromValue == null ? g.Device : fromValue;
    }

    public static int converterDate(g gVar) {
        if (gVar == null) {
            return g.Device.getValue();
        }
        return gVar.getValue();
    }
}
