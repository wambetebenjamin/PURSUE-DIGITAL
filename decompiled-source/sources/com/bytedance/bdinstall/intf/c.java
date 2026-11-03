package com.bytedance.bdinstall.intf;

import com.bytedance.bdinstall.util.p;
/* compiled from: IAdIdConfig.java */
/* loaded from: classes.dex */
public interface c {
    long a();

    boolean b();

    /* compiled from: IAdIdConfig.java */
    /* loaded from: classes.dex */
    public static class a implements c {
        @Override // com.bytedance.bdinstall.intf.c
        public long a() {
            return 100L;
        }

        @Override // com.bytedance.bdinstall.intf.c
        public boolean b() {
            return !p.w();
        }
    }
}
