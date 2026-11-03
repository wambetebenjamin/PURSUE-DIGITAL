package com.bytedance.sync.protocal;

import com.squareup.wire.EnumAdapter;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireEnum;
/* compiled from: AppEventType.java */
/* loaded from: classes3.dex */
public enum a implements WireEnum {
    Startup(0),
    Login(1),
    Logout(2),
    Switch2Foreground(3),
    SyncHeartbeat(4);
    
    public static final ProtoAdapter<a> ADAPTER = new EnumAdapter<a>() { // from class: com.bytedance.sync.protocal.a.a
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.squareup.wire.EnumAdapter
        /* renamed from: a */
        public a fromValue(int i2) {
            return a.fromValue(i2);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f13633a;

    a(int i2) {
        this.f13633a = i2;
    }

    public static a fromValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            return null;
                        }
                        return SyncHeartbeat;
                    }
                    return Switch2Foreground;
                }
                return Logout;
            }
            return Login;
        }
        return Startup;
    }

    @Override // com.squareup.wire.WireEnum
    public int getValue() {
        return this.f13633a;
    }
}
