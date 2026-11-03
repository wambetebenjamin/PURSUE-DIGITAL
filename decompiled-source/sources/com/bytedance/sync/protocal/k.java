package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Flag.java */
/* loaded from: classes3.dex */
public enum k implements WireEnum {
    None(0),
    ACK(1),
    Data(2),
    Event(4),
    Notify(8),
    Pull(16),
    Calibration(32),
    Poll(64);
    
    public static final ProtoAdapter<k> ADAPTER = new EnumAdapter<k>() { // from class: com.bytedance.sync.protocal.k.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public k fromValue(int i2) {
            return k.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13649a;

    k(int i2) {
        this.f13649a = i2;
    }

    public static k fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 4) {
                        if (i2 != 8) {
                            if (i2 != 16) {
                                if (i2 != 32) {
                                    if (i2 != 64) {
                                        return null;
                                    }
                                    return Poll;
                                }
                                return Calibration;
                            }
                            return Pull;
                        }
                        return Notify;
                    }
                    return Event;
                }
                return Data;
            }
            return ACK;
        }
        return None;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13649a;
    }
}
