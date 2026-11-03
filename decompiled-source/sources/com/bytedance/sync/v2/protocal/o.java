package com.bytedance.sync.v2.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: TopicStatus.java */
/* loaded from: classes3.dex */
public enum o implements WireEnum {
    NotExist(0),
    Active(1),
    Closed(2);
    
    public static final ProtoAdapter<o> ADAPTER = new EnumAdapter<o>() { // from class: com.bytedance.sync.v2.protocal.o.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public o fromValue(int i2) {
            return o.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13954a;

    o(int i2) {
        this.f13954a = i2;
    }

    public static o fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return null;
                }
                return Closed;
            }
            return Active;
        }
        return NotExist;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13954a;
    }
}
