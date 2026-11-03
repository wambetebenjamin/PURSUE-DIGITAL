package com.bytedance.apm.core;

import android.os.Parcel;
import android.os.Parcelable;
/* compiled from: SpPair.java */
/* loaded from: classes.dex */
public class g implements Parcelable {
    public static final Parcelable.Creator<g> CREATOR = new Parcelable.Creator<g>() { // from class: com.bytedance.apm.core.g.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public g[] newArray(int i2) {
            return new g[i2];
        }
    };
    public String mKey;
    public Object mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public g(String str, Object obj) {
        this.mKey = str;
        this.mValue = obj;
    }

    protected g(Parcel parcel) {
        this.mKey = parcel.readString();
        this.mValue = parcel.readValue(getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mKey);
        parcel.writeValue(this.mValue);
    }
}
