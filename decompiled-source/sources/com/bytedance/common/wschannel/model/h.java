package com.bytedance.common.wschannel.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;
/* compiled from: WsApi.java */
/* loaded from: classes2.dex */
public class h implements Parcelable {
    public static final Parcelable.Creator<h> CREATOR = new Parcelable.Creator<h>() { // from class: com.bytedance.common.wschannel.model.h.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public h createFromParcel(Parcel parcel) {
            h hVar = new h();
            hVar.f5830a = parcel.readInt();
            hVar.f5831b = parcel.readInt();
            return hVar;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public h[] newArray(int i2) {
            return new h[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    int f5830a;

    /* renamed from: b  reason: collision with root package name */
    int f5831b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int a() {
        return this.f5830a;
    }

    public void a(int i2) {
        this.f5830a = i2;
    }

    public int b() {
        return this.f5831b;
    }

    public void b(int i2) {
        this.f5831b = i2;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("service", this.f5830a);
        jSONObject.put("method", this.f5831b);
        return jSONObject;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.f5830a = jSONObject.optInt("service");
        this.f5831b = jSONObject.optInt("method");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f5830a);
        parcel.writeInt(this.f5831b);
    }
}
