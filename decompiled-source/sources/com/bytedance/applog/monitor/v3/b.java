package com.bytedance.applog.monitor.v3;
/* compiled from: Features.java */
/* loaded from: classes.dex */
public class b {

    /* compiled from: Features.java */
    /* loaded from: classes.dex */
    public enum a {
        NORMAL_SAMPLING_SWITCH(0),
        TEA_SAMPLING_SWITCH(1),
        PRIORITY_SWITCH(2),
        RECOVERY_SWITCH(3),
        LOG_SETTINGS_EVENT_FILTER(4),
        CUSTOM_EVENT_FILTER(5),
        ENCRYPT_SWITCH(6),
        FORWARD_REPORT_SWITCH(7),
        DATA_ISOLATE_SWITCH(8),
        EVENT_USER_ID_SWITCH(9),
        TOURIST_MODE_SWITCH(10),
        TOURIST_MODE_EVENT_SWITCH(11),
        ADJUST_TERMINATE_SWITCH(12),
        DISABLE_PERSONALIZATION_SWITCH(13),
        CHILD_MODE_BEFORE_INIT_SWITCH(14),
        CHILD_MODE_SWITCH(15),
        DEVTOOLS_SWITCH(16),
        DEBUG_LOG_SWITCH(17),
        BG_SESSION_SWITCH(18),
        CONTROL_EVENT_PARAM_SWITCH(21),
        CONTROL_CUSTOM_HEADER_SWITCH(22);
        
        private final int v;

        a(int i2) {
            this.v = i2;
        }

        public int a(int i2, boolean z) {
            int i3 = 1 << this.v;
            return z ? i2 | i3 : i2 & (~i3);
        }
    }

    /* compiled from: Features.java */
    /* renamed from: com.bytedance.applog.monitor.v3.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0038b {
        REPORT_INTERVAL(19, 2);
        

        /* renamed from: b  reason: collision with root package name */
        private final int f2908b;

        /* renamed from: c  reason: collision with root package name */
        private final int f2909c;

        EnumC0038b(int i2, int i3) {
            this.f2908b = i2;
            this.f2909c = i3;
        }

        public int a(int i2, int i3) {
            if (i3 >= (1 << this.f2909c)) {
                i3 = a();
            }
            return (i2 & (~a())) | (i3 << this.f2908b);
        }

        private int a() {
            int i2 = 1;
            for (int i3 = 0; i3 < this.f2909c; i3++) {
                i2 &= 1 << i3;
            }
            return i2;
        }
    }

    /* compiled from: Features.java */
    /* loaded from: classes.dex */
    public enum c {
        DEFAULT_60(0),
        LESS_THAN_60(1),
        BIG_THAN_60(2);
        

        /* renamed from: d  reason: collision with root package name */
        private int f2914d;

        c(int i2) {
            this.f2914d = i2;
        }

        public int a() {
            return this.f2914d;
        }
    }
}
