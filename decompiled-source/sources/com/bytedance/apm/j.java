package com.bytedance.apm;

import java.util.ArrayList;
import java.util.List;
/* compiled from: FluencySwitchManager.java */
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static List<i> f1140a = new ArrayList(2);

    public static synchronized void addListener(i iVar) {
        synchronized (j.class) {
            f1140a.add(iVar);
        }
    }

    public static synchronized void removeListener(i iVar) {
        synchronized (j.class) {
            f1140a.remove(iVar);
        }
    }

    public static synchronized void notify(com.bytedance.apm.config.g gVar) {
        synchronized (j.class) {
            for (i iVar : f1140a) {
                if (iVar != null) {
                    iVar.onRefresh(gVar);
                }
            }
        }
    }
}
