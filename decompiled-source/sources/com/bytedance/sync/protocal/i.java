package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: ConsumeType.java */
/* loaded from: classes3.dex */
public enum i implements WireEnum {
    OneByOne(0),
    Increment(1);
    
    public static final ProtoAdapter<i> ADAPTER = new EnumAdapter<i>() { // from class: com.bytedance.sync.protocal.i.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public i fromValue(int i2) {
            return i.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13645a;

    i(int i2) {
        this.f13645a = i2;
    }

    public static i fromValue(int i2) {
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
        return this.f13645a;
    }
}
