package com.bytedance.sdk.account.save.entity;

import java.util.List;
/* compiled from: InfoType.java */
/* loaded from: classes2.dex */
public enum c {
    INFO_TYPE_1(1),
    INFO_TYPE_2(2),
    INFO_TYPE_3(4);
    

    /* renamed from: a  reason: collision with root package name */
    private int f13004a;

    c(int i2) {
        this.f13004a = i2;
    }

    public int getValue() {
        return this.f13004a;
    }

    public static int getValue(List<c> list) {
        if (list == null) {
            return 0;
        }
        c cVar = INFO_TYPE_1;
        int i2 = list.contains(cVar) ? 0 + cVar.f13004a : 0;
        c cVar2 = INFO_TYPE_2;
        if (list.contains(cVar2)) {
            i2 += cVar2.f13004a;
        }
        c cVar3 = INFO_TYPE_3;
        return list.contains(cVar3) ? i2 + cVar3.f13004a : i2;
    }
}
