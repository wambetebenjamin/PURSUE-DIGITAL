package com.bytedance.sync.v2.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Status.java */
/* loaded from: classes3.dex */
public enum n implements WireEnum {
    InValid(0),
    Valid(1);
    
    public static final ProtoAdapter<n> ADAPTER = new EnumAdapter<n>() { // from class: com.bytedance.sync.v2.protocal.n.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public n fromValue(int i2) {
            return n.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13952a;

    n(int i2) {
        this.f13952a = i2;
    }

    public static n fromValue(int i2) {
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
        return this.f13952a;
    }
}
