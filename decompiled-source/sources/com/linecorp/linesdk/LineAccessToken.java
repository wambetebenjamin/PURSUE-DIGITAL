package com.linecorp.linesdk;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes9.dex */
public class LineAccessToken implements Parcelable {
    public static final Parcelable.Creator<LineAccessToken> CREATOR = new Parcelable.Creator<LineAccessToken>() { // from class: com.linecorp.linesdk.LineAccessToken.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineAccessToken createFromParcel(Parcel parcel) {
            return new LineAccessToken(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineAccessToken[] newArray(int i2) {
            return new LineAccessToken[i2];
        }
    };
    private final String accessToken;
    private final long expiresInMillis;
    private final long issuedClientTimeMillis;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LineAccessToken(String str, long j2, long j3) {
        this.accessToken = str;
        this.expiresInMillis = j2;
        this.issuedClientTimeMillis = j3;
    }

    private LineAccessToken(Parcel parcel) {
        this.accessToken = parcel.readString();
        this.expiresInMillis = parcel.readLong();
        this.issuedClientTimeMillis = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.accessToken);
        parcel.writeLong(this.expiresInMillis);
        parcel.writeLong(this.issuedClientTimeMillis);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public long getExpiresInMillis() {
        return this.expiresInMillis;
    }

    public long getIssuedClientTimeMillis() {
        return this.issuedClientTimeMillis;
    }

    public long getEstimatedExpirationTimeMillis() {
        return getIssuedClientTimeMillis() + getExpiresInMillis();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineAccessToken lineAccessToken = (LineAccessToken) obj;
        if (this.expiresInMillis == lineAccessToken.expiresInMillis && this.issuedClientTimeMillis == lineAccessToken.issuedClientTimeMillis) {
            return this.accessToken.equals(lineAccessToken.accessToken);
        }
        return false;
    }

    public int hashCode() {
        long j2 = this.expiresInMillis;
        long j3 = this.issuedClientTimeMillis;
        return (((this.accessToken.hashCode() * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)));
    }

    public String toString() {
        return "LineAccessToken{accessToken='#####', expiresInMillis=" + this.expiresInMillis + ", issuedClientTimeMillis=" + this.issuedClientTimeMillis + '}';
    }
}
