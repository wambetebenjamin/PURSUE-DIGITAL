package com.bytedance.bdinstall.callback;

import com.bytedance.bdinstall.ag;
/* compiled from: InstallFinishCallback.java */
/* loaded from: classes.dex */
public class e extends c<com.bytedance.bdinstall.callback.event.b, ag> implements a<com.bytedance.bdinstall.callback.event.b> {
    public e(ag agVar) {
        super(agVar);
    }

    @Override // com.bytedance.bdinstall.callback.a
    public void a(com.bytedance.bdinstall.callback.event.b bVar) {
        ag a2 = a();
        if (a2 != null) {
            a2.a(bVar.a());
        }
    }
}
