package com.bytedance.ttgame.core.realtime;

import android.text.TextUtils;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.ICoreInternalService;
import com.bytedance.ttgame.main.internal.log.IGLogService;
import com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class RealtimeEventService implements IRealtimeEventService {
    private static final Set<String> ALLOWLIST = new HashSet(Arrays.asList(IRealtimeEventService.EVENT_INIT_SUCCESS, IRealtimeEventService.EVENT_LOGIN_CALL, IRealtimeEventService.EVENT_LOGIN));
    private static final String TAG = "RealtimeEventService";
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private final RealtimeEventFormatter formatter = new RealtimeEventFormatter();
    private final RealtimeEventStore store = new RealtimeEventStore();
    private final RealtimeEventUploader uploader = new RealtimeEventUploader();
    private final AtomicBoolean draining = new AtomicBoolean(false);
    private final AtomicBoolean drainRequested = new AtomicBoolean(false);

    private String safe(String str) {
        return str == null ? "" : str;
    }

    @Override // com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService
    public void report(final String str, JSONObject jSONObject) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService", "com.bytedance.ttgame.core.realtime.RealtimeEventService", "report", new String[]{"java.lang.String", "org.json.JSONObject"}, Constants.VOID);
        if (TextUtils.isEmpty(str) || !ALLOWLIST.contains(str)) {
            log("ignore event: " + str);
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long j2 = currentTimeMillis / 1000;
        final String jSONObject2 = jSONObject != null ? jSONObject.toString() : "{}";
        executeAsync(new Runnable() { // from class: com.bytedance.ttgame.core.realtime.RealtimeEventService.1
            @Override // java.lang.Runnable
            public void run() {
                String did = RealtimeEventService.this.getDid();
                if (TextUtils.isEmpty(did)) {
                    RealtimeEventService.this.savePending(str, jSONObject2, j2, currentTimeMillis);
                } else {
                    RealtimeEventService.this.uploadImmediate(str, jSONObject2, j2, did);
                }
            }
        });
    }

    @Override // com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService
    public void flushPendingAfterDidReady() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService", "com.bytedance.ttgame.core.realtime.RealtimeEventService", "flushPendingAfterDidReady", new String[0], Constants.VOID);
        scheduleDrain();
    }

    @Override // com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService
    public List<JSONObject> dumpPendingRealtimeEvents() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService", "com.bytedance.ttgame.core.realtime.RealtimeEventService", "dumpPendingRealtimeEvents", new String[0], "java.util.List");
        ArrayList arrayList = new ArrayList();
        try {
            for (PendingRealtimeEvent pendingRealtimeEvent : this.store.loadAll()) {
                if (pendingRealtimeEvent != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", safe(pendingRealtimeEvent.id));
                    jSONObject.put("event", safe(pendingRealtimeEvent.event));
                    jSONObject.put("eventTime", pendingRealtimeEvent.eventTime);
                    jSONObject.put("createdAt", pendingRealtimeEvent.createdAt);
                    jSONObject.put("expireAt", pendingRealtimeEvent.expireAt);
                    jSONObject.put("params", safe(pendingRealtimeEvent.params));
                    jSONObject.put("payload", safe(pendingRealtimeEvent.payload));
                    arrayList.add(jSONObject);
                }
            }
        } catch (Throwable th) {
            log("dump pending realtime events failed: " + th.getMessage());
        }
        return arrayList;
    }

    private void scheduleDrain() {
        this.drainRequested.set(true);
        executeAsync(new Runnable() { // from class: com.bytedance.ttgame.core.realtime.RealtimeEventService.2
            @Override // java.lang.Runnable
            public void run() {
                RealtimeEventService.this.drainPending();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drainPending() {
        String did;
        if (!this.draining.compareAndSet(false, true)) {
            log("drain cached events skipped because another drain is running");
            return;
        }
        try {
            this.drainRequested.set(false);
            did = getDid();
        } finally {
        }
        if (TextUtils.isEmpty(did)) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.store.clearExpired(currentTimeMillis);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            List<PendingRealtimeEvent> loadValid = this.store.loadValid(currentTimeMillis, 50);
            if (loadValid.isEmpty()) {
                break;
            }
            ArrayList arrayList = new ArrayList();
            for (PendingRealtimeEvent pendingRealtimeEvent : loadValid) {
                try {
                    arrayList.add(buildCachedPayload(pendingRealtimeEvent, did));
                } catch (Exception e2) {
                    log("drop invalid cached event, id: " + pendingRealtimeEvent.id + ", event: " + pendingRealtimeEvent.event + ", error: " + e2.getMessage());
                }
            }
            boolean uploadBatch = this.uploader.uploadBatch(arrayList);
            this.store.delete(loadValid);
            i2 += loadValid.size();
            i3++;
            log("drain cached events batch count: " + loadValid.size() + ", success: " + uploadBatch);
            this.draining.set(false);
        }
        log("drain cached events total count: " + i2 + ", batch count: " + i3);
        this.draining.set(false);
        if (this.drainRequested.get()) {
            scheduleDrain();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePending(String str, String str2, long j2, long j3) {
        try {
            PendingRealtimeEvent pendingRealtimeEvent = new PendingRealtimeEvent();
            pendingRealtimeEvent.id = UUID.randomUUID().toString();
            pendingRealtimeEvent.event = str;
            pendingRealtimeEvent.params = str2;
            pendingRealtimeEvent.payload = this.formatter.build(str, str2, j2, "").toString();
            pendingRealtimeEvent.eventTime = j2;
            pendingRealtimeEvent.createdAt = j3;
            pendingRealtimeEvent.expireAt = j3 + RealtimeEventConfig.CACHE_TTL_MS;
            String did = getDid();
            if (!TextUtils.isEmpty(did)) {
                uploadImmediate(str, str2, j2, did);
                return;
            }
            this.store.save(pendingRealtimeEvent);
            log("cache event because DID is empty: " + str);
        } catch (Exception e2) {
            log("cache event failed: " + str + ", error: " + e2.getMessage());
        }
    }

    private JSONObject buildCachedPayload(PendingRealtimeEvent pendingRealtimeEvent, String str) throws Exception {
        JSONObject restoreSnapshot;
        return (TextUtils.isEmpty(pendingRealtimeEvent.payload) || (restoreSnapshot = this.formatter.restoreSnapshot(pendingRealtimeEvent.payload, pendingRealtimeEvent.params, str)) == null) ? this.formatter.build(pendingRealtimeEvent.event, pendingRealtimeEvent.params, pendingRealtimeEvent.eventTime, str) : restoreSnapshot;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadImmediate(String str, String str2, long j2, String str3) {
        try {
            log("upload event: " + str + ", success: " + this.uploader.uploadOne(this.formatter.build(str, str2, j2, str3)));
        } catch (Exception unused) {
            log("upload event failed: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDid() {
        ICoreInternalService iCoreInternalService = (ICoreInternalService) ModuleManager.INSTANCE.getService(ICoreInternalService.class);
        return iCoreInternalService != null ? iCoreInternalService.getDeviceId() : "";
    }

    private void executeAsync(Runnable runnable) {
        ExecutorService executor;
        ICoreInternalService iCoreInternalService = (ICoreInternalService) ModuleManager.INSTANCE.getService(ICoreInternalService.class);
        if (iCoreInternalService == null || (executor = iCoreInternalService.getExecutor(1)) == null) {
            return;
        }
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            log("execute async realtime task failed: " + e2.getClass().getSimpleName() + ", error: " + e2.getMessage());
        }
    }

    private void log(String str) {
        IGLogService iGLogService = (IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class);
        if (iGLogService != null) {
            iGLogService.d(TAG, str);
        }
    }
}
