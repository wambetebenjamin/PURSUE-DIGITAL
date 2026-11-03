package com.bytedance.apm.util;

import android.os.Parcel;
import android.os.Parcelable;
/* compiled from: SpPair.java */
/* loaded from: classes.dex */
public class af implements Parcelable {
    public static final Parcelable.Creator<af> CREATOR = new Parcelable.Creator<af>() { // from class: com.bytedance.apm.util.af.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public af createFromParcel(Parcel parcel) {
            return new af(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public af[] newArray(int i2) {
            return new af[i2];
        }
    };
    public String mKey;
    public Object mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public af(String str, Object obj) {
        this.mKey = str;
        this.mValue = obj;
    }

    protected af(Parcel parcel) {
        this.mKey = parcel.readString();
        this.mValue = parcel.readValue(getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mKey);
        parcel.writeValue(this.mValue);
    }
}
