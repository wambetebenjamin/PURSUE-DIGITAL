package com.bytedance.sync.persistence.converter;
/* compiled from: DataTypeConverter.java */
/* loaded from: classes3.dex */
public class c {
    public static com.bytedance.sync.model.a revertDate(int i2) {
        com.bytedance.sync.model.a fromValue = com.bytedance.sync.model.a.fromValue(i2);
        return fromValue == null ? com.bytedance.sync.model.a.ORIGIN : fromValue;
    }

    public static int converterDate(com.bytedance.sync.model.a aVar) {
        if (aVar == null) {
            return com.bytedance.sync.model.a.ORIGIN.getValue();
        }
        return aVar.getValue();
    }
}
