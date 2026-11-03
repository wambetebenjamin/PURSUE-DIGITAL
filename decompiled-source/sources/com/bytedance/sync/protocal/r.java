package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Status.java */
/* loaded from: classes3.dex */
public enum r implements WireEnum {
    InValid(0),
    Valid(1);
    
    public static final ProtoAdapter<r> ADAPTER = new EnumAdapter<r>() { // from class: com.bytedance.sync.protocal.r.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public r fromValue(int i2) {
            return r.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13659a;

    r(int i2) {
        this.f13659a = i2;
    }

    public static r fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return null;
            }
            return Valid;
        }
        return InValid;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13659a;
    }
}
