package com.bytedance.apm6.cpu;

import android.os.Process;
import com.bytedance.monitor.collector.o;
/* compiled from: CgroupFetcher.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1948a = Process.myPid();

    /* compiled from: CgroupFetcher.java */
    /* loaded from: classes.dex */
    public enum a {
        UNKNOWN(-1),
        TOP_APP(0),
        FOREGROUND(1),
        BACKGROUND(2);
        

        /* renamed from: b  reason: collision with root package name */
        private final int f1950b;

        a(int i2) {
            this.f1950b = i2;
        }

        public static a a(int i2) {
            if (i2 != -1) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 == 2) {
                            return BACKGROUND;
                        }
                        return UNKNOWN;
                    }
                    return FOREGROUND;
                }
                return TOP_APP;
            }
            return UNKNOWN;
        }
    }

    public static a getGroupLevel() {
        return a.a(o.getInstance().b(f1948a));
    }
}
