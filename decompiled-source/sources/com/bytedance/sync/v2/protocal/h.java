package com.bytedance.sync.v2.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: ConsumeType.java */
/* loaded from: classes3.dex */
public enum h implements WireEnum {
    OneByOne(0),
    Increment(1);
    
    public static final ProtoAdapter<h> ADAPTER = new EnumAdapter<h>() { // from class: com.bytedance.sync.v2.protocal.h.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public h fromValue(int i2) {
            return h.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13941a;

    h(int i2) {
        this.f13941a = i2;
    }

    public static h fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return null;
            }
            return Increment;
        }
        return OneByOne;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13941a;
    }
}
