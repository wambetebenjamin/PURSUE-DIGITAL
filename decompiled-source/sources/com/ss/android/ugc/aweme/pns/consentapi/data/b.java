package com.ss.android.ugc.aweme.pns.consentapi.data;

import com.bytedance.tracing.internal.e;
import com.bytedance.ttgame.sdk.module.account.platform.api.IThirdAuthorizeService;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ConsentStatusRecord.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bã\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\n¢\u0006\u0002\u0010\u001cJ\t\u00107\u001a\u00020\u0003HÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\u000f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u001aHÆ\u0003J\t\u0010D\u001a\u00020\nHÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003J\t\u0010G\u001a\u00020\bHÆ\u0003J\t\u0010H\u001a\u00020\nHÆ\u0003J\t\u0010I\u001a\u00020\u0003HÆ\u0003J\t\u0010J\u001a\u00020\nHÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003Jç\u0001\u0010L\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u00032\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\nHÆ\u0001J\u0013\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010P\u001a\u00020\bHÖ\u0001J\t\u0010Q\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u0018\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0016\u0010\u0017\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0016\u0010\u0014\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001eR\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0016\u0010\u0016\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0016\u0010\u001b\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u0016\u0010\u0019\u001a\u00020\u001a8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010)R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001eR\u0016\u0010\u0015\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001eR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001eR\u0016\u0010\f\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010!R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001eR\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001eR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106¨\u0006R"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/data/ConsentStatusRecord;", "", "id", "", "category", "type", "name", "version", "", "createdAt", "", "createdBy", "updatedAt", "updatedBy", "regions", "", "regionsOp", "description", "validator", "owners", IThirdAuthorizeService.ThirdVariant.Meta, "status", "parentID", "key", "channel", "record", "Lcom/ss/android/ugc/aweme/pns/consentapi/data/LightConsentRecord;", "products", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/ss/android/ugc/aweme/pns/consentapi/data/LightConsentRecord;J)V", "getCategory", "()Ljava/lang/String;", "getChannel", "getCreatedAt", "()J", "getCreatedBy", "getDescription", "getId", "getKey", "getMeta", "getName", "getOwners", "()Ljava/util/List;", "getParentID", "getProducts", "getRecord", "()Lcom/ss/android/ugc/aweme/pns/consentapi/data/LightConsentRecord;", "getRegions", "getRegionsOp", "getStatus", "getType", "getUpdatedAt", "getUpdatedBy", "getValidator", "getVersion", "()I", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes9.dex */
public final class b {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private final String f18685a;
    @SerializedName("category")

    /* renamed from: b  reason: collision with root package name */
    private final String f18686b;
    @SerializedName("type")

    /* renamed from: c  reason: collision with root package name */
    private final String f18687c;
    @SerializedName("name")

    /* renamed from: d  reason: collision with root package name */
    private final String f18688d;
    @SerializedName("version")

    /* renamed from: e  reason: collision with root package name */
    private final int f18689e;
    @SerializedName("created_at")

    /* renamed from: f  reason: collision with root package name */
    private final long f18690f;
    @SerializedName("created_by")

    /* renamed from: g  reason: collision with root package name */
    private final String f18691g;
    @SerializedName("updated_at")

    /* renamed from: h  reason: collision with root package name */
    private final long f18692h;
    @SerializedName("updated_by")

    /* renamed from: i  reason: collision with root package name */
    private final String f18693i;
    @SerializedName("regions")

    /* renamed from: j  reason: collision with root package name */
    private final List<String> f18694j;
    @SerializedName("regions_op")
    private final String k;
    @SerializedName("description")
    private final String l;
    @SerializedName("validator")
    private final String m;
    @SerializedName("owners")
    private final List<String> n;
    @SerializedName(IThirdAuthorizeService.ThirdVariant.Meta)
    private final String o;
    @SerializedName("status")
    private final String p;
    @SerializedName(e.KEY_PARENT_ID)
    private final String q;
    @SerializedName("key")
    private final String r;
    @SerializedName("channel")
    private final String s;
    @SerializedName("record")
    private final d t;
    @SerializedName("products")
    private final long u;

    public b() {
        this(null, null, null, null, 0, 0L, null, 0L, null, null, null, null, null, null, null, null, null, null, null, null, 0L, 2097151, null);
    }

    public final long A() {
        return this.f18690f;
    }

    public final String B() {
        return this.f18691g;
    }

    public final long C() {
        return this.f18692h;
    }

    public final String D() {
        return this.f18693i;
    }

    public final List<String> E() {
        return this.f18694j;
    }

    public final String F() {
        return this.k;
    }

    public final String G() {
        return this.l;
    }

    public final String H() {
        return this.m;
    }

    public final List<String> I() {
        return this.n;
    }

    public final String J() {
        return this.o;
    }

    public final String K() {
        return this.p;
    }

    public final String L() {
        return this.q;
    }

    public final String M() {
        return this.r;
    }

    public final String N() {
        return this.s;
    }

    public final d O() {
        return this.t;
    }

    public final long P() {
        return this.u;
    }

    public final b a(String id, String category, String type, String name, int i2, long j2, String createdBy, long j3, String updatedBy, List<String> regions, String regionsOp, String description, String validator, List<String> owners, String meta, String status, String parentID, String key, String channel, d record, long j4) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(createdBy, "createdBy");
        Intrinsics.checkNotNullParameter(updatedBy, "updatedBy");
        Intrinsics.checkNotNullParameter(regions, "regions");
        Intrinsics.checkNotNullParameter(regionsOp, "regionsOp");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(validator, "validator");
        Intrinsics.checkNotNullParameter(owners, "owners");
        Intrinsics.checkNotNullParameter(meta, "meta");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(parentID, "parentID");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(record, "record");
        return new b(id, category, type, name, i2, j2, createdBy, j3, updatedBy, regions, regionsOp, description, validator, owners, meta, status, parentID, key, channel, record, j4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            return Intrinsics.areEqual(this.f18685a, bVar.f18685a) && Intrinsics.areEqual(this.f18686b, bVar.f18686b) && Intrinsics.areEqual(this.f18687c, bVar.f18687c) && Intrinsics.areEqual(this.f18688d, bVar.f18688d) && this.f18689e == bVar.f18689e && this.f18690f == bVar.f18690f && Intrinsics.areEqual(this.f18691g, bVar.f18691g) && this.f18692h == bVar.f18692h && Intrinsics.areEqual(this.f18693i, bVar.f18693i) && Intrinsics.areEqual(this.f18694j, bVar.f18694j) && Intrinsics.areEqual(this.k, bVar.k) && Intrinsics.areEqual(this.l, bVar.l) && Intrinsics.areEqual(this.m, bVar.m) && Intrinsics.areEqual(this.n, bVar.n) && Intrinsics.areEqual(this.o, bVar.o) && Intrinsics.areEqual(this.p, bVar.p) && Intrinsics.areEqual(this.q, bVar.q) && Intrinsics.areEqual(this.r, bVar.r) && Intrinsics.areEqual(this.s, bVar.s) && Intrinsics.areEqual(this.t, bVar.t) && this.u == bVar.u;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((this.f18685a.hashCode() * 31) + this.f18686b.hashCode()) * 31) + this.f18687c.hashCode()) * 31) + this.f18688d.hashCode()) * 31) + Integer.hashCode(this.f18689e)) * 31) + Long.hashCode(this.f18690f)) * 31) + this.f18691g.hashCode()) * 31) + Long.hashCode(this.f18692h)) * 31) + this.f18693i.hashCode()) * 31) + this.f18694j.hashCode()) * 31) + this.k.hashCode()) * 31) + this.l.hashCode()) * 31) + this.m.hashCode()) * 31) + this.n.hashCode()) * 31) + this.o.hashCode()) * 31) + this.p.hashCode()) * 31) + this.q.hashCode()) * 31) + this.r.hashCode()) * 31) + this.s.hashCode()) * 31) + this.t.hashCode()) * 31) + Long.hashCode(this.u);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConsentStatusRecord(id=").append(this.f18685a).append(", category=").append(this.f18686b).append(", type=").append(this.f18687c).append(", name=").append(this.f18688d).append(", version=").append(this.f18689e).append(", createdAt=").append(this.f18690f).append(", createdBy=").append(this.f18691g).append(", updatedAt=").append(this.f18692h).append(", updatedBy=").append(this.f18693i).append(", regions=").append(this.f18694j).append(", regionsOp=").append(this.k).append(", description=");
        sb.append(this.l).append(", validator=").append(this.m).append(", owners=").append(this.n).append(", meta=").append(this.o).append(", status=").append(this.p).append(", parentID=").append(this.q).append(", key=").append(this.r).append(", channel=").append(this.s).append(", record=").append(this.t).append(", products=").append(this.u).append(')');
        return sb.toString();
    }

    public final String v() {
        return this.f18685a;
    }

    public final String w() {
        return this.f18686b;
    }

    public final String x() {
        return this.f18687c;
    }

    public final String y() {
        return this.f18688d;
    }

    public final int z() {
        return this.f18689e;
    }

    public b(String id, String category, String type, String name, int i2, long j2, String createdBy, long j3, String updatedBy, List<String> regions, String regionsOp, String description, String validator, List<String> owners, String meta, String status, String parentID, String key, String channel, d record, long j4) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(createdBy, "createdBy");
        Intrinsics.checkNotNullParameter(updatedBy, "updatedBy");
        Intrinsics.checkNotNullParameter(regions, "regions");
        Intrinsics.checkNotNullParameter(regionsOp, "regionsOp");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(validator, "validator");
        Intrinsics.checkNotNullParameter(owners, "owners");
        Intrinsics.checkNotNullParameter(meta, "meta");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(parentID, "parentID");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(record, "record");
        this.f18685a = id;
        this.f18686b = category;
        this.f18687c = type;
        this.f18688d = name;
        this.f18689e = i2;
        this.f18690f = j2;
        this.f18691g = createdBy;
        this.f18692h = j3;
        this.f18693i = updatedBy;
        this.f18694j = regions;
        this.k = regionsOp;
        this.l = description;
        this.m = validator;
        this.n = owners;
        this.o = meta;
        this.p = status;
        this.q = parentID;
        this.r = key;
        this.s = channel;
        this.t = record;
        this.u = j4;
    }

    public final String a() {
        return this.f18685a;
    }

    public final String b() {
        return this.f18686b;
    }

    public final String c() {
        return this.f18687c;
    }

    public final String d() {
        return this.f18688d;
    }

    public final int e() {
        return this.f18689e;
    }

    public final long f() {
        return this.f18690f;
    }

    public final String g() {
        return this.f18691g;
    }

    public final long h() {
        return this.f18692h;
    }

    public final String i() {
        return this.f18693i;
    }

    public /* synthetic */ b(String str, String str2, String str3, String str4, int i2, long j2, String str5, long j3, String str6, List list, String str7, String str8, String str9, List list2, String str10, String str11, String str12, String str13, String str14, d dVar, long j4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? "" : str4, (i3 & 16) != 0 ? 0 : i2, (i3 & 32) != 0 ? 0L : j2, (i3 & 64) != 0 ? "" : str5, (i3 & 128) != 0 ? 0L : j3, (i3 & 256) != 0 ? "" : str6, (i3 & 512) != 0 ? new ArrayList() : list, (i3 & 1024) != 0 ? "" : str7, (i3 & 2048) != 0 ? "" : str8, (i3 & 4096) != 0 ? "" : str9, (i3 & 8192) != 0 ? new ArrayList() : list2, (i3 & 16384) != 0 ? "" : str10, (i3 & 32768) != 0 ? "ACTIVE" : str11, (i3 & 65536) != 0 ? "" : str12, (i3 & 131072) != 0 ? "" : str13, (i3 & 262144) != 0 ? "" : str14, (i3 & 524288) != 0 ? new d(null, null, null, 0L, null, 31, null) : dVar, (i3 & 1048576) != 0 ? 0L : j4);
    }

    public final List<String> j() {
        return this.f18694j;
    }

    public final String k() {
        return this.k;
    }

    public final String l() {
        return this.l;
    }

    public final String m() {
        return this.m;
    }

    public final List<String> n() {
        return this.n;
    }

    public final String o() {
        return this.o;
    }

    public final String p() {
        return this.p;
    }

    public final String q() {
        return this.q;
    }

    public final String r() {
        return this.r;
    }

    public final String s() {
        return this.s;
    }

    public final d t() {
        return this.t;
    }

    public final long u() {
        return this.u;
    }
}
