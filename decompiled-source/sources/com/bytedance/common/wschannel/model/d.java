package com.bytedance.common.wschannel.model;

import android.os.Parcel;
import android.os.Parcelable;
/* compiled from: ServiceConnectEvent.java */
/* loaded from: classes2.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: com.bytedance.common.wschannel.model.d.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel.readInt(), parcel.readInt(), parcel.readByte() != 0, parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public d[] newArray(int i2) {
            return new d[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f5795a;

    /* renamed from: b  reason: collision with root package name */
    private final int f5796b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f5797c;

    /* renamed from: d  reason: collision with root package name */
    private final String f5798d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public d(int i2, int i3, boolean z, String str) {
        this.f5795a = i2;
        this.f5796b = i3;
        this.f5797c = z;
        this.f5798d = str;
    }

    public int a() {
        return this.f5795a;
    }

    public int b() {
        return this.f5796b;
    }

    public boolean c() {
        return this.f5797c;
    }

    public String d() {
        return this.f5798d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f5795a);
        parcel.writeInt(this.f5796b);
        parcel.writeByte(this.f5797c ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f5798d);
    }

    public String toString() {
        return "ServiceConnectEvent{mChannelId=" + this.f5795a + ", mServiceId=" + this.f5796b + ", mConnected=" + this.f5797c + ", mLogInfo='" + this.f5798d + "'}";
    }
}
