package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: PackageType.java */
/* loaded from: classes3.dex */
public enum q implements WireEnum {
    Normal(0),
    Merged(1);
    
    public static final ProtoAdapter<q> ADAPTER = new EnumAdapter<q>() { // from class: com.bytedance.sync.protocal.q.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public q fromValue(int i2) {
            return q.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13657a;

    q(int i2) {
        this.f13657a = i2;
    }

    public static q fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return null;
            }
            return Merged;
        }
        return Normal;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13657a;
    }
}
