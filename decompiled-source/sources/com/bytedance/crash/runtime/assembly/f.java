package com.bytedance.crash.runtime.assembly;

import android.content.Context;
import com.bytedance.crash.CrashType;
/* compiled from: NativeCrashAssembly.java */
@Deprecated
/* loaded from: classes2.dex */
class f extends b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public f(Context context, a aVar, BatteryWatcher batteryWatcher) {
        super(CrashType.NATIVE, context, aVar, batteryWatcher);
    }

    @Override // com.bytedance.crash.runtime.assembly.b
    public com.bytedance.crash.entity.b assemblyCrashBodyInner(int i2, com.bytedance.crash.entity.b bVar) {
        return super.assemblyCrashBodyInner(i2, bVar);
    }
}
