package com.bytedance.sync.v2.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Version.java */
/* loaded from: classes3.dex */
public enum q implements WireEnum {
    Unknown(0),
    V1(1),
    V2(2);
    
    public static final ProtoAdapter<q> ADAPTER = new EnumAdapter<q>() { // from class: com.bytedance.sync.v2.protocal.q.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public q fromValue(int i2) {
            return q.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13958a;

    q(int i2) {
        this.f13958a = i2;
    }

    public static q fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return null;
                }
                return V2;
            }
            return V1;
        }
        return Unknown;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13958a;
    }
}
