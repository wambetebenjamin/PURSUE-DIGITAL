package com.bytedance.sync.v2.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: Ctrl.java */
/* loaded from: classes3.dex */
public enum i implements WireEnum {
    Default(0),
    HttpPoll(1),
    HeartbeatPong(2);
    
    public static final ProtoAdapter<i> ADAPTER = new EnumAdapter<i>() { // from class: com.bytedance.sync.v2.protocal.i.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public i fromValue(int i2) {
            return i.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13943a;

    i(int i2) {
        this.f13943a = i2;
    }

    public static i fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return null;
                }
                return HeartbeatPong;
            }
            return HttpPoll;
        }
        return Default;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13943a;
    }
}
