package com.bytedance.monitor.collector;

import com.bytedance.monitor.collector.i;
import org.json.JSONObject;
/* compiled from: ApmLooperMonitorUtil.java */
/* loaded from: classes2.dex */
public class t {
    public static com.bytedance.crash.entity.f convert(final i.e eVar) {
        com.bytedance.crash.entity.f fVar = new com.bytedance.crash.entity.f() { // from class: com.bytedance.monitor.collector.t.1
            @Override // com.bytedance.crash.entity.f
            public JSONObject toJson() {
                return i.e.this.toJson();
            }
        };
        fVar.mMsgs = eVar.f9570d;
        fVar.mType = eVar.f9569c;
        fVar.mDurationTick = 1L;
        fVar.mDuration = eVar.f9571e;
        fVar.mDurationCpuTime = eVar.f9572f;
        fVar.mScheduleMsg = eVar.f9574h;
        fVar.mBlockStack = s.stackToString(eVar.f9575i);
        fVar.mSeriousBlockStack = s.stackToString(eVar.f9576j);
        fVar.uuid = eVar.l;
        return fVar;
    }
}
