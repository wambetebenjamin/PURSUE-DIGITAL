package com.bytedance.ttgame.channelapi.monitor;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: GsdkMonitorLogger.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002JD\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/bytedance/ttgame/channelapi/monitor/GsdkMonitorLogger;", "Lcom/bytedance/ttgame/channelapi/monitor/IMonitorLogger;", "()V", "monitorMethod", "Ljava/lang/reflect/Method;", "rocketApiObj", "", "log", "", "event", "", "status", "", "metrics", "", "extras", "Companion", "channel_api_i18n_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GsdkMonitorLogger implements IMonitorLogger {
    public static final Companion Companion = new Companion(null);
    public static final String SERVICE_NAME = "channel_monitor";
    private final Method monitorMethod;
    private final Object rocketApiObj;

    public GsdkMonitorLogger() {
        Class<?> cls = Class.forName("com.bytedance.ttgame.rocketapi.Rocket");
        Object invoke = cls.getDeclaredMethod("getInstance", new Class[0]).invoke(cls, new Object[0]);
        Class<?> cls2 = Class.forName("com.bytedance.ttgame.rocketapi.IRocketApi");
        Object invoke2 = cls.getDeclaredMethod("getComponent", Class.class).invoke(invoke, cls2);
        Intrinsics.checkNotNullExpressionValue(invoke2, "rocketClass.getDeclaredM…ocketObj, rocketApiClass)");
        this.rocketApiObj = invoke2;
        Method declaredMethod = cls2.getDeclaredMethod("sdkMonitorEvent", String.class, JSONObject.class, JSONObject.class, JSONObject.class);
        Intrinsics.checkNotNullExpressionValue(declaredMethod, "rocketApiClass.getDeclar…  JSONObject::class.java)");
        this.monitorMethod = declaredMethod;
    }

    /* compiled from: GsdkMonitorLogger.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/bytedance/ttgame/channelapi/monitor/GsdkMonitorLogger$Companion;", "", "()V", "SERVICE_NAME", "", "channel_api_i18n_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.bytedance.ttgame.channelapi.monitor.IMonitorLogger
    public void log(String event, int i2, Map<String, ? extends Object> map, Map<String, ? extends Object> map2) {
        Intrinsics.checkNotNullParameter(event, "event");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(event, i2);
            if (map == null) {
                map = MapsKt.emptyMap();
            }
            JSONObject jSONObject2 = new JSONObject(map);
            if (map2 == null) {
                map2 = MapsKt.emptyMap();
            }
            this.monitorMethod.invoke(this.rocketApiObj, SERVICE_NAME, jSONObject, jSONObject2, new JSONObject(map2));
        } catch (Exception e2) {
            Log.w("gsdk_monitor_channel", String.valueOf(e2.getMessage()));
        }
    }
}
