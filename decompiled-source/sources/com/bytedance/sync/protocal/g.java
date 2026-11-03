package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Bucket.java */
/* loaded from: classes3.dex */
public enum g implements WireEnum {
    Device(0),
    User(1);
    
    public static final ProtoAdapter<g> ADAPTER = new EnumAdapter<g>() { // from class: com.bytedance.sync.protocal.g.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public g fromValue(int i2) {
            return g.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13641a;

    g(int i2) {
        this.f13641a = i2;
    }

    public static g fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return null;
            }
            return User;
        }
        return Device;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13641a;
    }
}
