package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes10.dex */
public class TwitterAuthConfig implements Parcelable {
    public static final Parcelable.Creator<TwitterAuthConfig> CREATOR = new Parcelable.Creator<TwitterAuthConfig>() { // from class: com.twitter.sdk.android.core.TwitterAuthConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TwitterAuthConfig createFromParcel(Parcel parcel) {
            return new TwitterAuthConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TwitterAuthConfig[] newArray(int i2) {
            return new TwitterAuthConfig[i2];
        }
    };
    public static final int DEFAULT_AUTH_REQUEST_CODE = 140;
    private final String consumerKey;
    private final String consumerSecret;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getRequestCode() {
        return 140;
    }

    public TwitterAuthConfig(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("TwitterAuthConfig must not be created with null consumer key or secret.");
        }
        this.consumerKey = sanitizeAttribute(str);
        this.consumerSecret = sanitizeAttribute(str2);
    }

    private TwitterAuthConfig(Parcel parcel) {
        this.consumerKey = parcel.readString();
        this.consumerSecret = parcel.readString();
    }

    public String getConsumerKey() {
        return this.consumerKey;
    }

    public String getConsumerSecret() {
        return this.consumerSecret;
    }

    static String sanitizeAttribute(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.consumerKey);
        parcel.writeString(this.consumerSecret);
    }
}
