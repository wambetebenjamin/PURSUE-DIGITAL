package com.bytedance.crash.runtime.assembly;

import android.content.Context;
import com.bytedance.crash.CrashType;
import com.bytedance.crash.ICommonParams;
/* compiled from: BaseAssembly.java */
@Deprecated
/* loaded from: classes2.dex */
public abstract class b {
    public static final String INTERFACE_ERR_INFO = "Code err:\n";

    /* renamed from: a  reason: collision with root package name */
    protected CrashType f7036a;

    /* renamed from: b  reason: collision with root package name */
    protected Context f7037b;

    /* renamed from: c  reason: collision with root package name */
    protected ICommonParams f7038c;

    /* renamed from: d  reason: collision with root package name */
    protected com.bytedance.crash.runtime.assembly.a f7039d;

    /* renamed from: e  reason: collision with root package name */
    protected BatteryWatcher f7040e;

    /* compiled from: BaseAssembly.java */
    /* loaded from: classes2.dex */
    public interface a {
        com.bytedance.crash.entity.b afterAssembly(int i2, com.bytedance.crash.entity.b bVar, boolean z);

        com.bytedance.crash.entity.b beforeAssembly(int i2, com.bytedance.crash.entity.b bVar);

        void onException(Throwable th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.bytedance.crash.entity.b assemblyCrashBody(com.bytedance.crash.entity.b bVar) {
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.bytedance.crash.entity.b assemblyCrashBodyInner(int i2, com.bytedance.crash.entity.b bVar) {
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(CrashType crashType, Context context, com.bytedance.crash.runtime.assembly.a aVar, BatteryWatcher batteryWatcher) {
    }

    public com.bytedance.crash.entity.b assemblyCrashBody(com.bytedance.crash.entity.b bVar, a aVar, boolean z) {
        return bVar == null ? new com.bytedance.crash.entity.b() : bVar;
    }
}
