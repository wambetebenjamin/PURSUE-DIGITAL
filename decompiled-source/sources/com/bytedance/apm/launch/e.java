package com.bytedance.apm.launch;

import com.bytedance.apm.launch.h;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: LaunchDataReport.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f1153a = false;

    public static h.a createPerfData() {
        if (f1153a) {
            return null;
        }
        f1153a = true;
        if (b.getInstance().getConfig().isNeedCollectPerfData() && com.bytedance.apm.internal.a.getSwitch(4)) {
            return h.getLaunchTraceData();
        }
        return null;
    }

    public static JSONObject packagePerfData(h.a aVar) {
        if (aVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (aVar.getGcCount() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_GC_COUNT, aVar.getGcCount());
            }
            if (aVar.getGcTime() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_GC_TIME, aVar.getGcTime());
            }
            if (aVar.getBlockGcCount() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_BLOCK_GC_COUNT, aVar.getBlockGcCount());
            }
            if (aVar.getBlockGcTime() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_BLOCK_GC_TIME, aVar.getBlockGcTime());
            }
            if (aVar.getIowaitTime() != -1.0d) {
                jSONObject2.put(c.LAUNCH_DATA_IOWAIT_TIME, aVar.getIowaitTime());
            }
            if (aVar.getRunnableTime() != -1.0d) {
                jSONObject2.put(c.LAUNCH_DATA_RUNNABLE_TIME, aVar.getRunnableTime());
            }
            if (aVar.getSleepTime() != -1.0d) {
                jSONObject2.put(c.LAUNCH_DATA_SLEEP_TIME, aVar.getSleepTime());
            }
            if (aVar.getMinorFault() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_MINOR_FAULT, aVar.getMinorFault());
            }
            if (aVar.getMajorFault() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_MAJOR_FAULT, aVar.getMajorFault());
            }
            if (aVar.getCpuTime() != -1.0d) {
                jSONObject2.put(c.LAUNCH_DATA_CPU_TIME, aVar.getCpuTime());
            }
            if (aVar.getVoluntarySwitches() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_VOLUNTARY_SWITCHES, aVar.getVoluntarySwitches());
            }
            if (aVar.getInVoluntarySwitches() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_INVOLUNTARY_SWITCHES, aVar.getInVoluntarySwitches());
            }
            if (aVar.getAllThreadCount() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_ALL_THREAD_COUNT, aVar.getAllThreadCount());
            }
            if (aVar.getLockTime() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_LOCK_TIME, aVar.getLockTime());
            }
            if (aVar.getBinderTime() != -1) {
                jSONObject2.put(c.LAUNCH_DATA_BINDER_TIME, aVar.getBinderTime());
            }
            jSONObject.put(c.LAUNCH_DATA_LIST_DATA, jSONObject2);
            List<String> javaThreadNameList = aVar.getJavaThreadNameList();
            if (javaThreadNameList != null && !javaThreadNameList.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (String str : javaThreadNameList) {
                    jSONArray.put(str);
                }
                jSONObject.put(c.LAUNCH_DATA_JAVA_THREAD_NAME_LIST, jSONArray);
            }
            b.getInstance().logD("perf data: " + jSONObject);
            return jSONObject;
        } catch (Throwable th) {
            th.printStackTrace();
            b.getInstance().logE(th.getLocalizedMessage());
            return null;
        }
    }
}
