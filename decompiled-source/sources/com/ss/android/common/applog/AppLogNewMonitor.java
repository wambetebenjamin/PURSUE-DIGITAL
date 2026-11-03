package com.ss.android.common.applog;

import com.bytedance.applog.log.a;
import com.bytedance.applog.monitor.v3.h;
/* loaded from: classes9.dex */
public class AppLogNewMonitor {
    private static final a<h> monitor = new a<h>() { // from class: com.ss.android.common.applog.AppLogNewMonitor.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bytedance.applog.log.a
        public h create(Object... objArr) {
            return new h();
        }
    };

    public static h getNewMonitor() {
        return monitor.get(new Object[0]);
    }
}
