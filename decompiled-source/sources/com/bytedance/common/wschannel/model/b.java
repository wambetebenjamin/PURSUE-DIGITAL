package com.bytedance.common.wschannel.model;

import android.os.Parcel;
import android.os.Parcelable;
/* compiled from: IntegerParcelable.java */
/* loaded from: classes2.dex */
public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.bytedance.common.wschannel.model.b.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public b[] newArray(int i2) {
            return new b[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f5791a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public b(int i2) {
        this.f5791a = i2;
    }

    protected b(Parcel parcel) {
        this.f5791a = parcel.readInt();
    }

    public int a() {
        return this.f5791a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f5791a);
    }
}
