package com.bytedance.ttgame.core.scheduler;

import android.os.AsyncTask;
import com.bytedance.ttgame.core.monitor.MonitorManager;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService;
import org.json.JSONObject;
import timber.log.Timber;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class SchedulerMonitor {
    private static final String LOG_TYPE_SCHEDULER_ERROR = "gsdk_scheduler_error";
    static final int TYPE_LONG_MAIN = 0;
    static final int TYPE_TASK_REJECT = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void commit(final int i2, final String str, final int i3) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() { // from class: com.bytedance.ttgame.core.scheduler.SchedulerMonitor.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", i2);
                    jSONObject.put("module", "scheduler");
                    jSONObject.put("message", str);
                    jSONObject.put("pool", i3);
                    ((ISdkMonitorLogService) ModuleManager.INSTANCE.getService(ISdkMonitorLogService.class)).monitorCommonLog(SchedulerMonitor.LOG_TYPE_SCHEDULER_ERROR, jSONObject);
                    MonitorManager.INSTANCE.monitorCommonLog(SchedulerMonitor.LOG_TYPE_SCHEDULER_ERROR, jSONObject);
                } catch (Throwable th) {
                    Timber.tag("GScheduler").e("commit error, ", th.getMessage());
                }
            }
        });
    }
}
