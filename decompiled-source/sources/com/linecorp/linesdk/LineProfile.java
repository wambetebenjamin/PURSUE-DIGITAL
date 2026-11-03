package com.linecorp.linesdk;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes9.dex */
public class LineProfile implements Parcelable {
    public static final Parcelable.Creator<LineProfile> CREATOR = new Parcelable.Creator<LineProfile>() { // from class: com.linecorp.linesdk.LineProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineProfile createFromParcel(Parcel parcel) {
            return new LineProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineProfile[] newArray(int i2) {
            return new LineProfile[i2];
        }
    };
    private final String displayName;
    private final Uri pictureUrl;
    private final String statusMessage;
    private final String userId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LineProfile(String str, String str2, Uri uri, String str3) {
        this.userId = str;
        this.displayName = str2;
        this.pictureUrl = uri;
        this.statusMessage = str3;
    }

    private LineProfile(Parcel parcel) {
        this.userId = parcel.readString();
        this.displayName = parcel.readString();
        this.pictureUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.statusMessage = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.userId);
        parcel.writeString(this.displayName);
        parcel.writeParcelable(this.pictureUrl, i2);
        parcel.writeString(this.statusMessage);
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getUserId() {
        return this.userId;
    }

    public Uri getPictureUrl() {
        return this.pictureUrl;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            LineProfile lineProfile = (LineProfile) obj;
            if (!this.userId.equals(lineProfile.userId) || !this.displayName.equals(lineProfile.displayName)) {
                return false;
            }
            Uri uri = this.pictureUrl;
            if (uri == null ? lineProfile.pictureUrl != null : !uri.equals(lineProfile.pictureUrl)) {
                return false;
            }
            String str = this.statusMessage;
            String str2 = lineProfile.statusMessage;
            if (str != null) {
                return str.equals(str2);
            }
            if (str2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.userId.hashCode() * 31) + this.displayName.hashCode()) * 31;
        Uri uri = this.pictureUrl;
        int hashCode2 = (hashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        String str = this.statusMessage;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "LineProfile{displayName='" + this.displayName + "', userId='" + this.userId + "', pictureUrl='" + this.pictureUrl + "', statusMessage='" + this.statusMessage + "'}";
    }
}
