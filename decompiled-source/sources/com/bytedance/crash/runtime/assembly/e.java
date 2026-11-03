package com.bytedance.crash.runtime.assembly;

import android.content.Context;
import com.bytedance.crash.CrashType;
/* compiled from: JavaCrashAssembly.java */
@Deprecated
/* loaded from: classes2.dex */
class e extends b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public e(Context context, a aVar, BatteryWatcher batteryWatcher, boolean z) {
        super(z ? CrashType.LAUNCH : CrashType.JAVA, context, aVar, batteryWatcher);
    }

    @Override // com.bytedance.crash.runtime.assembly.b
    public com.bytedance.crash.entity.b assemblyCrashBodyInner(int i2, com.bytedance.crash.entity.b bVar) {
        return super.assemblyCrashBodyInner(i2, bVar);
    }
}
