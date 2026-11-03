package com.bytedance.applog.collector;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: PageHolder.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004J\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0006\u0010\f\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/bytedance/applog/collector/PageHolder;", "", "()V", "currentPageId", "", "currentPageName", ViewProps.ENABLED, "", "sdkPageId", "getPageId", "getPageInfo", "Lcom/bytedance/applog/collector/PageHolder$PageInfo;", "getSdkPageId", "isEnabled", "onBackground", "", "setEnabled", com.bytedance.frameworks.baselib.network.e.f7575a, "setPageId", "id", "setPageName", "name", "setSdkPageId", "PageInfo", "agent_globalRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2539a;

    /* renamed from: b  reason: collision with root package name */
    private String f2540b = "__coldstart";

    /* renamed from: c  reason: collision with root package name */
    private String f2541c = "";

    /* renamed from: d  reason: collision with root package name */
    private String f2542d = "__coldstart";

    /* compiled from: PageHolder.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u0006\u0010\u0018\u001a\u00020\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/bytedance/applog/collector/PageHolder$PageInfo;", "", com.bytedance.crash.entity.b.PID, "", "pName", "sdkPageId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPName", "()Ljava/lang/String;", "getPid", "getSdkPageId", "appendToJson", "", "target", "Lorg/json/JSONObject;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toJsonString", "toString", "Companion", "agent_globalRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final C0036a f2543a = new C0036a(null);

        /* renamed from: b  reason: collision with root package name */
        private final String f2544b;

        /* renamed from: c  reason: collision with root package name */
        private final String f2545c;

        /* renamed from: d  reason: collision with root package name */
        private final String f2546d;

        public static /* synthetic */ a a(a aVar, String str, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = aVar.f2544b;
            }
            if ((i2 & 2) != 0) {
                str2 = aVar.f2545c;
            }
            if ((i2 & 4) != 0) {
                str3 = aVar.f2546d;
            }
            return aVar.a(str, str2, str3);
        }

        public final a a(String pid, String pName, String sdkPageId) {
            Intrinsics.checkNotNullParameter(pid, "pid");
            Intrinsics.checkNotNullParameter(pName, "pName");
            Intrinsics.checkNotNullParameter(sdkPageId, "sdkPageId");
            return new a(pid, pName, sdkPageId);
        }

        public final String e() {
            return this.f2544b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof a) {
                a aVar = (a) obj;
                return Intrinsics.areEqual(this.f2544b, aVar.f2544b) && Intrinsics.areEqual(this.f2545c, aVar.f2545c) && Intrinsics.areEqual(this.f2546d, aVar.f2546d);
            }
            return false;
        }

        public final String f() {
            return this.f2545c;
        }

        public final String g() {
            return this.f2546d;
        }

        public int hashCode() {
            return (((this.f2544b.hashCode() * 31) + this.f2545c.hashCode()) * 31) + this.f2546d.hashCode();
        }

        public String toString() {
            return "PageInfo(pid=" + this.f2544b + ", pName=" + this.f2545c + ", sdkPageId=" + this.f2546d + ')';
        }

        public a(String pid, String pName, String sdkPageId) {
            Intrinsics.checkNotNullParameter(pid, "pid");
            Intrinsics.checkNotNullParameter(pName, "pName");
            Intrinsics.checkNotNullParameter(sdkPageId, "sdkPageId");
            this.f2544b = pid;
            this.f2545c = pName;
            this.f2546d = sdkPageId;
        }

        public final String a() {
            return this.f2544b;
        }

        public final String b() {
            return this.f2545c;
        }

        public final String c() {
            return this.f2546d;
        }

        public final String d() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.bytedance.crash.entity.b.PID, this.f2544b);
            jSONObject.put("pName", this.f2545c);
            jSONObject.put("sdkPageId", this.f2546d);
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "JSONObject().apply {\n   …\n            }.toString()");
            return jSONObject2;
        }

        public final void a(JSONObject target) {
            Intrinsics.checkNotNullParameter(target, "target");
            target.putOpt(e.f2547a, this.f2544b);
            target.putOpt(e.f2548b, this.f2545c);
            target.putOpt(e.f2549c, this.f2546d);
        }

        /* compiled from: PageHolder.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/bytedance/applog/collector/PageHolder$PageInfo$Companion;", "", "()V", "parse", "Lcom/bytedance/applog/collector/PageHolder$PageInfo;", "json", "", "agent_globalRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.bytedance.applog.collector.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0036a {
            public /* synthetic */ C0036a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private C0036a() {
            }

            public final a a(String json) {
                Intrinsics.checkNotNullParameter(json, "json");
                try {
                    JSONObject jSONObject = new JSONObject(json);
                    String optString = jSONObject.optString(com.bytedance.crash.entity.b.PID);
                    Intrinsics.checkNotNullExpressionValue(optString, "optString(\"pid\")");
                    String optString2 = jSONObject.optString("pName");
                    Intrinsics.checkNotNullExpressionValue(optString2, "optString(\"pName\")");
                    String optString3 = jSONObject.optString("sdkPageId");
                    Intrinsics.checkNotNullExpressionValue(optString3, "optString(\"sdkPageId\")");
                    return new a(optString, optString2, optString3);
                } catch (Throwable th) {
                    th.printStackTrace();
                    return null;
                }
            }
        }
    }

    public final void a(boolean z) {
        this.f2539a = z;
    }

    public final boolean a() {
        return this.f2539a;
    }

    public final void a(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.f2540b = id;
    }

    public final void b(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.f2542d = id;
    }

    public final void c(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.f2541c = name;
    }

    public final void b() {
        this.f2540b = "__background";
        this.f2542d = "__background";
        this.f2541c = "";
    }

    public final String c() {
        return this.f2540b;
    }

    public final String d() {
        return this.f2542d;
    }

    public final a e() {
        if (this.f2539a) {
            return new a(this.f2540b, this.f2541c, this.f2542d);
        }
        return null;
    }
}
