package com.bytedance.sync.v2.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: InfoKey.java */
/* loaded from: classes3.dex */
public enum k implements WireEnum {
    Params(0),
    CustomParams(1),
    ErrCode(100),
    ErrMsg(101);
    
    public static final ProtoAdapter<k> ADAPTER = new EnumAdapter<k>() { // from class: com.bytedance.sync.v2.protocal.k.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public k fromValue(int i2) {
            return k.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13947a;

    k(int i2) {
        this.f13947a = i2;
    }

    public static k fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 100) {
                    if (i2 != 101) {
                        return null;
                    }
                    return ErrMsg;
                }
                return ErrCode;
            }
            return CustomParams;
        }
        return Params;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13947a;
    }
}
