package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Version.java */
/* loaded from: classes3.dex */
public enum s implements WireEnum {
    Unknown(0),
    V1(1);
    
    public static final ProtoAdapter<s> ADAPTER = new EnumAdapter<s>() { // from class: com.bytedance.sync.protocal.s.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public s fromValue(int i2) {
            return s.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13661a;

    s(int i2) {
        this.f13661a = i2;
    }

    public static s fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return null;
            }
            return V1;
        }
        return Unknown;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13661a;
    }
}
