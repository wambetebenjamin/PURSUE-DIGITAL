package com.bytedance.apm.block;

import java.util.ArrayList;
import java.util.List;
/* compiled from: EvilMethodSwitchManager.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static List<e> f708a = new ArrayList(2);

    public static synchronized void addListener(e eVar) {
        synchronized (f.class) {
            f708a.add(eVar);
        }
    }

    public static synchronized void removeListener(e eVar) {
        synchronized (f.class) {
            f708a.remove(eVar);
        }
    }

    public static synchronized void notify(boolean z, long j2) {
        synchronized (f.class) {
            for (e eVar : f708a) {
                if (eVar != null) {
                    eVar.onRefresh(z, j2);
                }
            }
        }
    }
}
