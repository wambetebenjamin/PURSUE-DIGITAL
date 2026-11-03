package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Encryption.java */
/* loaded from: classes3.dex */
public enum j implements WireEnum {
    Plaintext(0),
    RSA(1);
    
    public static final ProtoAdapter<j> ADAPTER = new EnumAdapter<j>() { // from class: com.bytedance.sync.protocal.j.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public j fromValue(int i2) {
            return j.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13647a;

    j(int i2) {
        this.f13647a = i2;
    }

    public static j fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return null;
            }
            return RSA;
        }
        return Plaintext;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13647a;
    }
}
