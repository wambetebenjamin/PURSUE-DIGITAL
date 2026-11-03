package com.bytedance.common.wschannel.model;

import android.os.Parcel;
import android.os.Parcelable;
/* compiled from: ServiceParcelable.java */
/* loaded from: classes2.dex */
public class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new Parcelable.Creator<e>() { // from class: com.bytedance.common.wschannel.model.e.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public e[] newArray(int i2) {
            return new e[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f5799a;

    /* renamed from: b  reason: collision with root package name */
    private final int f5800b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public e(int i2, int i3) {
        this.f5799a = i2;
        this.f5800b = i3;
    }

    public int a() {
        return this.f5799a;
    }

    public int b() {
        return this.f5800b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f5799a);
        parcel.writeInt(this.f5800b);
    }
}
