package com.bytedance.bdinstall.callback;

import com.bytedance.bdinstall.y;
/* compiled from: HeaderUpdateCallback.java */
/* loaded from: classes.dex */
public class d extends c<com.bytedance.bdinstall.callback.event.a, y> implements a<com.bytedance.bdinstall.callback.event.a> {
    public d(y yVar) {
        super(yVar);
    }

    @Override // com.bytedance.bdinstall.callback.a
    public void a(com.bytedance.bdinstall.callback.event.a aVar) {
        y a2 = a();
        if (a2 != null) {
            a2.a(aVar.a());
        }
    }
}
