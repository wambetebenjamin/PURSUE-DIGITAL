package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Compression.java */
/* loaded from: classes3.dex */
public enum h implements WireEnum {
    Uncompress(0),
    GZip(1);
    
    public static final ProtoAdapter<h> ADAPTER = new EnumAdapter<h>() { // from class: com.bytedance.sync.protocal.h.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public h fromValue(int i2) {
            return h.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13643a;

    h(int i2) {
        this.f13643a = i2;
    }

    public static h fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return null;
            }
            return GZip;
        }
        return Uncompress;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13643a;
    }
}
