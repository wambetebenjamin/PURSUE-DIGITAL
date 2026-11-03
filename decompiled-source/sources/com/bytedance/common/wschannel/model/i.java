package com.bytedance.common.wschannel.model;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.common.wschannel.event.d;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* compiled from: WsChannelMsg.java */
/* loaded from: classes2.dex */
public class i implements Parcelable {
    public static final Parcelable.Creator<i> CREATOR = new Parcelable.Creator<i>() { // from class: com.bytedance.common.wschannel.model.i.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public i[] newArray(int i2) {
            return new i[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static i f5832a = new i();

    /* renamed from: b  reason: collision with root package name */
    public transient long f5833b;

    /* renamed from: c  reason: collision with root package name */
    private long f5834c;

    /* renamed from: d  reason: collision with root package name */
    private long f5835d;

    /* renamed from: e  reason: collision with root package name */
    private int f5836e;

    /* renamed from: f  reason: collision with root package name */
    private int f5837f;

    /* renamed from: g  reason: collision with root package name */
    private List<b> f5838g;

    /* renamed from: h  reason: collision with root package name */
    private String f5839h;

    /* renamed from: i  reason: collision with root package name */
    private String f5840i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f5841j;
    private ComponentName k;
    private int l;
    private c m;
    private String n;
    private boolean o;
    private d.a p;
    private String q;
    private String r;
    private String s;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public i() {
        this.p = d.a.Default;
    }

    public i(int i2, int i3, int i4, String str, boolean z) {
        this.p = d.a.Default;
        this.l = i2;
        this.f5836e = i3;
        this.f5837f = i4;
        this.n = str;
        this.o = z;
    }

    public i(int i2, long j2, long j3, int i3, int i4, List<b> list, String str, String str2, byte[] bArr, ComponentName componentName) {
        this.p = d.a.Default;
        this.l = i2;
        this.f5834c = j2;
        this.f5835d = j3;
        this.f5836e = i3;
        this.f5837f = i4;
        this.f5838g = list;
        this.f5839h = str;
        this.f5840i = str2;
        this.f5841j = bArr;
        this.k = componentName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public i(i iVar) {
        this.p = d.a.Default;
        a(iVar.i());
        b(iVar.j());
        a(iVar.k());
        b(iVar.l());
        a(iVar.m());
        a(iVar.q());
        e(iVar.o());
        f(iVar.p());
        c(iVar.n());
        a(iVar.r());
        a(iVar.s());
        a(iVar.d());
        a(iVar.c());
        a(iVar.h());
        d(iVar.q);
        c(iVar.s);
        b(iVar.r);
    }

    public String c() {
        return this.n;
    }

    public void a(String str) {
        this.n = str;
    }

    public boolean d() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public String e() {
        return this.r;
    }

    public void b(String str) {
        this.r = str;
    }

    public String f() {
        return this.s;
    }

    public void c(String str) {
        this.s = str;
    }

    public String g() {
        return this.q;
    }

    public void d(String str) {
        this.q = str;
    }

    public d.a h() {
        return this.p;
    }

    public void a(d.a aVar) {
        this.p = aVar;
    }

    public long i() {
        return this.f5834c;
    }

    public void a(long j2) {
        this.f5834c = j2;
    }

    public long j() {
        return this.f5835d;
    }

    public void b(long j2) {
        this.f5835d = j2;
    }

    public int k() {
        return this.f5836e;
    }

    public void a(int i2) {
        this.f5836e = i2;
    }

    public int l() {
        return this.f5837f;
    }

    public void b(int i2) {
        this.f5837f = i2;
    }

    public List<b> m() {
        return this.f5838g;
    }

    public void a(List<b> list) {
        this.f5838g = list;
    }

    public int n() {
        return this.l;
    }

    public void c(int i2) {
        this.l = i2;
    }

    public String o() {
        return this.f5839h;
    }

    public void e(String str) {
        this.f5839h = str;
    }

    public String p() {
        return this.f5840i;
    }

    public void f(String str) {
        this.f5840i = str;
    }

    public byte[] q() {
        if (this.f5841j == null) {
            this.f5841j = new byte[1];
        }
        return this.f5841j;
    }

    public void a(byte[] bArr) {
        this.f5841j = bArr;
    }

    public ComponentName r() {
        return this.k;
    }

    public void a(ComponentName componentName) {
        this.k = componentName;
    }

    public c s() {
        return this.m;
    }

    public void a(c cVar) {
        this.m = cVar;
    }

    public String toString() {
        return "WsChannelMsg{, channelId = " + this.l + ", logId=" + this.f5835d + ", service=" + this.f5836e + ", method=" + this.f5837f + ", msgHeaders=" + this.f5838g + ", payloadEncoding='" + this.f5839h + "', payloadType='" + this.f5840i + "', payload=" + Arrays.toString(this.f5841j) + ", replayToComponentName=" + this.k + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f5834c);
        parcel.writeLong(this.f5835d);
        parcel.writeInt(this.f5836e);
        parcel.writeInt(this.f5837f);
        parcel.writeTypedList(this.f5838g);
        parcel.writeString(this.f5839h);
        parcel.writeString(this.f5840i);
        parcel.writeByteArray(this.f5841j);
        parcel.writeParcelable(this.k, i2);
        parcel.writeInt(this.l);
        parcel.writeString(this.n);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.p.a());
        parcel.writeParcelable(this.m, i2);
        parcel.writeString(this.q);
        parcel.writeString(this.s);
        parcel.writeString(this.r);
    }

    /* compiled from: WsChannelMsg.java */
    /* loaded from: classes2.dex */
    public static class b implements Parcelable {
        public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.bytedance.common.wschannel.model.i.b.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public b createFromParcel(Parcel parcel) {
                b bVar = new b();
                bVar.f5852a = parcel.readString();
                bVar.f5853b = parcel.readString();
                return bVar;
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public b[] newArray(int i2) {
                return new b[i2];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        String f5852a;

        /* renamed from: b  reason: collision with root package name */
        String f5853b;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String a() {
            return this.f5852a;
        }

        public void a(String str) {
            this.f5852a = str;
        }

        public String b() {
            return this.f5853b;
        }

        public void b(String str) {
            this.f5853b = str;
        }

        public String toString() {
            return "MsgHeader{key='" + this.f5852a + "', value='" + this.f5853b + "'}";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f5852a);
            parcel.writeString(this.f5853b);
        }
    }

    /* compiled from: WsChannelMsg.java */
    /* loaded from: classes2.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final int f5842a;

        /* renamed from: c  reason: collision with root package name */
        private long f5844c;

        /* renamed from: d  reason: collision with root package name */
        private int f5845d;

        /* renamed from: e  reason: collision with root package name */
        private int f5846e;

        /* renamed from: f  reason: collision with root package name */
        private byte[] f5847f;

        /* renamed from: i  reason: collision with root package name */
        private long f5850i;

        /* renamed from: j  reason: collision with root package name */
        private ComponentName f5851j;

        /* renamed from: b  reason: collision with root package name */
        private Map<String, String> f5843b = new HashMap();

        /* renamed from: g  reason: collision with root package name */
        private String f5848g = "";

        /* renamed from: h  reason: collision with root package name */
        private String f5849h = "";

        public a(int i2) {
            this.f5842a = i2;
        }

        public static a a(int i2) {
            return new a(i2);
        }

        public a a(String str, String str2) {
            this.f5843b.put(str, str2);
            return this;
        }

        public a a(long j2) {
            this.f5844c = j2;
            return this;
        }

        public a b(int i2) {
            this.f5845d = i2;
            return this;
        }

        public a b(long j2) {
            this.f5850i = j2;
            return this;
        }

        public a c(int i2) {
            this.f5846e = i2;
            return this;
        }

        public a a(byte[] bArr) {
            this.f5847f = bArr;
            return this;
        }

        public a a(String str) {
            this.f5848g = str;
            return this;
        }

        public a b(String str) {
            this.f5849h = str;
            return this;
        }

        public a a(ComponentName componentName) {
            this.f5851j = componentName;
            return this;
        }

        public i a() {
            if (this.f5842a <= 0) {
                throw new IllegalArgumentException("illegal channelId");
            }
            if (this.f5845d < 0) {
                throw new IllegalArgumentException("illegal service");
            }
            if (this.f5846e <= 0) {
                throw new IllegalArgumentException("illegal method");
            }
            if (this.f5847f == null) {
                throw new IllegalArgumentException("illegal payload");
            }
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, String> entry : this.f5843b.entrySet()) {
                b bVar = new b();
                bVar.a(entry.getKey());
                bVar.b(entry.getValue());
                arrayList.add(bVar);
            }
            return new i(this.f5842a, this.f5850i, this.f5844c, this.f5845d, this.f5846e, arrayList, this.f5849h, this.f5848g, this.f5847f, this.f5851j);
        }
    }

    protected i(Parcel parcel) {
        this.p = d.a.Default;
        this.f5834c = parcel.readLong();
        this.f5835d = parcel.readLong();
        this.f5836e = parcel.readInt();
        this.f5837f = parcel.readInt();
        this.f5838g = parcel.createTypedArrayList(b.CREATOR);
        this.f5839h = parcel.readString();
        this.f5840i = parcel.readString();
        this.f5841j = parcel.createByteArray();
        this.k = (ComponentName) parcel.readParcelable(ComponentName.class.getClassLoader());
        this.l = parcel.readInt();
        this.n = parcel.readString();
        this.o = parcel.readByte() != 0;
        this.p = d.a.a(parcel.readInt());
        this.m = (c) parcel.readParcelable(c.class.getClassLoader());
        this.q = parcel.readString();
        this.s = parcel.readString();
        this.r = parcel.readString();
    }

    public Map<String, String> t() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("seqId", String.valueOf(this.f5834c));
        linkedHashMap.put("logId", String.valueOf(this.f5835d));
        linkedHashMap.put("service", String.valueOf(this.f5836e));
        linkedHashMap.put("method", String.valueOf(this.f5837f));
        linkedHashMap.put("payloadEncoding", this.f5839h);
        linkedHashMap.put("payloadType", this.f5840i);
        try {
            linkedHashMap.put(WsConstants.KEY_PAYLOAD_MD5, com.bytedance.common.wschannel.utils.e.a(MessageDigest.getInstance("MD5").digest(this.f5841j), ""));
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        linkedHashMap.put("channelId", String.valueOf(this.l));
        for (b bVar : this.f5838g) {
            linkedHashMap.put(bVar.f5852a, bVar.f5853b);
        }
        return linkedHashMap;
    }

    public void a(String str, String str2) {
        b bVar = new b();
        bVar.a(str);
        bVar.b(str2);
        this.f5838g.add(bVar);
    }
}
