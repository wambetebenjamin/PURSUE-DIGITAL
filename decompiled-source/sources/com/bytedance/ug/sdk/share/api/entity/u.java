package com.bytedance.ug.sdk.share.api.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* compiled from: UrlList.java */
/* loaded from: classes8.dex */
public class u implements Parcelable, Serializable {
    public static final Parcelable.Creator<u> CREATOR = new Parcelable.Creator<u>() { // from class: com.bytedance.ug.sdk.share.api.entity.u.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public u createFromParcel(Parcel parcel) {
            return new u(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public u[] newArray(int i2) {
            return new u[i2];
        }
    };
    @SerializedName("url")
    public String mUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mUrl);
    }

    public u() {
    }

    protected u(Parcel parcel) {
        this.mUrl = parcel.readString();
    }
}
