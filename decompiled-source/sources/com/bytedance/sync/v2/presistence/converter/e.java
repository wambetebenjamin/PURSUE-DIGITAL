package com.bytedance.sync.v2.presistence.converter;

import com.bytedance.sync.v2.protocal.p;
/* compiled from: TopicTypeConverter.java */
/* loaded from: classes3.dex */
public class e {
    public static p revertTopicType(int i2) {
        p fromValue = p.fromValue(i2);
        return fromValue == null ? p.SpecTopic : fromValue;
    }

    public static int converterStatus(p pVar) {
        if (pVar == null) {
            return p.SpecTopic.getValue();
        }
        return pVar.getValue();
    }
}
