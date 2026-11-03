package com.bytedance.bdturing.net.models;

import com.bytedance.apm.constant.p;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: DefaultSettingsModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/bytedance/bdturing/net/models/DefaultSettingsModel;", "", p.SETTING_GENERAL_API_REPORT_HOSTS, "Lorg/json/JSONObject;", "verifyURL", "verifyHosts", "(Lorg/json/JSONObject;Lorg/json/JSONObject;Lorg/json/JSONObject;)V", "getHosts", "()Lorg/json/JSONObject;", "getVerifyHosts", "getVerifyURL", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final JSONObject f4286a;

    /* renamed from: b  reason: collision with root package name */
    private final JSONObject f4287b;

    /* renamed from: c  reason: collision with root package name */
    private final JSONObject f4288c;

    public a() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ a a(a aVar, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            jSONObject = aVar.f4286a;
        }
        if ((i2 & 2) != 0) {
            jSONObject2 = aVar.f4287b;
        }
        if ((i2 & 4) != 0) {
            jSONObject3 = aVar.f4288c;
        }
        return aVar.a(jSONObject, jSONObject2, jSONObject3);
    }

    public final a a(JSONObject hosts, JSONObject verifyURL, JSONObject verifyHosts) {
        Intrinsics.checkNotNullParameter(hosts, "hosts");
        Intrinsics.checkNotNullParameter(verifyURL, "verifyURL");
        Intrinsics.checkNotNullParameter(verifyHosts, "verifyHosts");
        return new a(hosts, verifyURL, verifyHosts);
    }

    public final JSONObject d() {
        return this.f4286a;
    }

    public final JSONObject e() {
        return this.f4287b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.f4286a, aVar.f4286a) && Intrinsics.areEqual(this.f4287b, aVar.f4287b) && Intrinsics.areEqual(this.f4288c, aVar.f4288c);
        }
        return false;
    }

    public final JSONObject f() {
        return this.f4288c;
    }

    public int hashCode() {
        return (((this.f4286a.hashCode() * 31) + this.f4287b.hashCode()) * 31) + this.f4288c.hashCode();
    }

    public String toString() {
        return "DefaultSettingsModel(hosts=" + this.f4286a + ", verifyURL=" + this.f4287b + ", verifyHosts=" + this.f4288c + ')';
    }

    public a(JSONObject hosts, JSONObject verifyURL, JSONObject verifyHosts) {
        Intrinsics.checkNotNullParameter(hosts, "hosts");
        Intrinsics.checkNotNullParameter(verifyURL, "verifyURL");
        Intrinsics.checkNotNullParameter(verifyHosts, "verifyHosts");
        this.f4286a = hosts;
        this.f4287b = verifyURL;
        this.f4288c = verifyHosts;
    }

    public /* synthetic */ a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new JSONObject() : jSONObject, (i2 & 2) != 0 ? new JSONObject() : jSONObject2, (i2 & 4) != 0 ? new JSONObject() : jSONObject3);
    }

    public final JSONObject a() {
        return this.f4286a;
    }

    public final JSONObject b() {
        return this.f4287b;
    }

    public final JSONObject c() {
        return this.f4288c;
    }
}
