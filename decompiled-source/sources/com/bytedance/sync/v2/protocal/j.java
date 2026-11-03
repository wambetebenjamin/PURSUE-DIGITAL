package com.bytedance.sync.v2.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Flag.java */
/* loaded from: classes3.dex */
public enum j implements WireEnum {
    None(0),
    ACK(1),
    Data(2),
    Sync(3),
    Poll(4),
    FIN(5);
    
    public static final ProtoAdapter<j> ADAPTER = new EnumAdapter<j>() { // from class: com.bytedance.sync.v2.protocal.j.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public j fromValue(int i2) {
            return j.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13945a;

    j(int i2) {
        this.f13945a = i2;
    }

    public static j fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                return null;
                            }
                            return FIN;
                        }
                        return Poll;
                    }
                    return Sync;
                }
                return Data;
            }
            return ACK;
        }
        return None;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13945a;
    }
}
