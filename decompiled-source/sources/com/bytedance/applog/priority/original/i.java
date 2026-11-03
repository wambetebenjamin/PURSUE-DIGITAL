package com.bytedance.applog.priority.original;

import com.bytedance.applog.priority.PriorityCallback;
import com.facebook.internal.AnalyticsEvents;
import com.google.common.net.HttpHeaders;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: Model.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\u0018\u00002\u00020\u0001:\u000b\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\rB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u000e"}, d2 = {"Lcom/bytedance/applog/priority/original/Model;", "", "()V", "EventKey", "EventType", "FastMatchGroups", "GroupConfig", "GroupFrequency", "GroupFrequencyAllow", "PriorityConfig", "PriorityContext", "PriorityName", "Ser", "Version", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class i {

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$GroupFrequencyAllow;", "", "(Ljava/lang/String;I)V", HttpHeaders.ALLOW, "BlockByFrequency", "BlockByBackoff", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public enum f {
        Allow,
        BlockByFrequency,
        BlockByBackoff
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$PriorityName;", "", "(Ljava/lang/String;I)V", "P0", "P1", "P2", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.bytedance.applog.priority.original.i$i  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0043i {
        P0,
        P1,
        P2
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0001\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$EventType;", "", "type", "", com.ss.android.pushmanager.e.KEY_ALIAS, "", "(Ljava/lang/String;IILjava/lang/String;)V", "getAlias", "()Ljava/lang/String;", "getType", "()I", "V3", "V1", "LogData", "Launch", "Terminate", "Impression", "ForwardV3", AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN, "Companion", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public enum b {
        V3(0, "event_v3"),
        V1(1, "event_v1"),
        LogData(2, "log_data"),
        Launch(3, "launch"),
        Terminate(4, "terminate"),
        Impression(5, "item_impression"),
        ForwardV3(6, "forward_v3"),
        Unknown(99, "unknown");
        

        /* renamed from: a  reason: collision with root package name */
        public static final a f3182a = new a(null);

        /* renamed from: j  reason: collision with root package name */
        private final int f3191j;
        private final String k;

        b(int i2, String str) {
            this.f3191j = i2;
            this.k = str;
        }

        public final int a() {
            return this.f3191j;
        }

        public final String b() {
            return this.k;
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$EventType$Companion;", "", "()V", "getEventType", "Lcom/bytedance/applog/priority/original/Model$EventType;", "type", "", com.ss.android.pushmanager.e.KEY_ALIAS, "", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class a {
            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private a() {
            }

            public final b a(int i2) {
                b bVar;
                b[] values = b.values();
                int length = values.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        bVar = null;
                        break;
                    }
                    bVar = values[i3];
                    if (bVar.a() == i2) {
                        break;
                    }
                    i3++;
                }
                return bVar == null ? b.Unknown : bVar;
            }

            public final b a(String alias) {
                b bVar;
                Intrinsics.checkNotNullParameter(alias, "alias");
                b[] values = b.values();
                int length = values.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        bVar = null;
                        break;
                    }
                    bVar = values[i2];
                    String b2 = bVar.b();
                    Locale ROOT = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                    String lowerCase = alias.toLowerCase(ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    if (Intrinsics.areEqual(b2, lowerCase)) {
                        break;
                    }
                    i2++;
                }
                return bVar == null ? b.Unknown : bVar;
            }
        }
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\tHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$PriorityContext;", "", "databasePool", "Lcom/bytedance/applog/priority/original/DatabasePool;", com.bytedance.bdturing.methods.l.VALUE_CALLBACK, "Lcom/bytedance/applog/priority/PriorityCallback;", "logger", "Lcom/bytedance/applog/priority/Logger;", "defaultReportHost", "", "defaultReportPath", "(Lcom/bytedance/applog/priority/original/DatabasePool;Lcom/bytedance/applog/priority/PriorityCallback;Lcom/bytedance/applog/priority/Logger;Ljava/lang/String;Ljava/lang/String;)V", "getCallback", "()Lcom/bytedance/applog/priority/PriorityCallback;", "getDatabasePool", "()Lcom/bytedance/applog/priority/original/DatabasePool;", "getDefaultReportHost", "()Ljava/lang/String;", "getDefaultReportPath", "getLogger", "()Lcom/bytedance/applog/priority/Logger;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class h {

        /* renamed from: a  reason: collision with root package name */
        private final com.bytedance.applog.priority.original.d f3227a;

        /* renamed from: b  reason: collision with root package name */
        private final PriorityCallback f3228b;

        /* renamed from: c  reason: collision with root package name */
        private final com.bytedance.applog.priority.f f3229c;

        /* renamed from: d  reason: collision with root package name */
        private final String f3230d;

        /* renamed from: e  reason: collision with root package name */
        private final String f3231e;

        public static /* synthetic */ h a(h hVar, com.bytedance.applog.priority.original.d dVar, PriorityCallback priorityCallback, com.bytedance.applog.priority.f fVar, String str, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                dVar = hVar.f3227a;
            }
            if ((i2 & 2) != 0) {
                priorityCallback = hVar.f3228b;
            }
            PriorityCallback priorityCallback2 = priorityCallback;
            if ((i2 & 4) != 0) {
                fVar = hVar.f3229c;
            }
            com.bytedance.applog.priority.f fVar2 = fVar;
            if ((i2 & 8) != 0) {
                str = hVar.f3230d;
            }
            String str3 = str;
            if ((i2 & 16) != 0) {
                str2 = hVar.f3231e;
            }
            return hVar.a(dVar, priorityCallback2, fVar2, str3, str2);
        }

        public final h a(com.bytedance.applog.priority.original.d databasePool, PriorityCallback callback, com.bytedance.applog.priority.f logger, String defaultReportHost, String defaultReportPath) {
            Intrinsics.checkNotNullParameter(databasePool, "databasePool");
            Intrinsics.checkNotNullParameter(callback, "callback");
            Intrinsics.checkNotNullParameter(logger, "logger");
            Intrinsics.checkNotNullParameter(defaultReportHost, "defaultReportHost");
            Intrinsics.checkNotNullParameter(defaultReportPath, "defaultReportPath");
            return new h(databasePool, callback, logger, defaultReportHost, defaultReportPath);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof h) {
                h hVar = (h) obj;
                return Intrinsics.areEqual(this.f3227a, hVar.f3227a) && Intrinsics.areEqual(this.f3228b, hVar.f3228b) && Intrinsics.areEqual(this.f3229c, hVar.f3229c) && Intrinsics.areEqual(this.f3230d, hVar.f3230d) && Intrinsics.areEqual(this.f3231e, hVar.f3231e);
            }
            return false;
        }

        public final com.bytedance.applog.priority.original.d f() {
            return this.f3227a;
        }

        public final PriorityCallback g() {
            return this.f3228b;
        }

        public final com.bytedance.applog.priority.f h() {
            return this.f3229c;
        }

        public int hashCode() {
            return (((((((this.f3227a.hashCode() * 31) + this.f3228b.hashCode()) * 31) + this.f3229c.hashCode()) * 31) + this.f3230d.hashCode()) * 31) + this.f3231e.hashCode();
        }

        public final String i() {
            return this.f3230d;
        }

        public final String j() {
            return this.f3231e;
        }

        public String toString() {
            return "PriorityContext(databasePool=" + this.f3227a + ", callback=" + this.f3228b + ", logger=" + this.f3229c + ", defaultReportHost=" + this.f3230d + ", defaultReportPath=" + this.f3231e + ')';
        }

        public h(com.bytedance.applog.priority.original.d databasePool, PriorityCallback callback, com.bytedance.applog.priority.f logger, String defaultReportHost, String defaultReportPath) {
            Intrinsics.checkNotNullParameter(databasePool, "databasePool");
            Intrinsics.checkNotNullParameter(callback, "callback");
            Intrinsics.checkNotNullParameter(logger, "logger");
            Intrinsics.checkNotNullParameter(defaultReportHost, "defaultReportHost");
            Intrinsics.checkNotNullParameter(defaultReportPath, "defaultReportPath");
            this.f3227a = databasePool;
            this.f3228b = callback;
            this.f3229c = logger;
            this.f3230d = defaultReportHost;
            this.f3231e = defaultReportPath;
        }

        public final com.bytedance.applog.priority.original.d a() {
            return this.f3227a;
        }

        public final PriorityCallback b() {
            return this.f3228b;
        }

        public final com.bytedance.applog.priority.f c() {
            return this.f3229c;
        }

        public final String d() {
            return this.f3230d;
        }

        public final String e() {
            return this.f3231e;
        }
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\b\u0010\u001d\u001a\u00020\fH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010¨\u0006\u001e"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$EventKey;", "", "dbId", "", "groupId", "", "ts", "eventType", "Lcom/bytedance/applog/priority/original/Model$EventType;", "eventStained", "", "monitorId", "", "eventName", "(JIJLcom/bytedance/applog/priority/original/Model$EventType;ZLjava/lang/String;Ljava/lang/String;)V", "getDbId", "()J", "setDbId", "(J)V", "getEventName", "()Ljava/lang/String;", "getEventStained", "()Z", "getEventType", "()Lcom/bytedance/applog/priority/original/Model$EventType;", "getGroupId", "()I", "getMonitorId", "getTs", "toString", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private long f3175a;

        /* renamed from: b  reason: collision with root package name */
        private final int f3176b;

        /* renamed from: c  reason: collision with root package name */
        private final long f3177c;

        /* renamed from: d  reason: collision with root package name */
        private final b f3178d;

        /* renamed from: e  reason: collision with root package name */
        private final boolean f3179e;

        /* renamed from: f  reason: collision with root package name */
        private final String f3180f;

        /* renamed from: g  reason: collision with root package name */
        private final String f3181g;

        public a(long j2, int i2, long j3, b eventType, boolean z, String monitorId, String eventName) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            Intrinsics.checkNotNullParameter(monitorId, "monitorId");
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            this.f3175a = j2;
            this.f3176b = i2;
            this.f3177c = j3;
            this.f3178d = eventType;
            this.f3179e = z;
            this.f3180f = monitorId;
            this.f3181g = eventName;
        }

        public final long a() {
            return this.f3175a;
        }

        public final void a(long j2) {
            this.f3175a = j2;
        }

        public final int b() {
            return this.f3176b;
        }

        public final long c() {
            return this.f3177c;
        }

        public final b d() {
            return this.f3178d;
        }

        public final boolean e() {
            return this.f3179e;
        }

        public final String f() {
            return this.f3180f;
        }

        public final String g() {
            return this.f3181g;
        }

        public String toString() {
            return "id=" + this.f3175a + "|g=" + this.f3176b + "|n=" + this.f3181g + '|' + this.f3180f;
        }
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0000J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0001R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Version;", "", "version", "", "configIds", "Lorg/json/JSONArray;", "(ILorg/json/JSONArray;)V", "getVersion", "()I", "isNewFrom", "", "other", "toJson", "Lorg/json/JSONObject;", "toJsonWithValue", "value", "Companion", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class k {

        /* renamed from: a  reason: collision with root package name */
        public static final a f3264a = new a(null);

        /* renamed from: b  reason: collision with root package name */
        private final int f3265b;

        /* renamed from: c  reason: collision with root package name */
        private final JSONArray f3266c;

        public k(int i2, JSONArray jSONArray) {
            this.f3265b = i2;
            this.f3266c = jSONArray;
        }

        public final int a() {
            return this.f3265b;
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Version$Companion;", "", "()V", "fromJson", "Lcom/bytedance/applog/priority/original/Model$Version;", "json", "Lorg/json/JSONObject;", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class a {
            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private a() {
            }

            public final k a(JSONObject json) {
                Intrinsics.checkNotNullParameter(json, "json");
                return new k(json.optInt("version", 0), json.optJSONArray(com.bytedance.applog.sampling.d.f3361d));
            }
        }

        public final JSONObject a(Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("version", this.f3265b);
            jSONObject.put("value", value);
            JSONArray jSONArray = this.f3266c;
            if (jSONArray != null) {
                jSONObject.put(com.bytedance.applog.sampling.d.f3361d, jSONArray);
            }
            return jSONObject;
        }

        public final boolean a(k other) {
            Intrinsics.checkNotNullParameter(other, "other");
            int i2 = this.f3265b;
            int i3 = other.f3265b;
            if (i2 > i3) {
                return true;
            }
            if (i2 < i3) {
                return false;
            }
            JSONArray jSONArray = this.f3266c;
            String jSONArray2 = jSONArray != null ? jSONArray.toString() : null;
            JSONArray jSONArray3 = other.f3266c;
            return !Intrinsics.areEqual(jSONArray2, jSONArray3 != null ? jSONArray3.toString() : null);
        }

        public final JSONObject b() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("version", this.f3265b);
            JSONArray jSONArray = this.f3266c;
            if (jSONArray != null) {
                jSONObject.put(com.bytedance.applog.sampling.d.f3361d, jSONArray);
            }
            return jSONObject;
        }
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$PriorityConfig;", "", "engine", "Lcom/bytedance/applog/priority/original/Engine;", "version", "Lcom/bytedance/applog/priority/original/Model$Version;", "priorityName", "Lcom/bytedance/applog/priority/original/Model$PriorityName;", "maxEventSize", "", "maxRequestBodySize", "maxRequestEventCnt", "", "eventTtl", com.bytedance.bdturing.methods.l.VALUE_CALLBACK, "Lcom/bytedance/applog/priority/PriorityCallback;", "(Lcom/bytedance/applog/priority/original/Engine;Lcom/bytedance/applog/priority/original/Model$Version;Lcom/bytedance/applog/priority/original/Model$PriorityName;JJIJLcom/bytedance/applog/priority/PriorityCallback;)V", "getCallback", "()Lcom/bytedance/applog/priority/PriorityCallback;", "getEngine", "()Lcom/bytedance/applog/priority/original/Engine;", "getEventTtl", "()J", "getMaxEventSize", "getMaxRequestBodySize", "getMaxRequestEventCnt", "()I", "getPriorityName", "()Lcom/bytedance/applog/priority/original/Model$PriorityName;", "getVersion", "()Lcom/bytedance/applog/priority/original/Model$Version;", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class g {

        /* renamed from: a  reason: collision with root package name */
        private final com.bytedance.applog.priority.original.e f3219a;

        /* renamed from: b  reason: collision with root package name */
        private final k f3220b;

        /* renamed from: c  reason: collision with root package name */
        private final EnumC0043i f3221c;

        /* renamed from: d  reason: collision with root package name */
        private final long f3222d;

        /* renamed from: e  reason: collision with root package name */
        private final long f3223e;

        /* renamed from: f  reason: collision with root package name */
        private final int f3224f;

        /* renamed from: g  reason: collision with root package name */
        private final long f3225g;

        /* renamed from: h  reason: collision with root package name */
        private final PriorityCallback f3226h;

        public g(com.bytedance.applog.priority.original.e engine, k version, EnumC0043i priorityName, long j2, long j3, int i2, long j4, PriorityCallback callback) {
            Intrinsics.checkNotNullParameter(engine, "engine");
            Intrinsics.checkNotNullParameter(version, "version");
            Intrinsics.checkNotNullParameter(priorityName, "priorityName");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.f3219a = engine;
            this.f3220b = version;
            this.f3221c = priorityName;
            this.f3222d = j2;
            this.f3223e = j3;
            this.f3224f = i2;
            this.f3225g = j4;
            this.f3226h = callback;
        }

        public final com.bytedance.applog.priority.original.e a() {
            return this.f3219a;
        }

        public final k b() {
            return this.f3220b;
        }

        public final EnumC0043i c() {
            return this.f3221c;
        }

        public final long d() {
            return this.f3222d;
        }

        public final long e() {
            return this.f3223e;
        }

        public final int f() {
            return this.f3224f;
        }

        public final long g() {
            return this.f3225g;
        }

        public final PriorityCallback h() {
            return this.f3226h;
        }
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u0003H\u0002J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\rH\u0002J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"J\u0006\u0010$\u001a\u00020\"J\b\u0010%\u001a\u00020\"H\u0002R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006&"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$GroupFrequency;", "", "isRealtime", "", "version", "Lcom/bytedance/applog/priority/original/Model$Version;", "backoffRatio", "", "maxRequestPerMinute", "level", "Ljava/util/concurrent/atomic/AtomicInteger;", "levelSuccessCnt", "lastWindowTime", "", "lastCheckTime", "(ZLcom/bytedance/applog/priority/original/Model$Version;IILjava/util/concurrent/atomic/AtomicInteger;Ljava/util/concurrent/atomic/AtomicInteger;JJ)V", "MAX_BACKOFF_RATIO", "MAX_RECOVERY_COUNT", "getLevel", "()Ljava/util/concurrent/atomic/AtomicInteger;", "setLevel", "(Ljava/util/concurrent/atomic/AtomicInteger;)V", "getMaxRequestPerMinute", "()I", "setMaxRequestPerMinute", "(I)V", "getVersion", "()Lcom/bytedance/applog/priority/original/Model$Version;", "allow", "Lcom/bytedance/applog/priority/original/Model$GroupFrequencyAllow;", "backoffAllow", "freqAllow", "checkTime", "handle2xx", "", "handle4xx", "handle5xx", "resetWindow", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        private final boolean f3205a;

        /* renamed from: b  reason: collision with root package name */
        private final k f3206b;

        /* renamed from: c  reason: collision with root package name */
        private final int f3207c;

        /* renamed from: d  reason: collision with root package name */
        private int f3208d;

        /* renamed from: e  reason: collision with root package name */
        private AtomicInteger f3209e;

        /* renamed from: f  reason: collision with root package name */
        private AtomicInteger f3210f;

        /* renamed from: g  reason: collision with root package name */
        private long f3211g;

        /* renamed from: h  reason: collision with root package name */
        private long f3212h;

        /* renamed from: i  reason: collision with root package name */
        private final int f3213i;

        /* renamed from: j  reason: collision with root package name */
        private final int f3214j;

        public final void f() {
        }

        public e(boolean z, k version, int i2, int i3, AtomicInteger level, AtomicInteger levelSuccessCnt, long j2, long j3) {
            Intrinsics.checkNotNullParameter(version, "version");
            Intrinsics.checkNotNullParameter(level, "level");
            Intrinsics.checkNotNullParameter(levelSuccessCnt, "levelSuccessCnt");
            this.f3205a = z;
            this.f3206b = version;
            this.f3207c = i2;
            this.f3208d = i3;
            this.f3209e = level;
            this.f3210f = levelSuccessCnt;
            this.f3211g = j2;
            this.f3212h = j3;
            this.f3213i = 10000;
            this.f3214j = 5;
        }

        public final k a() {
            return this.f3206b;
        }

        public final void a(int i2) {
            this.f3208d = i2;
        }

        public final int b() {
            return this.f3208d;
        }

        public final void a(AtomicInteger atomicInteger) {
            Intrinsics.checkNotNullParameter(atomicInteger, "<set-?>");
            this.f3209e = atomicInteger;
        }

        public final AtomicInteger c() {
            return this.f3209e;
        }

        public final f d() {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            this.f3212h = currentTimeMillis;
            if (this.f3205a) {
                if (this.f3209e.get() > 0) {
                    long j2 = this.f3212h;
                    if (j2 - this.f3211g > 5) {
                        this.f3211g = j2;
                        return f.Allow;
                    }
                    return f.BlockByFrequency;
                }
                return f.Allow;
            } else if (!a(currentTimeMillis)) {
                return f.BlockByFrequency;
            } else {
                if (!h()) {
                    return f.BlockByBackoff;
                }
                return f.Allow;
            }
        }

        public final void e() {
            this.f3210f.set(this.f3208d);
            this.f3211g = this.f3212h;
            if (this.f3209e.incrementAndGet() > 4) {
                this.f3209e.set(4);
            }
        }

        public final void g() {
            int incrementAndGet = this.f3210f.incrementAndGet();
            if (this.f3209e.get() > 0) {
                if (incrementAndGet >= this.f3214j || this.f3205a) {
                    this.f3209e.set(0);
                }
            }
        }

        private final boolean a(long j2) {
            int i2 = this.f3209e.get();
            if (i2 == 0) {
                if (j2 - this.f3211g >= 60) {
                    i();
                    return true;
                }
            } else {
                if (j2 - this.f3211g >= ((long) 60) * ((long) Math.pow(2.0d, (double) i2))) {
                    i();
                    return true;
                }
            }
            return this.f3210f.get() < this.f3208d;
        }

        private final boolean h() {
            int i2 = this.f3207c;
            if (i2 == 0 || i2 == this.f3213i || new SecureRandom().nextInt(this.f3213i) > this.f3207c) {
                return true;
            }
            this.f3210f.incrementAndGet();
            return false;
        }

        private final void i() {
            this.f3210f.set(0);
            this.f3211g = this.f3212h;
        }
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0011J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u000fH\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006$"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$GroupConfig;", "", "id", "", "version", "Lcom/bytedance/applog/priority/original/Model$Version;", com.bytedance.applog.forward.b.f2686c, "frequency", "Lcom/bytedance/applog/priority/original/Model$GroupFrequency;", "priority", "Lcom/bytedance/applog/priority/original/Model$PriorityConfig;", "filter", "Lcom/bytedance/applog/priority/original/Model$Ser$SerGroupFilter;", "supportEventTypes", "", "", "reportUrl", "(ILcom/bytedance/applog/priority/original/Model$Version;ILcom/bytedance/applog/priority/original/Model$GroupFrequency;Lcom/bytedance/applog/priority/original/Model$PriorityConfig;Lcom/bytedance/applog/priority/original/Model$Ser$SerGroupFilter;Ljava/util/List;Ljava/lang/String;)V", "getFilter", "()Lcom/bytedance/applog/priority/original/Model$Ser$SerGroupFilter;", "getFrequency", "()Lcom/bytedance/applog/priority/original/Model$GroupFrequency;", "getId", "()I", "getInterval", "getPriority", "()Lcom/bytedance/applog/priority/original/Model$PriorityConfig;", "getReportUrl", "()Ljava/lang/String;", "getSupportEventTypes", "()Ljava/util/List;", "getVersion", "()Lcom/bytedance/applog/priority/original/Model$Version;", "setMaxRequestFrequency", "", "toString", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        private final int f3197a;

        /* renamed from: b  reason: collision with root package name */
        private final k f3198b;

        /* renamed from: c  reason: collision with root package name */
        private final int f3199c;

        /* renamed from: d  reason: collision with root package name */
        private final e f3200d;

        /* renamed from: e  reason: collision with root package name */
        private final g f3201e;

        /* renamed from: f  reason: collision with root package name */
        private final j.d f3202f;

        /* renamed from: g  reason: collision with root package name */
        private final List<String> f3203g;

        /* renamed from: h  reason: collision with root package name */
        private final String f3204h;

        public d(int i2, k version, int i3, e frequency, g priority, j.d dVar, List<String> supportEventTypes, String reportUrl) {
            Intrinsics.checkNotNullParameter(version, "version");
            Intrinsics.checkNotNullParameter(frequency, "frequency");
            Intrinsics.checkNotNullParameter(priority, "priority");
            Intrinsics.checkNotNullParameter(supportEventTypes, "supportEventTypes");
            Intrinsics.checkNotNullParameter(reportUrl, "reportUrl");
            this.f3197a = i2;
            this.f3198b = version;
            this.f3199c = i3;
            this.f3200d = frequency;
            this.f3201e = priority;
            this.f3202f = dVar;
            this.f3203g = supportEventTypes;
            this.f3204h = reportUrl;
            i();
        }

        public final int a() {
            return this.f3197a;
        }

        public final k b() {
            return this.f3198b;
        }

        public final int c() {
            return this.f3199c;
        }

        public final e d() {
            return this.f3200d;
        }

        public final g e() {
            return this.f3201e;
        }

        public final j.d f() {
            return this.f3202f;
        }

        public final List<String> g() {
            return this.f3203g;
        }

        public final String h() {
            return this.f3204h;
        }

        public String toString() {
            return "GroupConfig-" + this.f3197a;
        }

        private final void i() {
            if (this.f3199c <= 0) {
                this.f3200d.a(Integer.MAX_VALUE);
            } else if (this.f3200d.b() > 0) {
            } else {
                this.f3200d.a(RangesKt.coerceAtLeast(60 / this.f3199c, 10));
            }
        }
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J&\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$FastMatchGroups;", "", "groups", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/bytedance/applog/priority/original/Group;", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "eventTypes", "", "Lcom/bytedance/applog/priority/original/Model$EventType;", "getGroups", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "v3All", "", "v3Names", "", "match", "eventName", "eventType", "json", "Lkotlin/Lazy;", "Lorg/json/JSONObject;", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        private final CopyOnWriteArrayList<com.bytedance.applog.priority.original.f> f3192a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f3193b;

        /* renamed from: c  reason: collision with root package name */
        private final Set<String> f3194c;

        /* renamed from: d  reason: collision with root package name */
        private final Set<b> f3195d;

        /* compiled from: Model.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public /* synthetic */ class a {

            /* renamed from: a  reason: collision with root package name */
            public static final /* synthetic */ int[] f3196a;

            static {
                int[] iArr = new int[b.values().length];
                iArr[b.V3.ordinal()] = 1;
                f3196a = iArr;
            }
        }

        public c(CopyOnWriteArrayList<com.bytedance.applog.priority.original.f> groups) {
            Intrinsics.checkNotNullParameter(groups, "groups");
            this.f3192a = groups;
            this.f3194c = new LinkedHashSet();
            this.f3195d = new LinkedHashSet();
            Iterator<com.bytedance.applog.priority.original.f> it = groups.iterator();
            while (it.hasNext()) {
                com.bytedance.applog.priority.original.f next = it.next();
                for (String str : next.a().g()) {
                    if (Intrinsics.areEqual(str, b.V3.b())) {
                        this.f3195d.add(b.V3);
                        if (!this.f3193b) {
                            j.d f2 = next.a().f();
                            if (f2 != null) {
                                Set<String> a2 = f2.a();
                                if (a2.contains("*")) {
                                    this.f3193b = true;
                                    this.f3194c.clear();
                                } else {
                                    for (String str2 : a2) {
                                        this.f3194c.add(str2);
                                    }
                                }
                                HashMap<String, List<List<String>>> b2 = f2.b();
                                if (b2.containsKey("*")) {
                                    this.f3193b = true;
                                    this.f3194c.clear();
                                } else {
                                    Set<String> keySet = b2.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "this.keys");
                                    for (String e2 : keySet) {
                                        Set<String> set = this.f3194c;
                                        Intrinsics.checkNotNullExpressionValue(e2, "e");
                                        set.add(e2);
                                    }
                                }
                                HashMap<String, List<HashMap<String, String>>> c2 = f2.c();
                                if (c2.containsKey("*")) {
                                    this.f3193b = true;
                                    this.f3194c.clear();
                                } else {
                                    Set<String> keySet2 = c2.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet2, "this.keys");
                                    for (String e3 : keySet2) {
                                        Set<String> set2 = this.f3194c;
                                        Intrinsics.checkNotNullExpressionValue(e3, "e");
                                        set2.add(e3);
                                    }
                                }
                                if (c2 == null) {
                                }
                            }
                            this.f3193b = true;
                            Unit unit = Unit.INSTANCE;
                        }
                    } else {
                        this.f3195d.add(b.f3182a.a(str));
                    }
                }
            }
        }

        public final CopyOnWriteArrayList<com.bytedance.applog.priority.original.f> a() {
            return this.f3192a;
        }

        public final com.bytedance.applog.priority.original.f a(String eventName, b eventType, Lazy<? extends JSONObject> json) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            Intrinsics.checkNotNullParameter(json, "json");
            Object obj = null;
            if (a.f3196a[eventType.ordinal()] == 1) {
                if (this.f3193b || this.f3194c.contains(eventName)) {
                    JSONObject optJSONObject = json.getValue().optJSONObject("params");
                    Iterator<T> it = this.f3192a.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (((com.bytedance.applog.priority.original.f) next).b(eventType, eventName, optJSONObject)) {
                            obj = next;
                            break;
                        }
                    }
                    return (com.bytedance.applog.priority.original.f) obj;
                }
                return null;
            } else if (this.f3195d.contains(eventType)) {
                Iterator<T> it2 = this.f3192a.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next2 = it2.next();
                    if (((com.bytedance.applog.priority.original.f) next2).b(eventType, "", null)) {
                        obj = next2;
                        break;
                    }
                }
                return (com.bytedance.applog.priority.original.f) obj;
            } else {
                return null;
            }
        }
    }

    /* compiled from: Model.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\u0018\u00002\u00020\u0001:\b\u0003\u0004\u0005\u0006\u0007\b\t\nB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u000b"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser;", "", "()V", "SerBackoffPriority", "SerBackoffPriorityList", "SerGroup", "SerGroupFilter", "SerGroups", "SerPriorityConfig", "SerPriorityConfigConfig", "SerPriorityConfigConfigItem", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class j {

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser$SerBackoffPriority;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "backoffRatio", "", "getBackoffRatio", "()I", "maxRequestFrequency", "getMaxRequestFrequency", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private final int f3236a;

            /* renamed from: b  reason: collision with root package name */
            private final int f3237b;

            public a(JSONObject json) {
                Intrinsics.checkNotNullParameter(json, "json");
                this.f3236a = json.optInt("backoff_ratio", 0);
                this.f3237b = json.optInt("max_request_frequency", 10);
            }

            public final int a() {
                return this.f3236a;
            }

            public final int b() {
                return this.f3237b;
            }
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser$SerBackoffPriorityList;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "backoffRatio", "", "getBackoffRatio", "()I", "maxRequestFrequency", "getMaxRequestFrequency", "priorityMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/bytedance/applog/priority/original/Model$Ser$SerBackoffPriority;", "getPriorityMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "version", "Lcom/bytedance/applog/priority/original/Model$Version;", "getVersion", "()Lcom/bytedance/applog/priority/original/Model$Version;", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class b {

            /* renamed from: a  reason: collision with root package name */
            private final k f3238a;

            /* renamed from: b  reason: collision with root package name */
            private final int f3239b;

            /* renamed from: c  reason: collision with root package name */
            private final int f3240c;

            /* renamed from: d  reason: collision with root package name */
            private final ConcurrentHashMap<Integer, a> f3241d;

            public b(JSONObject json) {
                Intrinsics.checkNotNullParameter(json, "json");
                this.f3238a = new k(json.optInt("version", 0), json.optJSONArray(com.bytedance.applog.sampling.d.f3361d));
                this.f3239b = json.optInt("backoff_ratio", 0);
                this.f3240c = json.optInt("max_request_frequency", 10);
                this.f3241d = new ConcurrentHashMap<>();
                JSONArray optJSONArray = json.optJSONArray("priorities");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        JSONObject priority = optJSONArray.optJSONObject(i2);
                        if (priority != null) {
                            Intrinsics.checkNotNullExpressionValue(priority, "priority");
                            int optInt = priority.optInt("global_priority");
                            if (optInt > 0) {
                                this.f3241d.put(Integer.valueOf(optInt), new a(priority));
                            }
                        }
                    }
                }
            }

            public final k a() {
                return this.f3238a;
            }

            public final int b() {
                return this.f3239b;
            }

            public final int c() {
                return this.f3240c;
            }

            public final ConcurrentHashMap<Integer, a> d() {
                return this.f3241d;
            }
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\u0018\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser$SerPriorityConfigConfigItem;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "eventTtl", "", "getEventTtl", "()J", "getJson", "()Lorg/json/JSONObject;", "maxEventSize", "getMaxEventSize", "maxRequestBodySize", "getMaxRequestBodySize", "maxRequestEventCnt", "", "getMaxRequestEventCnt", "()I", "getDefaultInt", "key", "", "defaultValue", "getDefaultLong", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class h {

            /* renamed from: a  reason: collision with root package name */
            private final JSONObject f3259a;

            /* renamed from: b  reason: collision with root package name */
            private final long f3260b;

            /* renamed from: c  reason: collision with root package name */
            private final long f3261c;

            /* renamed from: d  reason: collision with root package name */
            private final int f3262d;

            /* renamed from: e  reason: collision with root package name */
            private final long f3263e;

            public h(JSONObject json) {
                Intrinsics.checkNotNullParameter(json, "json");
                this.f3259a = json;
                this.f3260b = a("max_event_size", 1048576L);
                this.f3261c = a("max_request_body_size", com.bytedance.applog.priority.original.j.f3267a);
                this.f3262d = a("max_request_event_count", 2000);
                this.f3263e = a("event_ttl", com.bytedance.applog.priority.original.j.f3269c);
            }

            public final JSONObject a() {
                return this.f3259a;
            }

            public final long b() {
                return this.f3260b;
            }

            public final long c() {
                return this.f3261c;
            }

            public final int d() {
                return this.f3262d;
            }

            public final long e() {
                return this.f3263e;
            }

            private final long a(String str, long j2) {
                long optLong = this.f3259a.optLong(str);
                return optLong > 0 ? optLong : j2;
            }

            private final int a(String str, int i2) {
                int optInt = this.f3259a.optInt(str);
                return optInt > 0 ? optInt : i2;
            }
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser$SerPriorityConfigConfig;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "p0", "Lcom/bytedance/applog/priority/original/Model$Ser$SerPriorityConfigConfigItem;", "getP0", "()Lcom/bytedance/applog/priority/original/Model$Ser$SerPriorityConfigConfigItem;", "p1", "getP1", "p2", "getP2", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class g {

            /* renamed from: a  reason: collision with root package name */
            private final h f3256a;

            /* renamed from: b  reason: collision with root package name */
            private final h f3257b;

            /* renamed from: c  reason: collision with root package name */
            private final h f3258c;

            public g(JSONObject json) {
                Intrinsics.checkNotNullParameter(json, "json");
                JSONObject optJSONObject = json.optJSONObject("P0");
                this.f3256a = new h(optJSONObject == null ? new JSONObject() : optJSONObject);
                JSONObject optJSONObject2 = json.optJSONObject("P1");
                this.f3257b = new h(optJSONObject2 == null ? new JSONObject() : optJSONObject2);
                JSONObject optJSONObject3 = json.optJSONObject("P2");
                this.f3258c = new h(optJSONObject3 == null ? new JSONObject() : optJSONObject3);
            }

            public final h a() {
                return this.f3256a;
            }

            public final h b() {
                return this.f3257b;
            }

            public final h c() {
                return this.f3258c;
            }
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser$SerPriorityConfig;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "config", "Lcom/bytedance/applog/priority/original/Model$Ser$SerPriorityConfigConfig;", "getConfig", "()Lcom/bytedance/applog/priority/original/Model$Ser$SerPriorityConfigConfig;", "version", "Lcom/bytedance/applog/priority/original/Model$Version;", "getVersion", "()Lcom/bytedance/applog/priority/original/Model$Version;", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class f {

            /* renamed from: a  reason: collision with root package name */
            private final k f3254a;

            /* renamed from: b  reason: collision with root package name */
            private final g f3255b;

            public f(JSONObject json) {
                Intrinsics.checkNotNullParameter(json, "json");
                this.f3254a = new k(json.optInt("version", 0), json.optJSONArray(com.bytedance.applog.sampling.d.f3361d));
                JSONObject optJSONObject = json.optJSONObject("config");
                this.f3255b = new g(optJSONObject == null ? new JSONObject() : optJSONObject);
            }

            public final k a() {
                return this.f3254a;
            }

            public final g b() {
                return this.f3255b;
            }
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tRq\u0010\n\u001ab\u0012\u0004\u0012\u00020\u0007\u0012&\u0012$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000bj\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\r0\f0\u000bj0\u0012\u0004\u0012\u00020\u0007\u0012&\u0012$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000bj\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\r0\f`\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fRE\u0010\u0010\u001a6\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f0\f0\u000bj\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f0\f`\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser$SerGroupFilter;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", com.bytedance.applog.forward.b.f2687d, "", "", "getEvents", "()Ljava/util/Set;", "paramKeyValues", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getParamKeyValues", "()Ljava/util/HashMap;", "paramKeys", "getParamKeys", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class d {

            /* renamed from: a  reason: collision with root package name */
            private final Set<String> f3249a;

            /* renamed from: b  reason: collision with root package name */
            private final HashMap<String, List<List<String>>> f3250b;

            /* renamed from: c  reason: collision with root package name */
            private final HashMap<String, List<HashMap<String, String>>> f3251c;

            public d(JSONObject json) {
                JSONObject jSONObject;
                Intrinsics.checkNotNullParameter(json, "json");
                this.f3249a = new LinkedHashSet();
                this.f3250b = new HashMap<>();
                this.f3251c = new HashMap<>();
                JSONArray optJSONArray = json.optJSONArray(com.bytedance.applog.forward.b.f2687d);
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        String e2 = optJSONArray.optString(i2);
                        String str = e2;
                        if (!(str == null || str.length() == 0)) {
                            Set<String> set = this.f3249a;
                            Intrinsics.checkNotNullExpressionValue(e2, "e");
                            set.add(e2);
                        }
                    }
                }
                JSONObject optJSONObject = json.optJSONObject("param_keys");
                if (optJSONObject != null) {
                    Iterator<String> keys = optJSONObject.keys();
                    Intrinsics.checkNotNullExpressionValue(keys, "it.keys()");
                    while (keys.hasNext()) {
                        String key = keys.next();
                        ArrayList arrayList = new ArrayList();
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray(key);
                        if (optJSONArray2 != null) {
                            Intrinsics.checkNotNullExpressionValue(optJSONArray2, "optJSONArray(key)");
                            int length2 = optJSONArray2.length();
                            int i3 = 0;
                            while (i3 < length2) {
                                JSONArray andArr = optJSONArray2.optJSONArray(i3);
                                ArrayList arrayList2 = new ArrayList();
                                if (andArr != null) {
                                    Intrinsics.checkNotNullExpressionValue(andArr, "andArr");
                                    int length3 = andArr.length();
                                    jSONObject = optJSONObject;
                                    int i4 = 0;
                                    while (i4 < length3) {
                                        int i5 = length3;
                                        String value = andArr.optString(i4);
                                        String str2 = value;
                                        if (!(str2 == null || str2.length() == 0)) {
                                            Intrinsics.checkNotNullExpressionValue(value, "value");
                                            arrayList2.add(value);
                                        }
                                        i4++;
                                        length3 = i5;
                                    }
                                } else {
                                    jSONObject = optJSONObject;
                                }
                                arrayList.add(arrayList2);
                                i3++;
                                optJSONObject = jSONObject;
                            }
                        }
                        Intrinsics.checkNotNullExpressionValue(key, "key");
                        this.f3250b.put(key, arrayList);
                        optJSONObject = optJSONObject;
                    }
                }
                JSONObject optJSONObject2 = json.optJSONObject("param_key_values");
                if (optJSONObject2 != null) {
                    Iterator<String> keys2 = optJSONObject2.keys();
                    Intrinsics.checkNotNullExpressionValue(keys2, "it.keys()");
                    while (keys2.hasNext()) {
                        String key2 = keys2.next();
                        ArrayList arrayList3 = new ArrayList();
                        JSONArray optJSONArray3 = optJSONObject2.optJSONArray(key2);
                        if (optJSONArray3 != null) {
                            Intrinsics.checkNotNullExpressionValue(optJSONArray3, "optJSONArray(key)");
                            int length4 = optJSONArray3.length();
                            int i6 = 0;
                            while (i6 < length4) {
                                JSONObject value2 = optJSONArray3.optJSONObject(i6);
                                HashMap hashMap = new HashMap();
                                if (value2 != null) {
                                    Intrinsics.checkNotNullExpressionValue(value2, "value");
                                    Iterator<String> keys3 = value2.keys();
                                    Intrinsics.checkNotNullExpressionValue(keys3, "v.keys()");
                                    while (keys3.hasNext()) {
                                        String k = keys3.next();
                                        JSONObject jSONObject2 = optJSONObject2;
                                        Intrinsics.checkNotNullExpressionValue(k, "k");
                                        String optString = value2.optString(k);
                                        Intrinsics.checkNotNullExpressionValue(optString, "v.optString(k)");
                                        hashMap.put(k, optString);
                                        optJSONObject2 = jSONObject2;
                                        keys2 = keys2;
                                    }
                                }
                                arrayList3.add(hashMap);
                                i6++;
                                optJSONObject2 = optJSONObject2;
                                keys2 = keys2;
                            }
                        }
                        Intrinsics.checkNotNullExpressionValue(key2, "key");
                        this.f3251c.put(key2, arrayList3);
                        optJSONObject2 = optJSONObject2;
                        keys2 = keys2;
                    }
                }
            }

            public final Set<String> a() {
                return this.f3249a;
            }

            public final HashMap<String, List<List<String>>> b() {
                return this.f3250b;
            }

            public final HashMap<String, List<HashMap<String, String>>> c() {
                return this.f3251c;
            }
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\nR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser$SerGroup;", "", "json", "Lorg/json/JSONObject;", "defaultReportHost", "", "defaultReportPath", "(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)V", "eventPriority", "getEventPriority", "()Ljava/lang/String;", "filter", "Lcom/bytedance/applog/priority/original/Model$Ser$SerGroupFilter;", "getFilter", "()Lcom/bytedance/applog/priority/original/Model$Ser$SerGroupFilter;", "id", "", "getId", "()I", com.bytedance.applog.forward.b.f2686c, "getInterval", "reportHost", "getReportHost", "reportPath", "getReportPath", "supportEventTypes", "", "getSupportEventTypes", "()Ljava/util/List;", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class c {

            /* renamed from: a  reason: collision with root package name */
            private final int f3242a;

            /* renamed from: b  reason: collision with root package name */
            private final int f3243b;

            /* renamed from: c  reason: collision with root package name */
            private final String f3244c;

            /* renamed from: d  reason: collision with root package name */
            private final String f3245d;

            /* renamed from: e  reason: collision with root package name */
            private final List<String> f3246e;

            /* renamed from: f  reason: collision with root package name */
            private final String f3247f;

            /* renamed from: g  reason: collision with root package name */
            private final d f3248g;

            public c(JSONObject json, String defaultReportHost, String defaultReportPath) {
                Intrinsics.checkNotNullParameter(json, "json");
                Intrinsics.checkNotNullParameter(defaultReportHost, "defaultReportHost");
                Intrinsics.checkNotNullParameter(defaultReportPath, "defaultReportPath");
                this.f3242a = json.optInt("global_priority", 0);
                this.f3243b = RangesKt.coerceAtLeast(json.optInt(com.bytedance.applog.forward.b.f2686c, 0), 0);
                String optString = json.optString("report_host", defaultReportHost);
                Intrinsics.checkNotNullExpressionValue(optString, "json.optString(\"report_host\", defaultReportHost)");
                this.f3244c = optString;
                String optString2 = json.optString("report_path", defaultReportPath);
                Intrinsics.checkNotNullExpressionValue(optString2, "json.optString(\"report_path\", defaultReportPath)");
                this.f3245d = optString2;
                this.f3246e = new ArrayList();
                String optString3 = json.optString("event_priority", "P2");
                Intrinsics.checkNotNullExpressionValue(optString3, "json.optString(\"event_priority\", \"P2\")");
                this.f3247f = optString3;
                JSONObject optJSONObject = json.optJSONObject("filter");
                this.f3248g = optJSONObject != null ? new d(optJSONObject) : null;
                JSONArray optJSONArray = json.optJSONArray("support_event_types");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        String type = optJSONArray.optString(i2);
                        String str = type;
                        if (!(str == null || str.length() == 0)) {
                            List<String> list = this.f3246e;
                            Intrinsics.checkNotNullExpressionValue(type, "type");
                            Locale ROOT = Locale.ROOT;
                            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                            String lowerCase = type.toLowerCase(ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                            list.add(lowerCase);
                        }
                    }
                }
            }

            public final int a() {
                return this.f3242a;
            }

            public final int b() {
                return this.f3243b;
            }

            public final String c() {
                return this.f3244c;
            }

            public final String d() {
                return this.f3245d;
            }

            public final List<String> e() {
                return this.f3246e;
            }

            public final String f() {
                return this.f3247f;
            }

            public final d g() {
                return this.f3248g;
            }
        }

        /* compiled from: Model.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/bytedance/applog/priority/original/Model$Ser$SerGroups;", "", "json", "Lorg/json/JSONObject;", "defaultReportHost", "", "defaultReportPath", "(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)V", "priorities", "", "Lcom/bytedance/applog/priority/original/Model$Ser$SerGroup;", "getPriorities", "()Ljava/util/List;", "version", "Lcom/bytedance/applog/priority/original/Model$Version;", "getVersion", "()Lcom/bytedance/applog/priority/original/Model$Version;", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class e {

            /* renamed from: a  reason: collision with root package name */
            private final k f3252a;

            /* renamed from: b  reason: collision with root package name */
            private final List<c> f3253b;

            public e(JSONObject json, String defaultReportHost, String defaultReportPath) {
                Intrinsics.checkNotNullParameter(json, "json");
                Intrinsics.checkNotNullParameter(defaultReportHost, "defaultReportHost");
                Intrinsics.checkNotNullParameter(defaultReportPath, "defaultReportPath");
                this.f3252a = new k(json.optInt("version", 0), json.optJSONArray(com.bytedance.applog.sampling.d.f3361d));
                this.f3253b = new ArrayList();
                JSONArray optJSONArray = json.optJSONArray("priorities");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        JSONObject priority = optJSONArray.optJSONObject(i2);
                        if (priority != null) {
                            Intrinsics.checkNotNullExpressionValue(priority, "priority");
                            this.f3253b.add(new c(priority, defaultReportHost, defaultReportPath));
                        }
                    }
                }
            }

            public final k a() {
                return this.f3252a;
            }

            public final List<c> b() {
                return this.f3253b;
            }
        }
    }
}
