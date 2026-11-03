package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import android.net.Uri;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f;
/* compiled from: DefaultDispatchStrategy.java */
/* loaded from: classes2.dex */
public class b extends f {
    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public void a(l lVar) {
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public void a(String str, boolean z, int i2) {
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public boolean a() {
        return false;
    }

    public b() {
        super(f.a.UNKNOWN_DISPATCH_STRATEGY);
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f
    public String a(Uri uri) {
        return uri.toString();
    }
}
