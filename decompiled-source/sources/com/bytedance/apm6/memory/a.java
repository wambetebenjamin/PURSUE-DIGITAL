package com.bytedance.apm6.memory;
/* compiled from: ApmMemoryManager.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f2302a;

    private a() {
    }

    public static a getInstance() {
        if (f2302a == null) {
            synchronized (a.class) {
                if (f2302a == null) {
                    f2302a = new a();
                }
            }
        }
        return f2302a;
    }

    public void addMemoryDataListener(com.bytedance.apm6.memory.listener.a aVar) {
        c.getInstance().addMemoryCallBackListener(aVar);
    }

    public void removeMemoryDataListener(com.bytedance.apm6.memory.listener.a aVar) {
        c.getInstance().removeMemoryCallBackListener(aVar);
    }
}
