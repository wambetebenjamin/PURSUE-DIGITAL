package com.bytedance.sync.logger;

import android.util.Log;
import com.bytedance.sync.interfaze.g;
/* compiled from: DefaultLogger.java */
/* loaded from: classes3.dex */
final class b implements g {
    b() {
    }

    @Override // com.bytedance.sync.interfaze.g
    public void v(String str) {
        Log.v(c.TAG, str);
    }

    @Override // com.bytedance.sync.interfaze.g
    public void d(String str) {
        Log.d(c.TAG, str);
    }

    @Override // com.bytedance.sync.interfaze.g
    public void i(String str) {
        Log.i(c.TAG, str);
    }

    @Override // com.bytedance.sync.interfaze.g
    public void e(String str) {
        Log.e(c.TAG, str);
    }
}
