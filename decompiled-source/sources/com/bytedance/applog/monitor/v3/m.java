package com.bytedance.applog.monitor.v3;
/* compiled from: StageEventType.java */
/* loaded from: classes.dex */
public enum m {
    V3_EVENT(0),
    V1_EVENT(1),
    LOG_DATA(2),
    LAUNCH(3),
    TERMINATE(4),
    ITEM_IMPRESSION(5);
    

    /* renamed from: g  reason: collision with root package name */
    private final int f3029g;

    m(int i2) {
        this.f3029g = i2;
    }

    public int a() {
        return this.f3029g;
    }

    public static m a(String str) {
        m[] values;
        for (m mVar : values()) {
            if (mVar.name().equals(str)) {
                return mVar;
            }
        }
        return V3_EVENT;
    }

    public static m a(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 == 4) {
                        return TERMINATE;
                    }
                    return V3_EVENT;
                }
                return LAUNCH;
            }
            return LOG_DATA;
        }
        return V1_EVENT;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name() + "-" + this.f3029g;
    }
}
