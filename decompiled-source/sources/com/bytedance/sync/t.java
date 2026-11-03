package com.bytedance.sync;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.crash.Npth;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitor;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitorUtils;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: SyncMonitor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0007J\b\u0010\u0010\u001a\u00020\nH\u0007J4\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0014H\u0007J\u001c\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/bytedance/sync/SyncMonitor;", "", "()V", "AID_SLARDAR", "", "monitorImpl", "Lcom/bytedance/framwork/core/sdkmonitor/SDKMonitor;", "sEnableMonitor", "", "init", "", "context", "Landroid/content/Context;", "configuration", "Lcom/bytedance/sync/Configuration;", "deviceId", "injectNpthVersion", "monitor", "serviceName", "category", "Lorg/json/JSONObject;", "metric", "logExtra", "monitorException", com.bytedance.frameworks.baselib.network.e.f7575a, "", "errMsg", "monitorStorageSize", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class t {
    public static final t INSTANCE = new t();

    /* renamed from: a  reason: collision with root package name */
    private static final String f13693a = "4119";

    /* renamed from: b  reason: collision with root package name */
    private static volatile SDKMonitor f13694b;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f13695c;

    private t() {
    }

    @JvmStatic
    public static final void init(Context context, f configuration, String str) {
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        if (TextUtils.isEmpty(configuration.monitorHost)) {
            return;
        }
        f13695c = true;
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("device_id", str);
                jSONObject.put(com.bytedance.framwork.core.sdkmonitor.h.HOST_APP_ID, configuration.aid);
                jSONObject.put("sdk_version", com.bytedance.sync.a.VERSION_NAME);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            SDKMonitorUtils.setDefaultReportUrl(f13693a, CollectionsKt.listOf(configuration.monitorHost + com.bytedance.framwork.core.sdkmonitor.h.COLLECT_PATH));
            SDKMonitorUtils.setConfigUrl(f13693a, CollectionsKt.listOf(configuration.monitorHost + "/monitor/appmonitor/v2/settings"));
            try {
                if (configuration.debug) {
                    i iVar = configuration.commonParamProvider;
                    Intrinsics.checkExpressionValueIsNotNull(iVar, "configuration.commonParamProvider");
                    jSONObject.put("channel", iVar.getCommonParams().get("channel"));
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (context == null) {
                Intrinsics.throwNpe();
            }
            SDKMonitorUtils.initMonitor(context, f13693a, jSONObject, new a(configuration));
            f13694b = SDKMonitorUtils.getInstance(f13693a);
        } catch (Exception e4) {
            e4.printStackTrace();
            f13695c = false;
        }
    }

    /* compiled from: SyncMonitor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u0006"}, d2 = {"com/bytedance/sync/SyncMonitor$init$1", "Lcom/bytedance/framwork/core/sdkmonitor/SDKMonitor$IGetExtendParams;", "getCommonParams", "", "", "getSessionId", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
    /* loaded from: classes3.dex */
    public static final class a implements SDKMonitor.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ f f13696a;

        @Override // com.bytedance.framwork.core.sdkmonitor.SDKMonitor.c
        public String getSessionId() {
            return null;
        }

        a(f fVar) {
            this.f13696a = fVar;
        }

        @Override // com.bytedance.framwork.core.sdkmonitor.SDKMonitor.c
        public Map<String, String> getCommonParams() {
            i iVar = this.f13696a.commonParamProvider;
            Intrinsics.checkExpressionValueIsNotNull(iVar, "configuration.commonParamProvider");
            Map<String, String> commonParam = iVar.getCommonParams();
            Intrinsics.checkExpressionValueIsNotNull(commonParam, "commonParam");
            commonParam.put("oversea", this.f13696a.oversea ? "1" : "0");
            commonParam.remove("aid");
            commonParam.put(com.bytedance.framwork.core.sdkmonitor.h.HOST_APP_ID, this.f13696a.aid);
            return commonParam;
        }
    }

    public static /* synthetic */ void monitor$default(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            jSONObject = null;
        }
        if ((i2 & 4) != 0) {
            jSONObject2 = null;
        }
        if ((i2 & 8) != 0) {
            jSONObject3 = null;
        }
        monitor(str, jSONObject, jSONObject2, jSONObject3);
    }

    @JvmStatic
    public static final void monitor(String serviceName, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        Intrinsics.checkParameterIsNotNull(serviceName, "serviceName");
        if (f13695c) {
            com.bytedance.sync.logger.c.v("[Monitor] " + serviceName + ", " + jSONObject + ", " + jSONObject2 + ", " + jSONObject3);
            SDKMonitor sDKMonitor = f13694b;
            if (sDKMonitor == null) {
                Intrinsics.throwNpe();
            }
            sDKMonitor.monitorEvent(serviceName, jSONObject, jSONObject2, jSONObject3);
        }
    }

    public static /* synthetic */ void monitorException$default(Throwable th, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        monitorException(th, str);
    }

    @JvmStatic
    public static final void monitorException(Throwable e2, String str) {
        Intrinsics.checkParameterIsNotNull(e2, "e");
        JSONObject jSONObject = new JSONObject();
        com.bytedance.sync.util.a.safePutParam(jSONObject, "errMsg", str);
        JSONObject jSONObject2 = new JSONObject();
        com.bytedance.sync.util.a.safePutParam(jSONObject2, "stack", Log.getStackTraceString(e2));
        com.bytedance.sync.util.a.safePutParam(jSONObject2, "type", e2.getClass().getName());
        com.bytedance.sync.logger.c.d("[Monitor] -> syncsdk_exception," + jSONObject);
        try {
            com.bytedance.services.apm.api.b.ensureNotReachHere(e2, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @JvmStatic
    public static final void monitorStorageSize(Context context) {
        double a2;
        double a3;
        double a4;
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            long length = context.getDatabasePath(C0393r.DB_V2).length();
            com.ss.android.ug.bus.a service = com.ss.android.ug.bus.b.getService(com.bytedance.sync.interfaze.f.class);
            Intrinsics.checkExpressionValueIsNotNull(service, "UgBusFramework.getServic…CacheService::class.java)");
            long length2 = ((com.bytedance.sync.interfaze.f) service).getCachedDir().length();
            JSONObject jSONObject = new JSONObject();
            a2 = u.a(length);
            jSONObject.put("db_size", a2);
            a3 = u.a(length2);
            jSONObject.put("file_size", a3);
            a4 = u.a(length + length2);
            jSONObject.put(com.bytedance.apm.constant.i.PERF_DISK_FILE_SIZE, a4);
            monitor$default("sync_sdk_storage_size", null, jSONObject, null, 10, null);
        } catch (Exception e2) {
            monitorException$default(e2, null, 2, null);
        }
    }

    @JvmStatic
    public static final void injectNpthVersion() {
        try {
            Npth.registerSdk(f13693a, com.bytedance.sync.a.VERSION_NAME);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
