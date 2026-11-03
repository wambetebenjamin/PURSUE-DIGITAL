package com.bytedance.common.wschannel.model;

import android.os.Parcel;
import android.os.Parcelable;
/* compiled from: NewMsgTimeHolder.java */
/* loaded from: classes2.dex */
public class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.bytedance.common.wschannel.model.c.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public c[] newArray(int i2) {
            return new c[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public final long f5792a;

    /* renamed from: b  reason: collision with root package name */
    public final long f5793b;

    /* renamed from: c  reason: collision with root package name */
    public final long f5794c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public c(long j2, long j3, long j4) {
        this.f5792a = j2;
        this.f5793b = j3;
        this.f5794c = j4;
    }

    protected c(Parcel parcel) {
        this.f5792a = parcel.readLong();
        this.f5793b = parcel.readLong();
        this.f5794c = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f5792a);
        parcel.writeLong(this.f5793b);
        parcel.writeLong(this.f5794c);
    }
}
