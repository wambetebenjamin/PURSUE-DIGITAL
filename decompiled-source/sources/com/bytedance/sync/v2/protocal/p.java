package com.bytedance.sync.v2.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: TopicType.java */
/* loaded from: classes3.dex */
public enum p implements WireEnum {
    SpecTopic(0),
    GlobalTopic(1),
    CustomTopic(2);
    
    public static final ProtoAdapter<p> ADAPTER = new EnumAdapter<p>() { // from class: com.bytedance.sync.v2.protocal.p.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public p fromValue(int i2) {
            return p.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13956a;

    p(int i2) {
        this.f13956a = i2;
    }

    public static p fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return null;
                }
                return CustomTopic;
            }
            return GlobalTopic;
        }
        return SpecTopic;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13956a;
    }
}
