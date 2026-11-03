package com.bytedance.common.wschannel.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.bytedance.common.wschannel.WsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: SsWsApp.java */
/* loaded from: classes2.dex */
public class g implements Parcelable, com.bytedance.common.wschannel.app.b {
    public static final Parcelable.Creator<g> CREATOR = new Parcelable.Creator<g>() { // from class: com.bytedance.common.wschannel.model.g.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public g[] newArray(int i2) {
            return new g[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    boolean f5810a;

    /* renamed from: b  reason: collision with root package name */
    List<Integer> f5811b;

    /* renamed from: c  reason: collision with root package name */
    String f5812c;

    /* renamed from: d  reason: collision with root package name */
    int f5813d;

    /* renamed from: e  reason: collision with root package name */
    boolean f5814e;

    /* renamed from: f  reason: collision with root package name */
    private Map<String, String> f5815f;

    /* renamed from: g  reason: collision with root package name */
    private List<String> f5816g;

    /* renamed from: h  reason: collision with root package name */
    private int f5817h;

    /* renamed from: i  reason: collision with root package name */
    private String f5818i;

    /* renamed from: j  reason: collision with root package name */
    private int f5819j;
    private String k;
    private String l;
    private int m;
    private int n;
    private int o;
    private String p;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public int j() {
        return this.o;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public String k() {
        return this.f5818i;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public List<String> l() {
        return this.f5816g;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public boolean m() {
        return this.f5810a;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public List<Integer> n() {
        return this.f5811b;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public String o() {
        return this.f5812c;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public int p() {
        return this.f5813d;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public boolean q() {
        return this.f5814e;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public String i() {
        return this.p;
    }

    protected g() {
        this.f5815f = new HashMap();
        this.f5816g = new ArrayList();
        this.f5811b = new ArrayList();
    }

    private g(int i2, int i3, String str, String str2, List<String> list, int i4, int i5, int i6, String str3, String str4, boolean z, List<Integer> list2, String str5, int i7, boolean z2, a aVar) {
        this.f5815f = new HashMap();
        this.f5816g = new ArrayList();
        this.f5811b = new ArrayList();
        this.f5819j = i3;
        this.f5817h = i2;
        this.k = str;
        this.l = str2;
        if (list != null) {
            this.f5816g.addAll(list);
        }
        this.m = i4;
        this.n = i5;
        this.o = i6;
        this.p = str3;
        this.f5818i = str4;
        if (aVar.p != null) {
            this.f5815f.putAll(aVar.p);
        }
        this.f5810a = z;
        this.f5811b = list2;
        this.f5812c = str5;
        this.f5813d = i7;
        this.f5814e = z2;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public int a() {
        return this.f5817h;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public int b() {
        return this.f5819j;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public String c() {
        return this.k;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public String d() {
        return this.l;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public int e() {
        return this.m;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public int f() {
        return this.n;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public Map<String, String> g() {
        return this.f5815f;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public JSONObject h() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(WsConstants.KEY_CHANNEL_ID, this.f5817h);
        jSONObject.put("app_id", this.f5819j);
        jSONObject.put("device_id", this.k);
        jSONObject.put("install_id", this.l);
        JSONArray jSONArray = new JSONArray();
        List<String> list = this.f5816g;
        if (list != null) {
            for (String str : list) {
                jSONArray.put(str);
            }
        }
        jSONObject.put("headers", com.bytedance.common.wschannel.utils.e.a(this.f5815f));
        jSONObject.put("urls", jSONArray);
        jSONObject.put("app_version", this.m);
        jSONObject.put("platform", this.n);
        jSONObject.put(WsConstants.KEY_FPID, this.o);
        jSONObject.put("app_kay", this.p);
        jSONObject.put("extra", this.f5818i);
        jSONObject.put(WsConstants.KEY_PRIVATE_PROTOCOL_ENABLE, this.f5810a);
        JSONArray jSONArray2 = new JSONArray();
        List<Integer> list2 = this.f5811b;
        if (list2 != null) {
            for (Integer num : list2) {
                jSONArray2.put(num);
            }
        }
        jSONObject.put(WsConstants.KEY_SERVICE_ID_LIST, jSONArray2);
        jSONObject.put(WsConstants.KEY_PRIVATE_PROTOCOL_URL, this.f5812c);
        jSONObject.put(WsConstants.KEY_TRANSPORT_MODE, this.f5813d);
        jSONObject.put(WsConstants.KEY_DISABLE_FALLBACK_WEBSOCKET, this.f5814e);
        return jSONObject;
    }

    @Override // com.bytedance.common.wschannel.app.b
    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.f5817h = jSONObject.optInt(WsConstants.KEY_CHANNEL_ID);
        this.f5819j = jSONObject.optInt("app_id");
        this.k = jSONObject.optString("device_id");
        this.l = jSONObject.optString("install_id");
        this.m = jSONObject.optInt("app_version");
        this.n = jSONObject.optInt("platform");
        this.o = jSONObject.optInt(WsConstants.KEY_FPID);
        this.p = jSONObject.optString("app_kay");
        this.f5818i = jSONObject.optString("extra");
        JSONArray optJSONArray = jSONObject.optJSONArray("urls");
        this.f5816g.clear();
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                this.f5816g.add(optJSONArray.optString(i2));
            }
        }
        this.f5810a = jSONObject.optBoolean(WsConstants.KEY_PRIVATE_PROTOCOL_ENABLE);
        JSONArray optJSONArray2 = jSONObject.optJSONArray(WsConstants.KEY_SERVICE_ID_LIST);
        this.f5811b.clear();
        if (optJSONArray2 != null) {
            int length2 = optJSONArray2.length();
            for (int i3 = 0; i3 < length2; i3++) {
                this.f5811b.add(Integer.valueOf(optJSONArray2.optInt(i3)));
            }
        }
        this.f5812c = jSONObject.optString(WsConstants.KEY_PRIVATE_PROTOCOL_URL);
        this.f5813d = jSONObject.optInt(WsConstants.KEY_TRANSPORT_MODE);
        this.f5814e = jSONObject.optBoolean(WsConstants.KEY_DISABLE_FALLBACK_WEBSOCKET);
        this.f5815f.clear();
        this.f5815f.putAll(com.bytedance.common.wschannel.utils.e.a(jSONObject.optJSONObject("headers")));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        if (this.f5817h == gVar.f5817h && this.f5819j == gVar.f5819j && this.m == gVar.m && this.n == gVar.n && this.o == gVar.o && this.f5815f.equals(gVar.f5815f)) {
            List<String> list = this.f5816g;
            if (list == null ? gVar.f5816g == null : list.equals(gVar.f5816g)) {
                String str = this.f5818i;
                if (str == null ? gVar.f5818i == null : str.equals(gVar.f5818i)) {
                    String str2 = this.k;
                    if (str2 == null ? gVar.k == null : str2.equals(gVar.k)) {
                        String str3 = this.l;
                        if (str3 == null ? gVar.l == null : str3.equals(gVar.l)) {
                            String str4 = this.p;
                            String str5 = gVar.p;
                            return str4 != null ? str4.equals(str5) : str5 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.f5815f.hashCode() * 31;
        List<String> list = this.f5816g;
        int hashCode2 = (((hashCode + (list != null ? list.hashCode() : 0)) * 31) + this.f5817h) * 31;
        String str = this.f5818i;
        int hashCode3 = (((hashCode2 + (str != null ? str.hashCode() : 0)) * 31) + this.f5819j) * 31;
        String str2 = this.k;
        int hashCode4 = (hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.l;
        int hashCode5 = (((((((hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.m) * 31) + this.n) * 31) + this.o) * 31;
        String str4 = this.p;
        return hashCode5 + (str4 != null ? str4.hashCode() : 0);
    }

    /* compiled from: SsWsApp.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        boolean f5820a;

        /* renamed from: b  reason: collision with root package name */
        List<Integer> f5821b = new ArrayList();

        /* renamed from: c  reason: collision with root package name */
        String f5822c;

        /* renamed from: d  reason: collision with root package name */
        int f5823d;

        /* renamed from: e  reason: collision with root package name */
        boolean f5824e;

        /* renamed from: f  reason: collision with root package name */
        private int f5825f;

        /* renamed from: g  reason: collision with root package name */
        private String f5826g;

        /* renamed from: h  reason: collision with root package name */
        private String f5827h;

        /* renamed from: i  reason: collision with root package name */
        private int f5828i;

        /* renamed from: j  reason: collision with root package name */
        private int f5829j;
        private int k;
        private String l;
        private String m;
        private int n;
        private List<String> o;
        private Map<String, String> p;

        public a a(int i2) {
            this.f5825f = i2;
            return this;
        }

        public a a(String str) {
            this.f5826g = str;
            return this;
        }

        public a b(String str) {
            this.f5827h = str;
            return this;
        }

        public a b(int i2) {
            this.f5828i = i2;
            return this;
        }

        public a c(int i2) {
            this.f5829j = i2;
            return this;
        }

        public a c(String str) {
            this.m = str;
            return this;
        }

        public a d(int i2) {
            this.k = i2;
            return this;
        }

        public a d(String str) {
            this.l = str;
            return this;
        }

        public g a() {
            return new g(this.n, this.f5825f, this.f5826g, this.f5827h, this.o, this.f5828i, this.f5829j, this.k, this.l, this.m, this.f5820a, this.f5821b, this.f5822c, this.f5823d, this.f5824e, this);
        }

        public g a(JSONObject jSONObject) {
            g gVar = new g();
            gVar.a(jSONObject);
            return gVar;
        }

        public a e(int i2) {
            this.n = i2;
            return this;
        }

        public a a(List<String> list) {
            this.o = list;
            return this;
        }

        public a a(Map<String, String> map) {
            this.p = map;
            return this;
        }

        public a a(boolean z) {
            this.f5820a = z;
            return this;
        }

        public a b(List<Integer> list) {
            this.f5821b = list;
            return this;
        }

        public a e(String str) {
            this.f5822c = str;
            return this;
        }

        public a f(int i2) {
            this.f5823d = i2;
            return this;
        }

        public a b(boolean z) {
            this.f5824e = z;
            return this;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f5815f.size());
        for (Map.Entry<String, String> entry : this.f5815f.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
        parcel.writeStringList(this.f5816g);
        parcel.writeInt(this.f5817h);
        parcel.writeString(this.f5818i);
        parcel.writeInt(this.f5819j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
        parcel.writeString(this.p);
        parcel.writeByte(this.f5810a ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f5811b.size());
        for (Integer num : this.f5811b) {
            parcel.writeInt(num.intValue());
        }
        parcel.writeString(this.f5812c);
        parcel.writeInt(this.f5813d);
        parcel.writeByte(this.f5814e ? (byte) 1 : (byte) 0);
    }

    protected g(Parcel parcel) {
        this.f5815f = new HashMap();
        this.f5816g = new ArrayList();
        this.f5811b = new ArrayList();
        int readInt = parcel.readInt();
        this.f5815f = new HashMap(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            this.f5815f.put(parcel.readString(), parcel.readString());
        }
        this.f5816g = parcel.createStringArrayList();
        this.f5817h = parcel.readInt();
        this.f5818i = parcel.readString();
        this.f5819j = parcel.readInt();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = parcel.readString();
        this.f5810a = parcel.readByte() != 0;
        int readInt2 = parcel.readInt();
        for (int i3 = 0; i3 < readInt2; i3++) {
            this.f5811b.add(Integer.valueOf(parcel.readInt()));
        }
        this.f5812c = parcel.readString();
        this.f5813d = parcel.readInt();
        this.f5814e = parcel.readByte() != 0;
    }
}
