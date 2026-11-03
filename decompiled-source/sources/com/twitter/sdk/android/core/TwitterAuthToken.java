package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class TwitterAuthToken extends AuthToken implements Parcelable {
    public static final Parcelable.Creator<TwitterAuthToken> CREATOR = new Parcelable.Creator<TwitterAuthToken>() { // from class: com.twitter.sdk.android.core.TwitterAuthToken.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TwitterAuthToken createFromParcel(Parcel parcel) {
            return new TwitterAuthToken(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TwitterAuthToken[] newArray(int i2) {
            return new TwitterAuthToken[i2];
        }
    };
    @SerializedName("secret")
    public final String secret;
    @SerializedName("token")
    public final String token;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.twitter.sdk.android.core.AuthToken
    public boolean isExpired() {
        return false;
    }

    public TwitterAuthToken(String str, String str2) {
        this.token = str;
        this.secret = str2;
    }

    TwitterAuthToken(String str, String str2, long j2) {
        super(j2);
        this.token = str;
        this.secret = str2;
    }

    private TwitterAuthToken(Parcel parcel) {
        this.token = parcel.readString();
        this.secret = parcel.readString();
    }

    public String toString() {
        return "token=" + this.token + ",secret=" + this.secret;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.token);
        parcel.writeString(this.secret);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TwitterAuthToken) {
            TwitterAuthToken twitterAuthToken = (TwitterAuthToken) obj;
            String str = this.secret;
            if (str == null ? twitterAuthToken.secret == null : str.equals(twitterAuthToken.secret)) {
                String str2 = this.token;
                String str3 = twitterAuthToken.token;
                return str2 == null ? str3 == null : str2.equals(str3);
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String str = this.token;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.secret;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }
}
