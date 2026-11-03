package com.bytedance.apm;

import android.os.SystemClock;
import com.bytedance.apm.k;
import com.bytedance.crash.NpthApi;
import com.bytedance.monitor.collector.i;
import com.bytedance.monitor.collector.s;
import com.bytedance.monitor.collector.t;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: NpthLooperMonitor.java */
/* loaded from: classes.dex */
public class m {
    public static void init() {
        k.a(new k.a() { // from class: com.bytedance.apm.m.1
            @Override // com.bytedance.apm.k.a
            public List<com.bytedance.crash.entity.f> dumpMainLooperHistoryMsg() {
                try {
                    List<i.e> dumpHistoryMsgItem = com.bytedance.monitor.collector.o.getInstance().getLooperDispatchMonitor().dumpHistoryMsgItem();
                    if (dumpHistoryMsgItem != null && !dumpHistoryMsgItem.isEmpty()) {
                        ArrayList arrayList = new ArrayList(dumpHistoryMsgItem.size());
                        for (int i2 = 0; i2 < dumpHistoryMsgItem.size(); i2++) {
                            arrayList.add(t.convert(dumpHistoryMsgItem.get(i2)));
                        }
                        return arrayList;
                    }
                } catch (Throwable unused) {
                }
                return null;
            }

            @Override // com.bytedance.apm.k.a
            public com.bytedance.crash.entity.f getDispatchingMsg() {
                i.e dumpCurrentMsgItem = com.bytedance.monitor.collector.o.getInstance().getLooperDispatchMonitor().dumpCurrentMsgItem(SystemClock.uptimeMillis());
                NpthApi.addTags(Collections.singletonMap("dispatch_last_msg", dumpCurrentMsgItem.mLastScheduleMsg == null ? "empty msg" : s.parseMessageKey(dumpCurrentMsgItem.mLastScheduleMsg)));
                return t.convert(dumpCurrentMsgItem);
            }
        });
    }
}
