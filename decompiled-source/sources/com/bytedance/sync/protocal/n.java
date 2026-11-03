package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: InfoKey.java */
/* loaded from: classes3.dex */
public enum n implements WireEnum {
    Business(0),
    AppEvent(1),
    Params(2),
    ResponseCode(3),
    ResponseMsg(4);
    
    public static final ProtoAdapter<n> ADAPTER = new EnumAdapter<n>() { // from class: com.bytedance.sync.protocal.n.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public n fromValue(int i2) {
            return n.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13651a;

    n(int i2) {
        this.f13651a = i2;
    }

    public static n fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            return null;
                        }
                        return ResponseMsg;
                    }
                    return ResponseCode;
                }
                return Params;
            }
            return AppEvent;
        }
        return Business;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13651a;
    }
}
